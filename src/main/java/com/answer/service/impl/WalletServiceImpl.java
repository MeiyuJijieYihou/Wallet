package com.answer.service.impl;

import com.answer.entity.*;
import com.answer.mapper.WalletMapper;
import com.answer.service.IWalletService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements IWalletService {

    @Autowired
    private WalletDetailsServiceImpl walletDetailsService;


    @Override
    public Wallet getById(String id) {

        // 返回用户钱包数据
        return super.getById(id);

    }

    @Override
    @Transactional // 添加事务
    public Msg consumption(Consumption consumption) {
        Msg msg = new Msg();
        msg.setObj(consumption);

        // 非空校验
        if (Objects.isNull(consumption)
                || Objects.isNull(consumption.getId())
                || Objects.isNull(consumption.getMoney())) {
            msg.setFlag(false);
            msg.setMsg("用户ID或消费金额为空");
            return msg;
        }

        // 消费金额不能为负数
        if (consumption.getMoney().compareTo(new BigDecimal(0)) < 0) {
            msg.setFlag(false);
            msg.setMsg("消费金额不能为负数");
            return msg;
        }

        // 获取用户钱包信息
        Wallet wallet = super.getById(consumption.getId());
        if (Objects.isNull(wallet)
                || Objects.isNull(wallet.getId())) {
            msg.setFlag(false);
            msg.setMsg("当前用户不存在");
            return msg;
        }

        // 尝试计算消费后的钱包余额
        BigDecimal newBalance = wallet.getBalance().subtract(consumption.getMoney());
        // 如果钱包余额小于0，则消费失败
        if(newBalance.compareTo(new BigDecimal(0)) < 0){
            msg.setFlag(false);
            msg.setMsg("消费失败，钱包余额不足，当前金额为："+wallet.getBalance()+",消费金额为："+consumption.getMoney());
            return msg;

        }

        // 新增消费明细，插入钱包明细表
        WalletDetails walletDetails = new WalletDetails();
        walletDetails.setId(UUID.randomUUID().toString().replace("-", ""));
        walletDetails.setType(1);
        walletDetails.setWalletId(consumption.getId());
        walletDetails.setChangeBalance(consumption.getMoney());
        walletDetails.setBalance(newBalance);
        System.out.println(walletDetails);
        walletDetailsService.save(walletDetails);

        // 更新钱包金额
        wallet.setBalance(newBalance);
        this.updateById(wallet);
        msg.setFlag(true);
        msg.setMsg("消费成功，消费后钱包余额为"+newBalance+",消费金额为："+consumption.getMoney());

        return msg;
    }

    @Override
    public Msg withdrawal(Withdrawal withdrawal) {
        Msg msg = new Msg();
        msg.setObj(withdrawal);

        // 非空校验
        if (Objects.isNull(withdrawal)
                || Objects.isNull(withdrawal.getId())
                || Objects.isNull(withdrawal.getMoney())) {
            msg.setFlag(false);
            msg.setMsg("用户ID或提现金额为空");
            return msg;
        }

        // 消费金额不能为负数
        if (withdrawal.getMoney().compareTo(new BigDecimal(0)) < 0) {
            msg.setFlag(false);
            msg.setMsg("提现金额不能为负数");
            return msg;
        }

        // 获取用户钱包信息
        Wallet wallet = super.getById(withdrawal.getId());
        if (Objects.isNull(wallet)
                || Objects.isNull(wallet.getId())) {
            msg.setFlag(false);
            msg.setMsg("当前用户不存在");
            return msg;
        }

        // 尝试计算消费后的钱包余额
        BigDecimal newBalance = wallet.getBalance().subtract(withdrawal.getMoney());
        // 如果钱包余额小于0，则消费失败
        if(newBalance.compareTo(new BigDecimal(0)) < 0){
            msg.setFlag(false);
            msg.setMsg("提现失败，钱包余额不足，当前金额为："+wallet.getBalance()+",提现金额为："+withdrawal.getMoney());
            return msg;

        }

        // 新增提现聚记录，插入钱包流水表
        WalletDetails walletDetails = new WalletDetails();
        walletDetails.setId(UUID.randomUUID().toString().replace("-", ""));
        walletDetails.setType(1);
        walletDetails.setWalletId(withdrawal.getId());
        walletDetails.setChangeBalance(withdrawal.getMoney());
        walletDetails.setBalance(newBalance);
        System.out.println(walletDetails);
        walletDetailsService.save(walletDetails);

        // 更新钱包金额
        wallet.setBalance(newBalance);
        this.updateById(wallet);
        msg.setFlag(true);
        msg.setMsg("提现成功，提现后钱包余额为"+newBalance+",提现金额为：" + withdrawal.getMoney());

        return msg;
    }

    @Override
    public boolean save(Wallet wallet) {

        // 校验
        if (Objects.isNull(wallet.getUsername())) return false;
        if (Objects.isNull(wallet.getBalance())) wallet.setBalance(new BigDecimal(4));

        // 设置ID
        wallet.setId(UUID.randomUUID().toString().replace("-", ""));

        return super.save(wallet);
    }

    @Override
    public Msg refund(Refund refund) {
        Msg msg = new Msg();
        msg.setObj(refund);

        // 非空校验
        if (Objects.isNull(refund)
                || Objects.isNull(refund.getId())
                || Objects.isNull(refund.getMoney())) {
            msg.setFlag(false);
            msg.setMsg("用户ID或退款金额为空");
            return msg;
        }

        // 退款金额不能为负数
        if (refund.getMoney().compareTo(new BigDecimal(0)) < 0) {
            msg.setFlag(false);
            msg.setMsg("退款金额不能为负数");
            return msg;
        }

        // 获取用户钱包信息
        Wallet wallet = this.getById(refund.getId());
        if (Objects.isNull(wallet)
                || Objects.isNull(wallet.getId())) {
            msg.setFlag(false);
            msg.setMsg("当前用户不存在");
            return msg;
        }


        // 计算当前钱包金额
        BigDecimal newBalance = wallet.getBalance().add(refund.getMoney());

        // 新增退款明细，插入钱包流水表
        WalletDetails walletDetails = new WalletDetails();
        walletDetails.setType(3);
        walletDetails.setWalletId(refund.getId());
        walletDetails.setChangeBalance(refund.getMoney());
        walletDetails.setBalance(newBalance);
        walletDetailsService.save(walletDetails);
        // 更新钱包金额
        wallet.setBalance(newBalance);
        this.updateById(wallet);
        msg.setFlag(true);
        msg.setMsg("退款成功，当前钱包余额为" + newBalance + ", 退款金额为：" + refund.getMoney());

        return msg;

    }

    @Override
    public Msg recharge(Recharge recharge) {
        Msg msg = new Msg();
        msg.setObj(recharge);

        // 非空校验
        if (Objects.isNull(recharge)
                || Objects.isNull(recharge.getId())
                || Objects.isNull(recharge.getMoney())) {
            msg.setFlag(false);
            msg.setMsg("充值失败，用户ID或充值金额为空");
            return msg;
        }

        // 充值金额不能为负数
        if (recharge.getMoney().compareTo(new BigDecimal(0)) < 0) {
            msg.setFlag(false);
            msg.setMsg("充值金额不能为负数");
            return msg;
        }


        // 获取用户钱包信息
        Wallet wallet = this.getById(recharge.getId());
        if (Objects.isNull(wallet)
                || Objects.isNull(wallet.getId())) {
            msg.setFlag(false);
            msg.setMsg("充值失败，当前用户不存在");
            return msg;
        }


        // 计算当前钱包金额
        BigDecimal newBalance = wallet.getBalance().add(recharge.getMoney());

        // 新增充值记录，插入钱包流水表
        WalletDetails walletDetails = new WalletDetails();
        walletDetails.setType(0);
        walletDetails.setWalletId(recharge.getId());
        walletDetails.setChangeBalance(recharge.getMoney());
        walletDetails.setBalance(newBalance);
        walletDetailsService.save(walletDetails);
        // 更新钱包金额
        wallet.setBalance(newBalance);
        this.updateById(wallet);
        msg.setFlag(true);
        msg.setMsg("充值成功，当前钱包余额为" + newBalance + ", 充值金额为：" + recharge.getMoney());

        return msg;
    }

    @Override
    public Msg details(String id) {
        Msg msg = new Msg();

        // 校验
        if (Objects.isNull(super.getById(id))) {
            msg.setFlag(false);
            msg.setMsg("查询失败，当前用户不存在");
        }
        

        // 获取钱包流水记录
        // 设置查询参数
        LambdaQueryWrapper<WalletDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletDetails::getWalletId,id);
        // 排序规则
        queryWrapper.orderByAsc(WalletDetails::getUpdateTime);
        // 查询
        List<WalletDetails> list = walletDetailsService.list(queryWrapper);

        // 设置返回结果
        // 转换返回值结果 WalletDetails -> walletDetailsShow
        msg.setObj(list.stream().map(e -> {
            WalletDetailsShow walletDetailsShow = new WalletDetailsShow();
            walletDetailsShow.setWalletId(e.getWalletId());
            walletDetailsShow.setBalance(e.getBalance());
            walletDetailsShow.setId(e.getId());
            walletDetailsShow.setType(e.getType());
            walletDetailsShow.setChangeBalance(e.getChangeBalance());
            walletDetailsShow.setUpdateTime(e.getUpdateTime());
            if (Objects.equals(e.getType(), 0)) walletDetailsShow.setDoWhat("存钱");
            if (Objects.equals(e.getType(), 1)) walletDetailsShow.setDoWhat("消费");
            if (Objects.equals(e.getType(), 3)) walletDetailsShow.setDoWhat("退款");
            if (Objects.equals(e.getType(), 4)) walletDetailsShow.setDoWhat("提现");
            return walletDetailsShow;
        }).collect(Collectors.toList()));
        msg.setMsg("查询成功");
        msg.setFlag(true);
        
        return msg;
    }


}
