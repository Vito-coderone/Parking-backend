package com.shanzhu.parking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.StallType;
import com.shanzhu.parking.mapper.StallTypeMapper;
import com.shanzhu.parking.service.StallTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Parking space type service layer implementation class
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class StallTypeServiceImpl extends ServiceImpl<StallTypeMapper, StallType> implements StallTypeService {

}
