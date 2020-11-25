package com.ruoyi.games.service;

import com.ruoyi.games.domain.AccountInfo;

import java.util.List;

public interface AccountInfoService {

    /**
     * 根据条件分页查询账号信息
     * @param accountInfo
     * @return
     */
    List<AccountInfo> selectAccountList(AccountInfo accountInfo);

}
