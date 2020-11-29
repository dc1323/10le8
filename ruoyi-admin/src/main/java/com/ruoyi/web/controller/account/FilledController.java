package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.RecordAchievement;
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
        mmap.put("endDate",DateUtils.getMonthLastDay());
        return prefix + "/shareinfo";
    }

    @RequiresPermissions("games:filled:shareinfolist")
    @PostMapping("/shareinfolist")
    @ResponseBody
    public TableDataInfo shareInfoList(RecordAchievement info) {
        startPage();
        List<RecordAchievement> list = filledService.getShareDetailInfo(info);
        RecordAchievement sumInfo = filledService.getShareDetailInfoSum(info);
        for(RecordAchievement recordAchievement : list){
            recordAchievement.setTotalPayAmount(sumInfo.getPayAmount());
        }
        return getDataTable(list);
    }


}
