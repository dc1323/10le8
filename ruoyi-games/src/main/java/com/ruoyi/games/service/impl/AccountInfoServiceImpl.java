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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,Object> selectAccountPage(String strWhere, int pageSize, int pageIndex) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("strWhere",strWhere);
        param.put("pageSize",pageSize);
        param.put("pageIndex",pageIndex);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectAccountPage(param);
        result.put("dataList",accountInfoList);
        result.put("total",param.get("recordCount"));
        return result;
    }
}
