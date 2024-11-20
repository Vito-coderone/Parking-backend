package com.shanzhu.parking.entity.query;

import lombok.Data;

/**
 * User Information   Query Object
 *
 * @author:     Zi Cheng
 * @date: 2024-12-02
 */
@Data
public class LoginInfoQuery {

    /**
     * username
     */
    private String person;

    /**
     * ip
     */
    private String ip;

    /**
     * Page size
     */
    private Integer pageSize;

    /**
     * Number of pages
     */
    private Integer pagenum;

}
