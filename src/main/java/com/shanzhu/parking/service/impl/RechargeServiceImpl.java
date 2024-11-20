package com.shanzhu.parking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.Recharge;
import com.shanzhu.parking.mapper.RechargeMapper;
import com.shanzhu.parking.service.RechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Recharge service layer implementation class
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

}
