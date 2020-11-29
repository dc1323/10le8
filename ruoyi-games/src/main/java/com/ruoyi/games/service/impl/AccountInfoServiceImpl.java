package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.SystemSecurity;
import com.ruoyi.games.mapper.AccountInfoMapper;
import com.ruoyi.games.mapper.GameKindItemMapper;
import com.ruoyi.games.mapper.SystemSecurityMapper;
import com.ruoyi.games.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    private static final Logger log = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private SystemSecurityMapper systemSecurityMapper;

    @Autowired
    private GameKindItemMapper gameKindItemMapper;

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

    @Override
    public int freezeAccount(String userIDs, int operType) {
        if (StringUtils.isEmpty(userIDs)) {
            return 1;
        } else {
            String[] userIds = userIDs.split(",");
            List<String> userIDList = Arrays.asList(userIds);
            switch (operType) {
                case 0:
                    accountInfoMapper.freezeAccount(userIDList);
                    break;
                case 1:
                    accountInfoMapper.unFreezeAccount(userIDList);
                    break;
            }
            return 1;
        }
    }

    @Override
    public int grantTreasure(Integer userID, String gold) {
        SysUser user = ShiroUtils.getSysUser();
        BigDecimal num = new BigDecimal(gold);
        String operatingIP = ShiroUtils.getIp();
        Map<String, Object> map = new HashMap<>();
        map.put("dwTypeId", 1);
        map.put("strUserIDList", userID);
        map.put("dwAddGold", num);
        map.put("dwMasterID", 18);
        map.put("strReason", "后台赠送");
        map.put("strClientIP", operatingIP);
        int result = accountInfoMapper.grantTreasure(map);
        if (result == 0) {
            AccountInfo accountInfo = accountInfoMapper.selectAccountByUserID(userID);
            String operatingName = "充值";
            String operatingAccounts = user.getUserName();
            String remark = user.getLoginName() + "给" + accountInfo.getAccounts() + "充值" + num.toString() + "元";
            String objectAccounts = accountInfo.getAccounts();
            SystemSecurity systemSecurity = new SystemSecurity();
            systemSecurity.setObjectAccounts(objectAccounts);
            systemSecurity.setOperatingAccounts(operatingAccounts);
            systemSecurity.setOperatingIP(operatingIP);
            systemSecurity.setOperatingName(operatingName);
            systemSecurity.setRemark(remark);
            systemSecurityMapper.insert(systemSecurity);
            return 1;
        }
        return 0;
    }

    @Override
    public int setTeShu(String userIDs) {
        if (StringUtils.isEmpty(userIDs)) {
            return 1;
        } else {
            String[] userIds = userIDs.split(",");
            List<String> userIDList = Arrays.asList(userIds);
            try {
                accountInfoMapper.setTeShu(userIDList);
                return 1;
            } catch (Exception e) {
                log.error("设置特殊账号出错:", e);
                return 0;
            }
        }
    }

    @Override
    public int qxTeShu(String userIDs) {
        if (StringUtils.isEmpty(userIDs)) {
            return 1;
        } else {
            String[] userIds = userIDs.split(",");
            List<String> userIDList = Arrays.asList(userIds);
            try {
                accountInfoMapper.qxTeShu(userIDList);
                return 1;
            } catch (Exception e) {
                log.error("设置特殊账号出错:", e);
                return 0;
            }
        }
    }

    @Override
    public List<AccountInfo> getOnlieList(AccountInfo info) {
        return accountInfoMapper.getOnlieList(info);
    }

    @Override
    public List<GameKindItem> getGameList() {
        List<GameKindItem> list = gameKindItemMapper.getGameList();
        GameKindItem temp1 = new GameKindItem();
        temp1.setKindID(-1);
        temp1.setKindName("全部");
        list.add(0, temp1);
        GameKindItem temp2 = new GameKindItem();
        temp2.setKindID(-2);
        temp2.setKindName("游戏");
        list.add(1, temp2);
        GameKindItem temp3 = new GameKindItem();
        temp3.setKindID(0);
        temp3.setKindName("大厅");
        list.add(2, temp3);
        return list;
    }

    @Override
    public int unlock(String userIDs) {
        accountInfoMapper.deleteGameScoreLocker();
        if (StringUtils.isEmpty(userIDs)) {
            return 1;
        } else {
            String[] userIds = userIDs.split(",");
            List<String> userIDList = Arrays.asList(userIds);
            try {
                accountInfoMapper.deleteGameScoreLockerByUsers(userIDList);
                return 1;
            } catch (Exception e) {
                log.error("清除卡线出错:", e);
                return 0;
            }
        }
    }
}
