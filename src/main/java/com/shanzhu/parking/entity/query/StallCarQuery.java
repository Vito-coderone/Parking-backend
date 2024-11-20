package com.shanzhu.parking.entity.query;

import lombok.Data;

/**
 * Parking space information  Query object
 *
 * @author: Zi Cheng
 * @date: 2024-10-15
 */
@Data
public class StallCarQuery {

    /**
     * name
     */
    private String nike;

    /**
     * ID number
     */
    private String card;

    /**
     * Page size
     */
    private Integer pageSize;

    /**
     * Pagination page number
     */
    private Integer pagenum;

}
