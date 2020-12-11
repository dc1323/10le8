package com.ruoyi.games.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.games.domain.Customer;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.UserDistills;

import java.util.List;

public interface FilledService {

    List<RecordAchievement> getShareDetailInfo(RecordAchievement info);

    RecordAchievement getShareDetailInfoSum(RecordAchievement info);

    List<UserDistills> getUserDistills(UserDistills info);

    UserDistills getUserDistillsSum(UserDistills info);

    AjaxResult accept(Integer id, Integer userID, int operaType, String reason);

    AjaxResult distillPay(Integer id, Integer userID, String reason,String money);

    List<Customer> getCustomerList(Customer customer);

    List<Customer> getCustomers();

    List<Customer> getCustomerType();

    Customer getCustomerInfoById(Integer id);

    AjaxResult editCustomer(Customer customer);

}
