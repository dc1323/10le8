package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.mapper.*;
import com.ruoyi.games.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Time;
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

    @Autowired
    private PhoneSmsMapper phoneSmsMapper;

    @Autowired
    private AccountsInfoBankMapper accountsInfoBankMapper;

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
                //HttpUtils.sendPost(serverUrl, param);
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

    @Override
    public AjaxResult bindPlayingGame(Integer userID, Integer gameID, Integer playingGame) {
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("gameID", gameID);
        param.put("playingGame", playingGame);
        accountInfoMapper.bindPlayingGame(param);
        String result = (String) param.get("strErr");
        if (StringUtils.isEmpty(result)) {
            return AjaxResult.error(result);
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult userDistillAli(Integer userID, Integer gameID, Double distillMoney, String bankCardNumber) {
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("gameID", gameID);
        param.put("distillType", 1);
        param.put("money", new BigDecimal(distillMoney));
        param.put("bankCardNumber", bankCardNumber);
        param.put("memo", "支付宝提款申请中");
        param.put("strClientIP", ShiroUtils.getIp());
        accountInfoMapper.userDistillAli(param);
        String result = (String) param.get("strErr");
        if ("申请成功".equals(result)) {
            return AjaxResult.success(result);
        }
        return AjaxResult.error(result);
    }

    @Override
    public AjaxResult registerAccount(String phoneNumber, String phoneCode, String passWord,
                                      String machineID, String nickName, int playingGame,
                                      HttpServletRequest request) {
        PhoneSms phoneSms = phoneSmsMapper.getPhoneSmsByPhoneNumber(phoneNumber, phoneCode);
        if (null == phoneSms || phoneSms.getId().intValue() == 0) {
            return AjaxResult.error("请发送手机验证码");
        }

        if (playingGame != -1) {
            AccountInfo info = accountInfoMapper.getAccountInfoByGameID(playingGame);
            if (null == info || info.getUserID() == null || info.getUserID().intValue() == 0) {
                return AjaxResult.error("上级用户不存在");
            }
        }

        Date endTime = new Date();
        Date startTime = phoneSms.getInsertTime();
        long timeSpan = endTime.getTime() - startTime.getTime();
        if (timeSpan > 300) {
            return AjaxResult.error("验证码有效期为5分钟,请重新发送手机验证码");
        }

        if (phoneSms.getIsUse() == 1) {
            return AjaxResult.error("短信码已被验证过,请重新发送手机验证码");
        }

        //注册账号
        Map<String, String> dic = new HashMap<>();
        String strAccounts = phoneNumber;
        String strNickName = nickName;
        String strLogonPass = Md5Utils.hash(passWord);

        dic.put("strAccounts", strAccounts);
        dic.put("strNickName", strNickName);
        dic.put("strLogonPass", strLogonPass);
        dic.put("wFaceID", "0");
        dic.put("cbGender", "0");
        dic.put("strPassPortID", "");
        dic.put("strCompellation", "");
        dic.put("strClientIP", IpUtils.getIpAddr(request));
        dic.put("strMachineID", machineID);
        dic.put("strRegisterMobile", phoneNumber);
        dic.put("nPlayingGame", playingGame + "");

        Map<String, String> message = registerAccountByMessage(dic);
        if (message.size() > 0) {
            return AjaxResult.error("注册失败", message);
        }
        return AjaxResult.success("注册成功");
    }

    @Override
    public Map<String, String> registerAccountByMessage(Map<String, String> param) {
        return accountInfoMapper.registerAccountByMessage(param);
    }

    @Override
    public AjaxResult sendMsg(String phoneNumber, int userID, int typeID) {
        PhoneSms sms = new PhoneSms();
        sms.setPhoneNumber(phoneNumber);
        sms.setUserID(userID);
        sms.setTypeID((short)typeID);
        sms.setIsUse((short)0);
        sms.setInsertTime(new Date());;
        String phoneCode = CodeUtils.randomNum(100000, 999999) + "";
        sms.setSmsCode(phoneCode);
        int count = phoneSmsMapper.addPhoneSMS(sms);
        if (count > 0) {
            List<SystemFunctionStatusInfo> list = systemFunctionStatusInfoMapper.getFunctionStatusInfoList(new SystemFunctionStatusInfo());
            Map<String, String> dic = new HashMap<String, String>();
            int i = 0;
            int j = 0;
            String accessKeyID = "";
            String accessKeySecret = "";
            for (SystemFunctionStatusInfo info : list) {
                if (info.getStatusName().equals("SiteCode")) {
                    if (i == 0){
                        accessKeyID = info.getStatusValue();
                    }
                    i++;
                }

                if (info.getStatusName().equals("SiteSecret")) {
                    if (j == 0){
                        accessKeySecret = info.getStatusValue();
                    }
                    j++;
                }
            }

            dic.put("AccessKeyID", accessKeyID);
            dic.put("AccessKeySecret", accessKeySecret);
            dic.put("PhoneNumber", sms.getPhoneNumber());
            dic.put("PhoneCode", sms.getSmsCode());

            /*
            var response = SMSHelper.Current.SendSms(dic);
            if (!response.Code.ToUpper().Equals("OK"))
            {
                ajv.SetFail("发送失败");

                return ajv;
            }*/
            return AjaxResult.success("发送成功");
        }
        return AjaxResult.error("发送失败");
    }

    @Override
    public AjaxResult bindBank(Integer userID, Integer gameID, String bankName,
                               String bankCardNumber, String bankUserName,
                               String bankTypeName, String phoneNumber, String phoneCode) {
        PhoneSms phoneSms = phoneSmsMapper.getPhoneSms(userID, phoneNumber, phoneCode, (short) 2);
        if (null == phoneSms || Objects.equals(0, phoneSms.getId())) {
            return AjaxResult.error("验证码有误");
        }
        Date endDate = new Date();
        Date startDate = phoneSms.getInsertTime();
        long diffDate = endDate.getTime() - startDate.getTime();
        if (diffDate > 300 * 1000) {
            return AjaxResult.error("验证码有效期为5分钟,请重新发送手机验证码");
        }
        AccountsInfoBank info = new AccountsInfoBank();
        info.setBankCardNumber(bankCardNumber);
        info.setBankUserName(bankUserName);
        List<AccountsInfoBank> banks = accountsInfoBankMapper.getUserBank(info);
        if (null != banks && banks.size() > 0) {
            return AjaxResult.error("此银行卡已经被绑定");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("gameID", gameID);
        param.put("bankName", bankName);
        param.put("bankCardNumber", bankCardNumber);
        param.put("bankUserName", bankUserName);
        param.put("bankTypeName", bankTypeName);
        accountInfoMapper.bindBank(param);
        String result = (String) param.get("strErr");
        if (!result.contains("恭喜您")) {
            return AjaxResult.error("银行卡绑定失败，请重试!!");
        }
        accountInfoMapper.updateAccountCompellation(bankUserName, userID);
        return AjaxResult.success("银行卡绑定成功!");
    }

    @Override
    public AjaxResult cachOut(Integer userID, Integer gameID) {
        Map<String,Object> param = new HashMap<>();
        param.put("userID",userID);
        param.put("gameID",gameID);
        accountInfoMapper.cachOut(param);
        String result = (String)param.get("strErr");
        if(StringUtils.isNotEmpty(result)){
            return AjaxResult.error(result);
        }
        String data = accountInfoMapper.getCanCachOut(param);
        return AjaxResult.success("操作成功",data);
    }

    @Override
    public int updateNickNameByUserID(String nickName, int userID) {
        return accountInfoMapper.updateNickNameByUserID(nickName, userID);
    }
}
