package com.answer.controller;


import com.answer.entity.*;
import com.answer.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private IWalletService walletService;


    /**
     * 1.查询用户钱包信息，id为用户id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Msg getBalance(@PathVariable String id){
        Msg msg = new Msg();
        msg.setObj(walletService.getById(id));
        if (Objects.isNull(msg.getObj())) {
            msg.setFlag(false);
            msg.setMsg("当前用户不存在");
        } else {
            msg.setFlag(true);
            msg.setMsg("用户信息如下");
        }

        return msg;
    }

    /**
     * 创建用户
     * @param wallet
     * @return
     */
    @PostMapping("/save")
    public Msg saveUser(@RequestBody Wallet wallet)
    {
        Msg msg = new Msg();
        msg.setFlag(walletService.save(wallet));
        if (msg.isFlag()) {
            msg.setMsg("OK");
        } else {
            msg.setMsg("用户名不能为空");
        }
        msg.setObj(wallet);

        return msg;
    }

    /**
     * 用户充值，参数：id为用户id，money为退款金额
     * @param recharge
     * @return
     */
    @PostMapping("/recharge")
    public Msg refund(@RequestBody Recharge recharge){
        return walletService.recharge(recharge);
    }

    /**
     * 用户消费，参数：id为用户id，money为消费金额
     * @param consumption
     * @return
     */
    @PostMapping("/consumption")
    public Msg consumption(@RequestBody Consumption consumption){

        return walletService.consumption(consumption);
    }

    /**
     * 用户提现，参数：id为用户id，money为提现金额
     * @param withdrawal
     * @return
     */
    @PostMapping("/withdrawal")
    public Msg withdrawal(@RequestBody Withdrawal withdrawal){

        return walletService.withdrawal(withdrawal);
    }

    /**
     * 用户退款，参数：id为用户id，money为退款金额
     * @param refund
     * @return
     */
    @PostMapping("/refund")
    public Msg refund(@RequestBody Refund refund){
        return walletService.refund(refund);
    }


    /**
     * 查询用户钱包金额流水
      * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public Msg details(@PathVariable("id") String id){
        return walletService.details(id);
    }
}
