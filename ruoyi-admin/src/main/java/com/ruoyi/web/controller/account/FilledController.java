package com.ruoyi.web.controller.account;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.Customer;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.UserDistills;
import com.ruoyi.games.service.FilledService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 银行管理
 */
@Controller
@RequestMapping("/games/filled")
public class FilledController extends BaseController {

    private String prefix = "games/filled";

    @Autowired
    private FilledService filledService;

    /**
     * @description: 跳转到充值记录界面
     * @author liuyang
     * @date 2020/12/1 15:07
     */
    @RequiresPermissions("games:filled:shareinfo")
    @GetMapping()
    public String shareInfo(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/shareinfo";
    }

    @RequiresPermissions("games:filled:shareinfolist")
    @PostMapping("/shareinfolist")
    @ResponseBody
    public TableDataInfo shareInfoList(RecordAchievement info) {
        startPage();
        List<RecordAchievement> list = filledService.getShareDetailInfo(info);
        RecordAchievement sumInfo = filledService.getShareDetailInfoSum(info);
        for (RecordAchievement recordAchievement : list) {
            recordAchievement.setTotalPayAmount(sumInfo.getPayAmount());
        }
        return getDataTable(list);
    }

    /**
     * @description: 跳转到提现申请界面
     * @author liuyang
     * @date 2020/12/1 15:06
     */
    @RequiresPermissions("games:filled:userdistills")
    @GetMapping("/userdistills")
    public String userDistills(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/userdistills";
    }

    @RequiresPermissions("games:filled:userdistillslist")
    @PostMapping("/userdistillslist")
    @ResponseBody
    public TableDataInfo userDistillsList(UserDistills info) {
        startPage();
        List<UserDistills> list = filledService.getUserDistills(info);
        UserDistills sumInfo = filledService.getUserDistillsSum(info);
        for (UserDistills userDistills : list) {
            userDistills.setPayAmount(sumInfo.getPayAmount());
            userDistills.setPayAmountCount(sumInfo.getPayAmountCount());
        }
        return getDataTable(list);
    }

    /**
     * @description: 处理提现申请
     * @author liuyang
     * @date 2020/11/30 21:27
     */
    @Log(title = "提现审核", businessType = BusinessType.UPDATE)
    @RequiresPermissions("games:filled:accept")
    @PostMapping("/accept")
    @ResponseBody
    public AjaxResult accept(Integer id, Integer userID, int operaType, String reason) {
        if (operaType == 0 && StringUtils.isEmpty(reason)) {
            return AjaxResult.error("未填写拒绝提款原因!");
        } else {
            return filledService.accept(id, userID, operaType, reason);
        }
    }

    /**
     * @description: 跳转到提现处理页面
     * @author liuyang
     * @date 2020/12/1 15:05
     */
    @RequiresPermissions("games:filled:distillwith")
    @GetMapping("/distillwith")
    public String distillWith(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/distillwith";
    }

    @Log(title = "提现处理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("games:filled:distillpay")
    @PostMapping("/distillpay")
    @ResponseBody
    public AjaxResult distillPay(Integer id, Integer userID, String reason, String money) {
        return filledService.distillPay(id, userID, reason, money);
    }

    /**
     * @description: 跳转到提现记录页面
     * @author liuyang
     * @date 2020/12/1 15:05
     */
    @RequiresPermissions("games:filled:distilllist")
    @GetMapping("/distilllist")
    public String distillList(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/distilllist";
    }

    /**
     * @description: 跳转到提现记录页面
     * @author liuyang
     * @date 2020/12/1 15:05
     */
    @RequiresPermissions("games:filled:customers")
    @GetMapping("/customers")
    public String customers(ModelMap mmap) {
        List<Customer> typeList = filledService.getCustomerType();
        mmap.put("typeList", typeList);
        return prefix + "/customers";
    }

    @RequiresPermissions("games:filled:customerlist")
    @PostMapping("/customerlist")
    @ResponseBody
    public TableDataInfo customerList(Customer info) {
        startPage();
        List<Customer> list = filledService.getCustomerList(info);
        return getDataTable(list);
    }

    @RequiresPermissions("games:filled:customer")
    @GetMapping("/customer/{id}")
    public String customer(@PathVariable("id") Integer id, ModelMap mmap) {
        List<Customer> typeList = filledService.getCustomerType();
        Customer customer = filledService.getCustomerInfoById(id);
        mmap.put("typeList", typeList);
        mmap.put("customer", customer);
        return prefix + "/customer";
    }

    @Log(title = "修改客服信息", businessType = BusinessType.UPDATE)
    @RequiresPermissions("games:filled:customer")
    @PostMapping("/customer/edit")
    @ResponseBody
    public AjaxResult editCustomer(Customer customer) {
        return filledService.editCustomer(customer);
    }

}
