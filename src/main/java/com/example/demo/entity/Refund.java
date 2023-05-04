package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包id
     */
    private String id;

    /**
     * 退款金额
     */
    private BigDecimal money;


}
