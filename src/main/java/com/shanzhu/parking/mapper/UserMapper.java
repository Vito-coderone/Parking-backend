package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.dto.DateType;
import com.shanzhu.parking.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User Information Persistence Layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * Get user parking type records
     *
     * @param person user
     * @return Parking space type
     */
    List<DateType> getUserDataType(@Param("person") String person);

    /**
     * Get user payment records
     *
     * @param person user
     * @return Payment Record
     */
    List<DateType> getUserDataMoney(@Param("person") String person);
}
