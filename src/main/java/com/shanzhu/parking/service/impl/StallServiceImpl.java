package com.shanzhu.parking.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzhu.parking.entity.po.*;
import com.shanzhu.parking.entity.query.StallCarQuery;
import com.shanzhu.parking.entity.query.StallQuery;
import com.shanzhu.parking.entity.query.StallResQuery;
import com.shanzhu.parking.entity.vo.MsgVo;
import com.shanzhu.parking.mapper.*;
import com.shanzhu.parking.service.StallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Parking space   service layer implementation class
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Service
@RequiredArgsConstructor
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements StallService {

    private final StallMapper stallMapper;

    private final StallResMapper stallResMapper;

    private final StallTypeMapper stallTypeMapper;

    private final UserMapper userMapper;

    private final RechargeMapper rechargeMapper;

    @Override
    public IPage<Stall> pageStall(StallQuery stallQuery) {
        //Paging conditions
        Page<Stall> page = new Page<>(stallQuery.getPagenum(), stallQuery.getPageSize());

        //Query conditions
        LambdaQueryWrapper<Stall> lambdaQuery = Wrappers.<Stall>lambdaQuery()
                //Parking Space
                .eq(StrUtil.isNotBlank(stallQuery.getCarArea()), Stall::getStallArea, stallQuery.getCarArea())
                //Parking space status
                .eq(StrUtil.isNotBlank(stallQuery.getCarState()), Stall::getStallState, stallQuery.getCarState())
                //Parking space type
                .eq(StrUtil.isNotBlank(stallQuery.getCarType()), Stall::getStallType, stallQuery.getCarType())
                //Not deleted
                .eq(Stall::getStallLive, "1");

        //Pagination Query
        return this.page(page, lambdaQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean orderStall(Integer uid, Integer sid) {
        //Query parking space information
        Stall stall = this.getById(sid);

        //No one is parking
        if (stall.getUserId() == null) {

            //Update parking space status
            stall.setUserId(uid);
            stall.setStallState("已停车");
            stallMapper.updateById(stall);

            //Add personal parking record
            StallRes stallRes = new StallRes();
            User user = userMapper.selectById(uid);
            stallRes.setPerson(user.getUsername());
            stallRes.setStallId(sid);
            stallRes.setCreateTime(LocalDateTime.now());
            stallResMapper.insert(stallRes);

            return true;
        }

        return false;
    }

    @Override
    public MsgVo addStall(Stall stall) {
        //Query parking space type
        StallType stallType = stallTypeMapper.selectOne(
                Wrappers.<StallType>lambdaQuery().eq(StallType::getOtype,
                        stall.getStallType())
        );

        //Query existing parking space information
        Stall existStall = stallMapper.selectOne(
                Wrappers.<Stall>lambdaQuery()
                        .eq(Stall::getStallNum, stall.getStallNum())
                        .eq(Stall::getStallArea, stall.getStallArea())
                        .eq(Stall::getStallType, stall.getStallType()));

        //Parking space does not exist
        if (existStall == null) {
            //Set parking space status
            stall.setStallState("Idle");
            stall.setStallLive("1");
            stall.setStallMoney(stallType.getOmoney());

            //Save parking space
            if (this.save(stall)) {
                return new MsgVo(true, "Added successfully");
            } else {
                return new MsgVo(false, "Add failed, please try again");
            }
        } else {
            return new MsgVo(false, "This parking space already exists");
        }
    }

    @Override
    public MsgVo updateStall(Stall stall) {
        //Check existing parking spaces
        Stall existStall = stallMapper.selectOne(
                Wrappers.<Stall>lambdaQuery()
                        .eq(Stall::getStallNum, stall.getStallNum())
                        .eq(Stall::getStallArea, stall.getStallArea()));

        //Parking space can be updated only if it exists
        if (existStall != null) {
            if (this.updateById(stall)) {
                return new MsgVo(true, "Modification successful");
            } else {
                return new MsgVo(false, "The modification failed, please try again");
            }
        } else {
            return new MsgVo(false, "Modification failed, the parking space does not exist");
        }

    }

    @Override
    public List<StallRes> listUserStallRes(String person) {
        return stallResMapper.getAllStallRes(person);
    }

    @Override
    public IPage<StallRes> getAllListStallRes(StallResQuery stallResQuery) {
        //Paging conditions
        Page<StallRes> page = new Page<>(stallResQuery.getPagenum(), stallResQuery.getPageSize());

        return stallResMapper.getAllListStallRes(
                page, stallResQuery.getPerson(), stallResQuery.getInTime(),
                stallResQuery.getOutTime(), stallResQuery.getStallArea()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsgVo payMoneyPerson(StallRes stallRes) {
        //Query car owner user
        User user = userMapper.selectOne(
                Wrappers.<User>lambdaQuery().eq(User::getUsername, stallRes.getPerson())
        );

        if (user == null) {
            return new MsgVo(false, "Payment failed");
        }

        if (user.getMoney() >= stallRes.getMoney()) {
            //Update user balance
            user.setMoney(user.getMoney() - stallRes.getMoney());
            userMapper.updateById(user);

            //Set parking space vacancy status
            stallMapper.setStallOrg(stallRes.getStallId());

            //Update parking record
            StallRes userStallRes = new StallRes();
            userStallRes.setPid(stallRes.getPid());
            userStallRes.setOverTime(LocalDateTime.now());
            userStallRes.setMoney(stallRes.getMoney());
            stallResMapper.updateById(userStallRes);

            //Keep payment records
            Recharge recharge = new Recharge();
            recharge.setMoney(stallRes.getMoney());
            recharge.setPerson(stallRes.getPerson());
            recharge.setCtime(LocalDateTime.now());
            rechargeMapper.insert(recharge);

            return new MsgVo(true, "Payment successful");
        } else {
            return new MsgVo(false, "Insufficient balance, please recharge first");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean payMoneyManager(StallRes stallRes) {
        //Query car owner user
        User carUser = userMapper.selectOne(
                Wrappers.<User>lambdaQuery().eq(User::getUsername, stallRes.getPerson())
        );

        if (carUser == null) {
            return false;
        }

        //Update user balance
        carUser.updateMoney(stallRes.getMoney());
        userMapper.updateById(carUser);

        //Set parking space vacancy status
        stallMapper.setStallOrg(stallRes.getStallId());

        //Update parking record
        StallRes userStrllRes = new StallRes();
        userStrllRes.setPid(stallRes.getPid());
        userStrllRes.setOverTime(LocalDateTime.now());
        userStrllRes.setMoney(stallRes.getMoney());
        stallResMapper.updateById(userStrllRes);

        //Keep payment records
        Recharge recharge = new Recharge();
        recharge.setMoney(stallRes.getMoney());
        recharge.setPerson(stallRes.getPerson());
        recharge.setCtime(LocalDateTime.now());
        rechargeMapper.insert(recharge);

        return true;
    }


    @Override
    public List<StallRes> getAllNoPay(String person) {
        return stallResMapper.getAllStallRes(person);
    }

    @Override
    public IPage<Stall> carPage(StallCarQuery stallCarQuery) {
        Page<Stall> page = new Page<>(stallCarQuery.getPagenum(), stallCarQuery.getPageSize());
        return stallMapper.getStallAll(page, stallCarQuery.getNike(), stallCarQuery.getCard());
    }

}
