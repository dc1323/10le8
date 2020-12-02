package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 游戏账号配置
 */
@Repository
public interface AccountInfoMapper {

    List<AccountInfo> selectAccountPage(Map<String, Object> param);

    String getCanCachOut(Map<String, Object> param);

    AccountInfo selectAccountByUserID(Integer userID);

    void freezeAccount(List<String> userIDs);

    void unFreezeAccount(List<String> userIDs);

    List<OrderInfo> grantTreasure(Map<String, Object> param);

    void setTeShu(List<String> userIDs);

    void qxTeShu(List<String> userIDs);

    List<AccountInfo> getOnlieList(AccountInfo info);

    void deleteGameScoreLocker();

    void deleteGameScoreLockerByUsers(List<String> userIDs);

}
