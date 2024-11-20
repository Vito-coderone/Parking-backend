package com.shanzhu.parking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzhu.parking.entity.po.Stall;
import com.shanzhu.parking.entity.po.StallRes;
import com.shanzhu.parking.entity.query.StallCarQuery;
import com.shanzhu.parking.entity.query.StallQuery;
import com.shanzhu.parking.entity.query.StallResQuery;
import com.shanzhu.parking.entity.vo.MsgVo;

import java.util.List;

/**
 * Parking space    Service Layer
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
public interface StallService extends IService<Stall> {

    /**
     * Paged acquisition
     *
     * @param stallQuery parameter
     * @return Parking space information
     */
    IPage<Stall> pageStall(StallQuery stallQuery);

    /**
     * Reserve a parking space
     *
     * @param uid User Number
     * @param sid Parking space number
     * @return result
     */
    Boolean orderStall(Integer uid, Integer sid);

    /**
     * Add parking space
     *
     * @param stall Parking space information
     * @return result
     */
    MsgVo addStall(Stall stall);

    /**
     * Update parking space
     *
     * @param stall Parking space information
     * @return Object
     */
    MsgVo updateStall(Stall stall);

    /**
     * Get all parking records of the user
     *
     * @param person user
     * @return Object
     */
    List<StallRes> listUserStallRes(String person);

    /**
     * Get all parking records
     *
     * @param stallResQuery Query information
     * @return Parking record
     */
    IPage<StallRes> getAllListStallRes(StallResQuery stallResQuery);

    /**
     * Parking Payment (Car Owner)
     *
     * @param stallRes Parking Information
     * @return result
     */
    MsgVo payMoneyPerson(StallRes stallRes);

    /**
     * Parking Payment (Administrator)
     *
     * @param stallRes Parking Information
     * @return result
     */
    Boolean payMoneyManager(StallRes stallRes);

    /**
     * Get all the user's unpaid records
     *
     * @param person username
     * @return Unpaid records
     */
    List<StallRes> getAllNoPay(String person);

    /**
     * Get user's parked vehicle information
     *
     * @param stallCarQuery Vehicle Information
     * @return Parked vehicle information
     */
    IPage<Stall> carPage(StallCarQuery stallCarQuery);
}
