package com.shanzhu.parking.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User Information
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {

    /**
     * user id
     */
    private Integer uid;

    /**
     * role id
     */
    private Integer role;

    /**
     * state
     */
    private Boolean state;

    /**
     * information
     */
    private String msg;

    public UserInfoVo() {

    }

    public UserInfoVo(Integer uid, Integer role, Boolean state, String msg) {
        this.uid = uid;
        this.role = role;
        this.state = state;
        this.msg = msg;
    }

}

  