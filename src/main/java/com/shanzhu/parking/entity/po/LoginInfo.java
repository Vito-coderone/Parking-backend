package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Login Information
 *
 * @author: Zi Cheng
 * @date: 2024-09-24
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Login ID
     */
    @TableId(value = "yid", type = IdType.AUTO)
    private Integer yid;

    /**
     * user
     */
    private String person;

    /**
     * IP address
     */
    private String ip;

    /**
     * browser
     */
    private String browser;

    /**
     * operating system
     */
    private String os;

    /**
     * login time
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

}
