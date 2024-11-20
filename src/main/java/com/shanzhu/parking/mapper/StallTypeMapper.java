package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.StallType;
import org.apache.ibatis.annotations.Mapper;

/**
 * Parking Space Type Persistence Layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface StallTypeMapper extends BaseMapper<StallType> {

}
