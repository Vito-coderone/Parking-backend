package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Recharge
 *
 * @author: Zi Cheng
 * @date: 2024-09-27
 */
@Data
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Recharge Number
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * user
     */
    private String person;

    /**
     * Recharge amount
     */
    private Double money;

    /**
     * Recharge time
     */
    private LocalDateTime ctime;

}
