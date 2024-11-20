package com.shanzhu.parking.entity.query;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * Parking record information      Query object
 *
 * @author: Zi Cheng
 * @date: 2023-12-02
 */
@Data
public class StallResQuery {

    /**
     * username
     */
    private String person;

    /**
     * parking space
     */
    private String stallArea;

    /**
     * entry time
     */
    private LocalDateTime inTime;

    /**
     * exit time
     */
    private LocalDateTime outTime;

    /**
     * page size
     */
    private Integer pageSize;

    /**
     * -page number
     */
    private Integer pagenum;

}
