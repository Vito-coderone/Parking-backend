package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.dto.DateType;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.query.UserQuery;
import com.shanzhu.parking.entity.vo.UserInfoVo;
import com.shanzhu.parking.mapper.LoginInfoMapper;
import com.shanzhu.parking.mapper.UserMapper;
import com.shanzhu.parking.service.UserService;
import com.shanzhu.parking.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * User information service layer implementation class
 *
 * @author: ChengZi
 * @date: 2024-10-02
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    private final LoginInfoMapper loginInfoMapper;

    /*   @Override
    public UserInfoVo login(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername()) && StrUtil.isNotBlank(user.getPassword())) {
            //查询用户信息
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));

            //Users can find
            if (existUser != null) {
                //Encrypt the entered password and compare it with the password in the database
                if (user.getPassword().equals(existUser.getPassword())) {
                    //Record login log
                    recommendLoginInfo(existUser.getUsername(), IpUtils.getRequest());
                    return new UserInfoVo(existUser.getUid(), existUser.getRole(), true, "Verification Success");
                } else {
                    return new UserInfoVo().setState(false).setMsg("Wrong password");
                }
            } else {
                return new UserInfoVo().setState(false).setMsg("Username does not exist");
            }
        }

        return new UserInfoVo().setState(false).setMsg("Username does not exist");
    }
    */
    @Override
    public UserInfoVo login(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername()) && StrUtil.isNotBlank(user.getPassword())) {
            // Query user information
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));
    
            // Users can find
            if (existUser != null) {
                // Encrypt the entered password and compare it with the password in the database
                String hashedPassword = hashPassword(user.getPassword());
                if (hashedPassword.equals(existUser.getPassword())) {
                    // Record login log
                    recommendLoginInfo(existUser.getUsername(), IpUtils.getRequest());
                    return new UserInfoVo(existUser.getUid(), existUser.getRole(), true, "Verification Success");
                } else {
                    return new UserInfoVo().setState(false).setMsg("Wrong password");
                }
            } else {
                return new UserInfoVo().setState(false).setMsg("Username does not exist");
            }
        }
    
        return new UserInfoVo().setState(false).setMsg("Username and password cannot be empty");
    }
    


    /**
     * Record login log
     */
    public void recommendLoginInfo(String username, HttpServletRequest request) {
        LoginInfo loginInfo = new LoginInfo();
        // Get the client IP
        String ip = IpUtils.getIpAddress(request);
        // Get client operation information
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        loginInfo.setIp(ip);
        loginInfo.setLoginTime(LocalDateTime.now());
        loginInfo.setBrowser(userAgent.getBrowser().getName());
        loginInfo.setOs(userAgent.getOperatingSystem().getName());
        loginInfo.setPerson(username);
        loginInfoMapper.insert(loginInfo);
    }

    @Override
    public Boolean register(User user) {
        //No user information
        if (user == null) {
            return false;
        }
        user.setCreateTime(LocalDateTime.now());

        //The registered user does not exist
        User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (existUser == null) {
            //Save User
            return this.save(user);
        }

        return false;
    }

    @Override
    public Boolean add(User user) {
        if (user != null && StrUtil.isNotBlank(user.getUsername())) {

            //The query user does not exist
            User existUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,
                    user.getUsername()));
            if (existUser == null) {
                //Save user information
                return this.save(user);
            }
        }
        return false;
    }

    @Override
    public Boolean delUser(Integer uid) {
        return this.removeById(uid);
    }

    @Override
    public Boolean updateUser(User user) {
        return this.updateByUser(user);
    }

    @Override
    public Boolean updateByUser(User user) {
        return this.updateById(user);
    }

    @Override
    public boolean resetPassword(Integer uid) {
        //Reset password to 123456
        User user = new User();
        user.setUid(uid);
        user.setPassword("123456");

        return this.updateById(user);
    }

    @Override
    public IPage<User> getUsersPage(UserQuery userQuery) {
        //Paging Information
        Page<User> page = new Page<>(userQuery.getPagenum(), userQuery.getPageSize());

        return lambdaQuery()
                //Fuzzy query user name
                .like(StrUtil.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername())
                //Fuzzy query name
                .like(StrUtil.isNotBlank(userQuery.getNike()), User::getNike, userQuery.getNike())
                //Fuzzy query license plate number
                .like(StrUtil.isNotBlank(userQuery.getCard()), User::getCard, userQuery.getCard())
                //Pagination Query
                .page(page);

    }

    @Override
    public Boolean userPay(User user) {
        User existUser = userMapper.selectById(user.getUid());
        if (existUser.getMoney() != null) {
            user.setMoney(existUser.getMoney() + user.getMoney());
        }
        return this.updateById(user);
    }

    @Override
    public List<DateType> getUserDataType(String person) {
        return userMapper.getUserDataType(person);
    }

    @Override
    public List<DateType> getUserDataMoney(String person) {
        return userMapper.getUserDataMoney(person);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in hashing password", e);
        }
    }
    
    /**
     * Batch encrypt the plaintext passwords of users in the database
     */
    public void encryptExistingPasswords() {
        // Query all users
        List<User> users = userMapper.selectList(null);

        for (User user : users) {
            // If the password is not encrypted, encrypt it
            if (user.getPassword() != null && !user.getPassword().matches("^[a-fA-F0-9]{64}$")) { 
                String hashedPassword = hashPassword(user.getPassword());
                user.setPassword(hashedPassword);
                // Update to database
                userMapper.updateById(user);
            }
        }
        System.out.println("All user passwords are encrypted!");
    }
}
