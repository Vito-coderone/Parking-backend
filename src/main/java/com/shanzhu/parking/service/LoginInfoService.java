package com.shanzhu.parking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.query.LoginInfoQuery;

/**
 * Login Information Service Layer
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
public interface LoginInfoService extends IService<LoginInfo> {

    /**
     * Get the list of logged in users
     *
     * @param loginInfoQuery User Information
     * @return User List
     */
    IPage<LoginInfo> getLoginInfoList(LoginInfoQuery loginInfoQuery);

}
