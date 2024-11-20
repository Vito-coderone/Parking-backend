package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Parking fee information
 *
 *  * @author: Zi Cheng
 *  * @date: 2024-09-22

 */
@Data
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Charge Standard ID
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * Parking space type
     */
    private String carType;

    /**
     * price
     */
    private Double money;

    /**
     * Price Description
     */
    private String moneyDesc;

    /**
     * Creation time
     */
    private LocalDateTime feeTime;

}
