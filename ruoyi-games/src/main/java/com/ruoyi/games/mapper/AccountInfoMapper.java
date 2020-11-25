package com.ruoyi.games.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.games.domain.AccountInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游戏账号配置
 */
@Repository
@DataSource(value = DataSourceType.ACCOUNT)
public interface AccountInfoMapper {

    /**
     * 根据条件分页查询账号信息
     * @param accountInfo
     * @return
     */
     List<AccountInfo> selectAccountList(AccountInfo accountInfo);

}
