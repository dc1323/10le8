package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.SystemSecurity;
import com.ruoyi.games.domain.UserDistills;
import com.ruoyi.games.mapper.AccountInfoMapper;
import com.ruoyi.games.mapper.RecordAchievementMapper;
import com.ruoyi.games.mapper.SystemSecurityMapper;
import com.ruoyi.games.mapper.UserDistillsMapper;
import com.ruoyi.games.service.FilledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilledServiceImpl implements FilledService {
    private static final Logger log = LoggerFactory.getLogger(FilledServiceImpl.class);

    @Autowired
    private RecordAchievementMapper recordAchievementMapper;

    @Autowired
    private UserDistillsMapper userDistillsMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private SystemSecurityMapper systemSecurityMapper;

    @Override
    public List<RecordAchievement> getShareDetailInfo(RecordAchievement info) {
        return recordAchievementMapper.getShareDetailInfo(info);
    }

    @Override
    public RecordAchievement getShareDetailInfoSum(RecordAchievement info) {
        return recordAchievementMapper.getShareDetailInfoSum(info);
    }

    @Override
    public List<UserDistills> getUserDistills(UserDistills info) {
        return userDistillsMapper.getUserDistills(info);
    }

    @Override
    public UserDistills getUserDistillsSum(UserDistills info) {
        return userDistillsMapper.getUserDistillsSum(info);
    }

    @Override
    public AjaxResult accept(Integer id, Integer userID, int operaType, String reason) {
        Map<String,Object> param = new HashMap<>();
        param.put("userID",userID);
        param.put("distillID",id);
        param.put("memo",reason);
        param.put("operatUserId", ShiroUtils.getUserId().intValue());
        if(operaType == 0){
            userDistillsMapper.noAccept(param);
        }else{
           userDistillsMapper.accept(param);
        }
        String msg = (String)param.get("strErrorDescribe");
        if(StringUtils.isEmpty(msg) || "操作成功".equals(msg)){
            return AjaxResult.success();
        }else{
            return AjaxResult.error(msg);
        }
    }

    @Override
    public AjaxResult distillPay(Integer id, Integer userID, String reason, String money) {
        SysUser user = ShiroUtils.getSysUser();
        Map<String,Object> param = new HashMap<>();
        param.put("userID",userID);
        param.put("distillID",id);
        param.put("memo",reason);
        param.put("operatUserId", user.getUserId().intValue());
        userDistillsMapper.distillPay(param);
        String msg = (String)param.get("strErrorDescribe");
        if(StringUtils.isEmpty(msg) || "操作成功".equals(msg)){
            return AjaxResult.success();
        }else{
            BigDecimal num = new BigDecimal(money);
            String operatingIP = ShiroUtils.getIp();
            AccountInfo accountInfo = accountInfoMapper.selectAccountByUserID(userID);
            String operatingName = "提现";
            String operatingAccounts = user.getUserName();
            String remark = user.getLoginName() + "给" + accountInfo.getAccounts() + "提现" + num.toString() + "元";
            String objectAccounts = accountInfo.getAccounts();
            SystemSecurity systemSecurity = new SystemSecurity();
            systemSecurity.setObjectAccounts(objectAccounts);
            systemSecurity.setOperatingAccounts(operatingAccounts);
            systemSecurity.setOperatingIP(operatingIP);
            systemSecurity.setOperatingName(operatingName);
            systemSecurity.setRemark(remark);
            systemSecurityMapper.insert(systemSecurity);
            return AjaxResult.error(msg);
        }
    }
}
