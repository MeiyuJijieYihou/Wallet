package com.answer.service.impl;

import com.answer.mapper.WalletDetailsMapper;
import com.answer.service.IWalletDetailsService;
import com.answer.entity.WalletDetails;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class WalletDetailsServiceImpl extends ServiceImpl<WalletDetailsMapper, WalletDetails> implements IWalletDetailsService {

}
