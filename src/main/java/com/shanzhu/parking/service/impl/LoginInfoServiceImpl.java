package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.LoginInfo;
import com.shanzhu.parking.entity.query.LoginInfoQuery;
import com.shanzhu.parking.mapper.LoginInfoMapper;
import com.shanzhu.parking.service.LoginInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Login information Service layer implementation class
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

    @Override
    public IPage<LoginInfo> getLoginInfoList(LoginInfoQuery loginInfoQuery) {
        //Paging conditions
        Page<LoginInfo> page = new Page<>(loginInfoQuery.getPagenum(), loginInfoQuery.getPageSize());

        //Query conditions
        return lambdaQuery()
                //Fuzzy query username username
                .like(StrUtil.isNotBlank(loginInfoQuery.getPerson()),LoginInfo::getPerson, loginInfoQuery.getPerson())
                //Fuzzy query IP
                .like(StrUtil.isNotBlank(loginInfoQuery.getIp()),LoginInfo::getIp, loginInfoQuery.getIp())
                .page(page);
    }

}
