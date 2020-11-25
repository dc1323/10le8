package com.ruoyi.games.service.impl;

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
    public Map<String, Object> selectAccountPage(String strWhere, int pageSize, int pageIndex) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("strWhere", strWhere);
        param.put("pageSize", pageSize);
        param.put("pageIndex", pageIndex);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectAccountPage(param);
        if (null != accountInfoList && accountInfoList.size() > 0) {
            for (AccountInfo accountInfo : accountInfoList) {
                Map<String, Object> canMap = new HashMap<>(2);
                canMap.put("userID", accountInfo.getUserID());
                canMap.put("gameID", accountInfo.getGameID());
                String commission = accountInfoMapper.getCanCachOut(canMap);
                accountInfo.setCommission(commission);
            }
        }
        result.put("dataList", accountInfoList);
        result.put("total", param.get("recordCount"));
        return result;
    }

    @Override
    public Map<String, Object> selectAccount(String strWhere, int pageSize, int pageIndex) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("strWhere", strWhere);
        param.put("pageSize", pageSize);
        param.put("pageIndex", pageIndex);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectAccountPage(param);
        result.put("dataList", accountInfoList);
        result.put("total", param.get("recordCount"));
        return result;
    }

    @Override
    public AccountInfo selectAccountByUserID(Integer userID) {
        return accountInfoMapper.selectAccountByUserID(userID);
    }
}
