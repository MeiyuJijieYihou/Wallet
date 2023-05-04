package com.answer.service;

import com.answer.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IWalletService extends IService<Wallet> {

    /**
     * 消费
     * @param consumption
     * @return
     */
    Msg consumption(Consumption consumption);

    /**
     * 取款
     * @param withdrawal
     * @return
     */
    Msg withdrawal(Withdrawal withdrawal);

    /**
     * 新增用户
     * @param wallet
     * @return
     */
    boolean save(Wallet wallet);

    /**
     * 退款
     * @param refund
     * @return
     */
    Msg refund(Refund refund);

    /**
     * 充值
     * @param recharge
     * @return
     */
    Msg recharge(Recharge recharge);

    /**
     * 钱包流水记录
     * @param id
     * @return
     */
    Msg details(String id);

    /**
     * 查询钱包余额等信息
     * @param id
     * @return
     */
    Wallet getById(String id);
}
