package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 金额变动
     */
    private BigDecimal changeBalance;

    /**
     * 钱包余额
     */
    private BigDecimal balance;

    /**
     * 钱包id
     */
    private String walletId;
    /**
     * 更新创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 钱包流水类型：0为存钱，1为消费，3为退款，4为提现
     */
    private int type;

}
