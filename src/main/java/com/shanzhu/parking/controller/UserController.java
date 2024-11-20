package com.shanzhu.parking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shanzhu.parking.common.R;
import com.shanzhu.parking.entity.dto.DateType;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.query.UserQuery;
import com.shanzhu.parking.entity.vo.UserInfoVo;
import com.shanzhu.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User related  control layer
 *
 * @author: Cheng Zi
 * @date: 2024-09-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * User login
     *
     * @param user User Info
     * @return User
     */
    @PostMapping("/login")
    public R<UserInfoVo> login(@RequestBody User user) {
        return R.success(userService.login(user));
    }

    /**
     * Registered User
     *
     * @param user User Information
     * @return Registration Results
     */
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody User user) {
        return R.success(userService.register(user));
    }

    /**
     * Check Username
     *
     * @param username Username
     * @return Is it repeated?
     */
    @GetMapping("/checkUsername")
    public R<Boolean> checkUsername(String username) {
        return R.success(userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)) == null);
    }

    /**
     * Add a new user
     *
     * @param user User Information
     * @return Result
     * 
     */
    @PostMapping("/add")
    public R<Boolean> addUser(@RequestBody User user) {
        return R.success(userService.add(user));
    }

    /**
     * Delete user information based on user ID
     *
     * @param uid user id
     * @return result
     */
    @GetMapping("/del")
    public R<Boolean> delUser(Integer uid) {
        return R.success(userService.delUser(uid));
    }

    /**
     * Get users by page
     *
     * @param userQuery condition information
     * @return user information
     */
    @PostMapping("/getUsers")
    public R<IPage<User>> getUsersPage(@RequestBody UserQuery userQuery) {
        return R.success(userService.getUsersPage(userQuery));
    }

    /**
     * Change User Information
     *
     * @param user User Information
     * @return Update results
     */
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        return R.success(userService.updateUser(user));
    }

    /**
     * User changes personal information
     *
     * @param user User Information
     * @return Update results
     */
    @PostMapping("/updateByUser")
    public R<Boolean> updateByUser(@RequestBody User user) {
        return R.success(userService.updateByUser(user));
    }

    /**
     * Reset Password
     *
     * @param uid User id
     * @return Result
     */
    @GetMapping("/resetPassword")
    public R<Boolean> resetPassword(Integer uid) {
        return R.success(userService.resetPassword(uid));
    }


    /**
     * Query by user id
     *
     * @param uid User id
     * @return User
     */
    @GetMapping("/getUserByUid")
    public R<User> gerUserByUid(Integer uid) {
        return R.success(userService.getById(uid));
    }


    /**
     * User recharge
     *
     * @param user User Information
     * @return Result
     */
    @PostMapping("/userPay")
    public R<Boolean> userPay(@RequestBody User user) {
        return R.success(userService.userPay(user));
    }

    /**
     * Get user parking type records
     *
     * @param person user
     * @return Parking space type
     */
    @GetMapping("/getCarTypes")
    public R<List<DateType>> getCarTypes(String person) {
        return R.success(userService.getUserDataType(person));
    }

    /**
     * Get user payment records
     *
     * @param person user
     * @return Payment Record
     */
    @GetMapping("/dataMoney")
    public R<List<DateType>> getUserMoney(String person) {
        return R.success(userService.getUserDataMoney(person));
    }

}

