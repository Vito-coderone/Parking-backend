package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.*;
import com.shanzhu.parking.entity.vo.MsgVo;
import com.shanzhu.parking.mapper.FeeMapper;
import com.shanzhu.parking.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Parking fee information service layer implementation class
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class FeeServiceImpl extends ServiceImpl<FeeMapper, Fee> implements FeeService {

    private final StallTypeService stallTypeService;

    private final StallService stallService;

    private final UserService userService;

    private final RechargeService rechargeService;

    @Override
    public MsgVo updateFee(Fee fee) {
        if (StrUtil.isNotBlank(fee.getCarType()) && fee.getMoney() != null) {
            Boolean updateStatus = this.update(
                    fee, Wrappers.<Fee>lambdaQuery().eq(Fee::getCarType, fee.getCarType()));

            //Update Success
            if (updateStatus) {
                //Update parking fees
                Stall stall = new Stall();
                stall.setStallType(fee.getCarType());
                stall.setStallMoney(fee.getMoney());
                stallService.update(stall, Wrappers.<Stall>lambdaQuery().eq(Stall::getStallType, fee.getCarType()));

                //Update parking space prices
                StallType stallType = new StallType();
                stallType.setOmoney(fee.getMoney());
                stallType.setOtype(fee.getCarType());
                stallTypeService.update(stallType, Wrappers.<StallType>lambdaQuery().eq(StallType::getOtype,
                        fee.getCarType()));

                return new MsgVo(true, "Update Success");
            }
        } else {
            return new MsgVo(false, "Update failed, incomplete");
        }

        return new MsgVo(false, "Update failed, please try again");
    }

    @Override
    public MsgVo addUserFee(User user) {
        if (StrUtil.isNotBlank(user.getUsername()) && user.getMoney() != null) {
            //Query User
            User existUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));

            //User does not exist
            if (existUser == null) {
                return new MsgVo(false, "Recharge failed, user does not exist");
            }

            Double mon = user.getMoney();
            Double non = existUser.getMoney() == null ? 0 : existUser.getMoney();

            //Update recharge balance
            user.setMoney(non + user.getMoney());
            Boolean updateStatus = userService.update(
                    user, Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));

            if (updateStatus) {
                //Save recharge records
                Recharge recharge = new Recharge();
                recharge.setCtime(LocalDateTime.now());
                recharge.setPerson(user.getUsername());
                recharge.setMoney(mon);
                rechargeService.save(recharge);

                return new MsgVo(true, "Recharge successful");
            }
        }

        return new MsgVo(false, "Recharge failed, incomplete information");

    }
}
