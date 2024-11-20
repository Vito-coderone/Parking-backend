package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.LoginInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * User login information persistence layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

}
