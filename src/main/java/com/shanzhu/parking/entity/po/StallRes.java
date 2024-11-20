package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Parking record
 *
 * @author: Zi Cheng
 * @date: 2024-10-07
 */
@Data
@TableName("stall_res")
public class StallRes {

    /**
     * Primary Key
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * username
     */
    @TableField("person")
    private String person;

    /**
     * Parking space id
     */
    @TableField("stall_id")
    private Integer stallId;

    /**
     * Creation time
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * End time
     */
    @TableField("over_time")
    private LocalDateTime overTime;

    /**
     * Payment amount
     */
    @TableField("pay_money")
    private Double money;

    /**
     * Parking Space
     */
    @TableField(exist = false)
    private Stall stall;

}

