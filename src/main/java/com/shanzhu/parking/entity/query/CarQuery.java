package com.shanzhu.parking.entity.query;


import lombok.Data;

/**
 * Vehicle Information  Query Object
 *
 * @author: Zi Cheng
 * @date: 2024-12-02
 */
@Data
public class CarQuery {

    /**
     * personnel
     */
    private String person;

    /**
     * Vehicle Type
     */
    private String carType;

    /**
     * ID number
     */
    private String card;

    /**
     * Pagination page number
     */
    private Integer pagenum;

    /**
     * Page size
     */
    private Integer pageSize;

}
