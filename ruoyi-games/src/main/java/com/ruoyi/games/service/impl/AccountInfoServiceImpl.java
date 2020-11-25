package com.ruoyi.games.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.mapper.AccountInfoMapper;
import com.ruoyi.games.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    private static final Logger log = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoMapper accountInfoMapper;


    @Override
    @DataSource(value = DataSourceType.ACCOUNT)
    public List<AccountInfo> selectAccountList(AccountInfo accountInfo) {
        return accountInfoMapper.selectAccountList(accountInfo);
    }
}
