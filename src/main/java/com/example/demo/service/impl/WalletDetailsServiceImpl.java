package com.example.demo.service.impl;

import com.example.demo.entity.WalletDetails;
import com.example.demo.mapper.WalletDetailsMapper;
import com.example.demo.service.IWalletDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class WalletDetailsServiceImpl extends ServiceImpl<WalletDetailsMapper, WalletDetails> implements IWalletDetailsService {

}
