package com.shanzhu.parking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzhu.parking.entity.dto.DateType;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.query.UserQuery;
import com.shanzhu.parking.entity.vo.UserInfoVo;

import java.util.List;

/**
 * User Information   service layer
 *
 * @author: Zi Cheng
 * @date: 2024-10-15
 */
public interface UserService extends IService<User> {

    /**
     * user login
     *
     * @param user user info
     * @return Login Results
     */
    UserInfoVo login(User user);

    /**
     * User Registration
     *
     * @param user user info
     * @return boolean
     */
    Boolean register(User user);

    /**
     * add user
     *
     * @param user user info
     * @return boolean
     */
    Boolean add(User user);

    /**
     * delate user
     *
     * @param uid user id
     * @return boolean
     */
    Boolean delUser(Integer uid);

    /**
     * update user info
     *
     * @param user user info
     * @return boolean
     */
    Boolean updateUser(User user);

    /**
     * User updates personal information
     *
     * @param user user info
     * @return result
     */
    Boolean updateByUser(User user);

    /**
     * Reset Password
     *
     * @param uid User Number
     * @return boolean
     */
    boolean resetPassword(Integer uid);

    /**
     * Get users by page
     *
     * @param userQuery Condition Information
     * @return user
     */
    IPage<User> getUsersPage(UserQuery userQuery);

    /**
     * User recharge
     *
     * @param user user info
     * @return Boolean
     */
    Boolean userPay(User user);

    /**
     * Get user parking type records
     *
     * @param person user
     * @return Parking space type
     */
    List<DateType> getUserDataType(String person);

    /**
     * Get user payment records
     *
     * @param person user 
     * @return Payment Record
     */
    List<DateType> getUserDataMoney(String person);

}
