package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzhu.parking.entity.po.Stall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Parking Space Persistence Layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface StallMapper extends BaseMapper<Stall> {

    /**
     * Set parking space vacancy status
     *
     * @param sid parking space id
     * @return result
     */
    Integer setStallOrg(@Param("sid") Integer sid);

    /**
     * Get user's parked vehicle information
     *
     * @param page Paging Information
     * @param nike nike name
     * @param card License plate number
     * @return Parked vehicle information
     */
    IPage<Stall> getStallAll(Page<Stall> page, @Param("nike") String nike, @Param("card") String card);
}
