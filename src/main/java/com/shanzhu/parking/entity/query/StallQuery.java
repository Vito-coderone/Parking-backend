package com.shanzhu.parking.entity.query;


import lombok.Data;

/**
 * Parking space information   Query object
 *
 * @author: Zi Cheng
 * @date: 2024-11-02
 */
@Data
public class StallQuery {

    /**
     * Parking area
     */
    private String carArea;

    /**
     * Parking space type
     */
    private String carType;

    /**
     * Parking space status
     */
    private String carState;

    /**
     * Number of pages
     */
    private Integer pagenum;

    /**
     * 
     */
    private Integer pageSize;

}
