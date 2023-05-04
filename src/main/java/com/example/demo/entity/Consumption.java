package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包id
     */
    private String id;

    /**
     * 消费金额
     */
    private BigDecimal money;


}
