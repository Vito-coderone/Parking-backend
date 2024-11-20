package com.shanzhu.parking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzhu.parking.entity.po.Fee;
import com.shanzhu.parking.entity.po.User;
import com.shanzhu.parking.entity.vo.MsgVo;

/**
 * Parking fee information Service layer
 *
 * @author: Cheng Zi
 * @date: 2023-12-02
 */
public interface FeeService extends IService<Fee> {

    /**
     * Update parking fee
     *
     * @param fee Parking space information
     * @return result
     */
    MsgVo updateFee(Fee fee);

    /**
     * User balance recharge
     *
     * @param user User Information
     * @return result
     */
    MsgVo addUserFee(User user);
}
