package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.ScoreInfo;
import com.ruoyi.games.service.ScoreInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games/abnormal")
public class AbnormalController extends BaseController {

    private String prefix = "games/abnormal";

    @Autowired
    private ScoreInfoService scoreInfoService;

    @RequiresPermissions("games:abnormal:view")
    @GetMapping()
    public String abnormal(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate",DateUtils.getMonthLastDay());
        return prefix + "/abnormal";
    }

    @RequiresPermissions("games:abnormal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScoreInfo info) {
        PageDomain pageDomain = buildPage();
        info.setPageSize(pageDomain.getPageSize());
        info.setPageIndex(pageDomain.getPageNum());
        int total = 0;
        Map<String, Object> dataMap = scoreInfoService.queryUserRDScore(info);
        List<ScoreInfo> list = (List<ScoreInfo>) dataMap.get("dataList");
        total = (int) dataMap.get("total");
        return getPageDataTable(list, total);
    }

    @RequiresPermissions("games:abnormal:getscoredetail")
    @GetMapping("/getscoredetail")
    public String getscoredetail(Integer userID,Integer gameID,String startDate,String endDate,String accounts,String nickName,ModelMap mmap) {
        List<ScoreInfo> infoList = scoreInfoService.getScoreDetail(userID, startDate, endDate);
        mmap.put("dataList", infoList);
        mmap.put("userID", userID);
        mmap.put("gameID",gameID);
        mmap.put("times",startDate + "至" + endDate);
        mmap.put("nickName",nickName);
        return prefix + "/scoredetail";
    }

}
