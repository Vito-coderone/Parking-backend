package com.shanzhu.parking.entity.query;


import lombok.Data;

/**
 * User Information Query Object
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Data
public class UserQuery {

    /**
     * username
     */
    private String username;

    /**
     * name
     */
    private String nike;

    /**
     * ID number
     */
    private String card;

    /**
     * page number
     */
    private Integer pagenum;

    /**
     * page number
     */
    private Integer pageSize;

}
