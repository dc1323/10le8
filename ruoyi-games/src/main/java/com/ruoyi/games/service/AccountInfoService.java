package com.ruoyi.games.service;

import com.ruoyi.games.domain.AccountInfo;

import java.util.List;
import java.util.Map;

public interface AccountInfoService {

    /**
     * 根据条件分页查询账号信息
     * @param accountInfo
     * @return
     */
    List<AccountInfo> selectAccountList(AccountInfo accountInfo);

    /**
     * 根据条件分页查询账号信息
     * @param strWhere 查询条件
     * @param pageSize 一页多少条
     * @param pageIndex 第几页
     * @return
     */
    Map<String,Object> selectAccountPage(String strWhere, int pageSize, int pageIndex);

}
