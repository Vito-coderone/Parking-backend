package com.shanzhu.parking.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Parking fee payment statistics
 *
 * @author: Cheng Zi
 * @date: 2024-09-23
 */
@Data
public class StallFee {

    /**
     * name
     */
    private LocalDateTime name;

    /**
     * value
     */
    private Double value;

}
