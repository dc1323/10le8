package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.mapper.*;
import com.ruoyi.games.service.FilledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    @Autowired
    private CustomerMapper customerMapper;

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
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("distillID", id);
        param.put("memo", reason);
        param.put("operatUserId", ShiroUtils.getUserId().intValue());
        if (operaType == 0) {
            userDistillsMapper.noAccept(param);
        } else {
            userDistillsMapper.accept(param);
        }
        String msg = (String) param.get("strErrorDescribe");
        if (StringUtils.isEmpty(msg) || "操作成功".equals(msg)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error(msg);
        }
    }

    @Override
    public AjaxResult distillPay(Integer id, Integer userID, String reason, String money) {
        SysUser user = ShiroUtils.getSysUser();
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("distillID", id);
        param.put("memo", reason);
        param.put("operatUserId", user.getUserId().intValue());
        userDistillsMapper.distillPay(param);
        String msg = (String) param.get("strErrorDescribe");
        if (StringUtils.isEmpty(msg) || "操作成功".equals(msg)) {
            return AjaxResult.success();
        } else {
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

    @Override
    public List<Customer> getCustomerList(Customer customer) {
        return customerMapper.getCustomerList(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerMapper.getCustomers();
    }

    @Override
    public List<Customer> getCustomerType() {
        return customerMapper.getCustomerType();
    }

    @Override
    public Customer getCustomerInfoById(Integer id) {
        return customerMapper.getCustomerInfoById(id);
    }

    @Override
    public AjaxResult editCustomer(Customer customer) {
        if (null == customer) {
            return AjaxResult.error("参数不能为空");
        }
        if (StringUtils.isEmpty(customer.getCustomerValue())) {
            return AjaxResult.error("客服账号不能为空");
        }
        if (customer.getTypeID() == 0) {
            return AjaxResult.error("客服类型不能为空");
        }
        customerMapper.updateCustomer(customer);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getShareDetailInfoList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        int index = 0;
        if(null == pageIndex){
            pageIndex = 0;
        }
        if(null == pageSize){
            pageSize = 0;
        }
        if(pageIndex > 0){
            index = (pageIndex -1)*pageSize;
        }
        int total = recordAchievementMapper.getShareDetailInfoCount(userID);
        if(total > 0){
            List<ShareDetailInfo> list = recordAchievementMapper.getShareDetailInfoList(userID,index,pageSize);
            Map<String,Object> result = new HashMap<>();
            result.put("total",total);
            result.put("data",list);
            return AjaxResult.success("获取成功",result);
        }
        return AjaxResult.success();
    }
}
