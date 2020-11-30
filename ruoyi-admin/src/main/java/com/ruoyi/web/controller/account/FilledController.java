package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.UserDistills;
import com.ruoyi.games.service.FilledService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @description:
     * @author liuyang
     * @date 2020/11/30 21:27
     */
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

    @RequiresPermissions("games:filled:distillwith")
    @GetMapping("/distillwith")
    public String distillWith(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/distillwith";
    }

    @RequiresPermissions("games:filled:distillpay")
    @PostMapping("/distillpay")
    @ResponseBody
    public AjaxResult distillPay(Integer id, Integer userID, String reason, String money) {
        return filledService.distillPay(id, userID, reason, money);
    }

}
