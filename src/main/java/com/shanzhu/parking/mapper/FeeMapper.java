package com.shanzhu.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzhu.parking.entity.po.Fee;
import org.apache.ibatis.annotations.Mapper;

/**
 * Vehicle charging information   persistence layer
 *
 * @author: Zi Cheng
 * @date: 2023-11-25
 */
@Mapper
public interface FeeMapper extends BaseMapper<Fee> {

}
