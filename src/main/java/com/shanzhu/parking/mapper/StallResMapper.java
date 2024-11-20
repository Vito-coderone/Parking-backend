package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzhu.parking.entity.po.StallRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Parking record persistence layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface StallResMapper extends BaseMapper<StallRes> {

    /**
     * Get all parking records of the user
     *
     * @param person username
     * @return Parking record
     */
    List<StallRes> getAllStallRes(@Param("person") String person);

    /**
     * Get all parking records
     *
     * @param person username
     * @return Parking record
     */
    IPage<StallRes> getAllListStallRes(Page<StallRes> page, @Param("person") String person,
                                       @Param("inTime") LocalDateTime inTime,
                                       @Param("outTime") LocalDateTime outTime,
                                       @Param("stallArea") String stallArea);
}
