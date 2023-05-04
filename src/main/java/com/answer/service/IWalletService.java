package com.answer.service;

import com.answer.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IWalletService extends IService<Wallet> {

    Msg consumption(Consumption consumption);

    Msg withdrawal(Withdrawal withdrawal);

    boolean save(Wallet wallet);

    Msg refund(Refund refund);

    Msg recharge(Recharge recharge);

    Msg details(String id);

    Wallet getById(String id);
}
