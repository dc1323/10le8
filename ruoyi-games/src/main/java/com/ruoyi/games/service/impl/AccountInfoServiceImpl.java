package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.mapper.*;
import com.ruoyi.games.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class AccountInfoServiceImpl implements AccountInfoService {
    private static final Logger log = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Value("${ServerUrl}")
    private String serverUrl;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private SystemSecurityMapper systemSecurityMapper;

    @Autowired
    private GameKindItemMapper gameKindItemMapper;

    @Autowired
    private SystemFunctionStatusInfoMapper systemFunctionStatusInfoMapper;

    @Autowired
    private RebateScaleInfoMapper rebateScaleInfoMapper;

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
        map.put("strUserIDList", userID.toString());
        map.put("dwAddGold", num);
        map.put("dwMasterID", user.getUserId());
        map.put("strReason", "后台赠送");
        map.put("strClientIP", operatingIP);
        map.put("typeID", 0);
        try {
            List<OrderInfo> orderInfos = accountInfoMapper.grantTreasure(map);
            if (null != orderInfos && orderInfos.size() > 0) {
                OrderInfo orderInfo = orderInfos.get(0);
                String param = "{\"userid\":" + orderInfo.getUserid() + ", \"payamount\":" + orderInfo.getPayamount() + "}";
                HttpUtils.sendPost(serverUrl, param);
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
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.error("充值存储过程出错，错误为：", e);
            return 0;
        }
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
    public List<GameKindItem> getGameKindList() {
        List<GameKindItem> list = gameKindItemMapper.getGameList();
        GameKindItem temp1 = new GameKindItem();
        temp1.setKindID(0);
        temp1.setKindName("请选择游戏类型");
        list.add(0, temp1);
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

    @Override
    public SystemFunctionStatusInfo getInfoByStatusName(String statusName) {
        SystemFunctionStatusInfo info = new SystemFunctionStatusInfo();
        info.setStatusName(statusName);
        List<SystemFunctionStatusInfo> list = systemFunctionStatusInfoMapper.getFunctionStatusInfoList(info);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<RebateScaleInfo> getRebateScaleInfo() {
        return rebateScaleInfoMapper.getRebateScaleInfo();
    }

    @Override
    public AjaxResult rebateScaleInfo(RebateInfo rebateInfo) {
        if (null == rebateInfo) {
            return AjaxResult.error("参数不能为空!");
        }
        List<RebateScaleInfo> infos = rebateInfo.getInfos();
        if (null != infos && infos.size() > 0) {
            for (RebateScaleInfo info : infos) {
                BigDecimal temp = info.getRebateScale();
                info.setRebateScale(temp.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
                rebateScaleInfoMapper.update(info);
            }
        }
        systemFunctionStatusInfoMapper.update(rebateInfo.getInfo());
        return AjaxResult.success();
    }
}
