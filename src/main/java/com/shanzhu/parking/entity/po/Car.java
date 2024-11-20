package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * car
 *
 * @author: Zi Cheng
 * @date: 2024-09-22
 */
@Data
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Vehicle number
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    /**
     * License plate number
     */
    private String card;

    /**
     * Car Type
     */
    @TableField("car_type")
    private String carType;

    /**
     * user
     */
    private String person;

    /**
     * Entry time
     */
    private LocalDateTime xtime;

    @TableField(exist = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(tid, car.tid) && Objects.equals(card, car.card) && Objects.equals(carType, car.carType) && Objects.equals(person, car.person) && Objects.equals(xtime, car.xtime) && Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, card, carType, person, xtime, user);
    }

    @Override
    public String toString() {
        return "Car{" +
                "tid=" + tid +
                ", card='" + card + '\'' +
                ", carType='" + carType + '\'' +
                ", person='" + person + '\'' +
                ", xtime=" + xtime +
                ", user=" + user +
                '}';
    }
}
