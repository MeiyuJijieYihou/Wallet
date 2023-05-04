package com.answer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletDetailsShow extends WalletDetails implements Serializable {

    /**
     * 做了什么
     */
    private String doWhat;

}
