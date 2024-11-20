package com.shanzhu.parking.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * user info
 *
 * @author: Zi Cheng
 * @date: 2024-10-19
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * nike name
     */
    private String nike;

    /**
     * age
     */
    private Integer age;

    /**
     * sex
     */
    private String sex;

    /**
     * contact number
     */
    private String phone;

    /**
     * licesn plate number
     */
    private String card;

    /**
     * Balance
     */
    private Double money;

    /**
     * role. 0 System Administrator，1 car owner
     */
    private Integer role;

    /**
     * Payment deduction
     *
     * @param payMoney Payment amount
     */
    public void updateMoney(Double payMoney) {
        this.money = this.money - payMoney;
    }

    /**
     * creation time
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password,
                user.password) && Objects.equals(nike, user.nike) && Objects.equals(age, user.age) && Objects.equals(sex, user.sex) && Objects.equals(phone, user.phone) && Objects.equals(card, user.card) && Objects.equals(money, user.money) && Objects.equals(role, user.role) && Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, nike, age, sex, phone, card, money, role, createTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nike='" + nike + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", card='" + card + '\'' +
                ", money=" + money +
                ", role=" + role +
                ", createTime=" + createTime +
                '}';
    }
}
