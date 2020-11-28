package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;
import com.ruoyi.games.service.AccountInfoService;
import com.ruoyi.games.service.RecordAchievementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/games/record")
public class RecordAchievementController extends BaseController {

    private String prefix = "games/record";

    @Autowired
    private RecordAchievementService recordAchievementService;

    @Autowired
    private AccountInfoService accountInfoService;

    @RequiresPermissions("games:record:view")
    @GetMapping()
    public String abnormal(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        return prefix + "/achievement";
    }

    @RequiresPermissions("games:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecordAchievement info) {
        startPage();
        List<RecordAchievement> list = recordAchievementService.getRecordAchievementInfo(info);
        return getDataTable(list);
    }

    @RequiresPermissions("games:record:querydetail")
    @GetMapping("/querydetail")
    public String queryDetail(String orderID, Integer userID, ModelMap mmap) {
        mmap.put("orderID", orderID);
        mmap.put("userID", userID);
        return prefix + "/achievementdetail";
    }

    @RequiresPermissions("games:record:querydetail")
    @PostMapping("/querydetaillist")
    @ResponseBody
    public TableDataInfo queryDetailList(RecordAchievementDetail info) {
        startPage();
        List<RecordAchievementDetail> list = recordAchievementService.queryRecordAchievementDetail(info);
        if (null != list && list.size() > 0) {
            AccountInfo accountInfo = accountInfoService.selectAccountByUserID(info.getUserID());
            for (RecordAchievementDetail detail : list) {
                detail.setGameID(accountInfo.getGameID());
                detail.setNickName(accountInfo.getNickName());
            }
        }
        return getDataTable(list);
    }


}
