package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;


    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 更新创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public Wallet() {

    }
}
