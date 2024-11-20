package com.shanzhu.parking.entity.vo;

import lombok.Data;

/**
 * Message object vo
 * Compatible interface message
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Data
public class MsgVo {

    /**
     * Message Status
     */
    private Boolean flag;

    /**
     * Message copy
     */
    private String msg;

    public MsgVo(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

}

  