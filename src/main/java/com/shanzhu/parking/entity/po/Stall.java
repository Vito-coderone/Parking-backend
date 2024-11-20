package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Parking
 *
 * @author: Zi Cheng
 * @date: 2024-10-5
 */
@Data
public class Stall implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Parking space number
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    /**
     * Parking space number
     */
    @TableField("stall_num")
    private String stallNum;

    /**
     * Parking area
     */
    @TableField("stall_area")
    private String stallArea;

    /**
     * Parking space type
     */
    @TableField("stall_type")
    private String stallType;

    /**
     * Parking space status
     */
    @TableField("stall_state")
    private String stallState;

    /**
     * Parking fee
     */
    @TableField("stall_money")
    private Double stallMoney;

    /**
     * Logical deletion flag
     */
    @TableField("stall_live")
    private String stallLive;

    /**
     * User Number
     */
    @TableField("user_id")
    private Integer userId;

    @TableField(exist = false)
    private User user;

}
