package com.ruoyi.web.controller.game;

import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.service.AccountInfoService;
import com.ruoyi.games.service.GameService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 游戏管理
 */
@Controller
@RequestMapping("/games/game")
public class GameController extends BaseController {
    private String prefix = "games/game";

    @Autowired
    private GameService gameService;
    @Autowired
    private AccountInfoService accountInfoService;

    @RequiresPermissions("games:game:view")
    @GetMapping("/functionSet")
    public String functionSet(ModelMap mmap) {
        List<GameFunctionSet> list = gameService.queryFunctionSet();
        Map<String, String> map = Maps.newHashMap();
        for (GameFunctionSet functionSet : list) {
            if (functionSet.getStatusName().equals("DistillsTime")) {
                String value = functionSet.getStatusValue();
                String[] values = value.split("-");
                map.put("DistillsTime0", values[0]);
                map.put("DistillsTime1", values[1]);
            } else {
                map.put(functionSet.getStatusName(), functionSet.getStatusValue());
            }
        }
        mmap.addAttribute("myMap", map);
        return prefix + "/function_set";
    }

    @RequiresPermissions("games:game:edit")
    @Log(title = "游戏管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/edit")
    public AjaxResult editSave(@RequestBody List<Map<String, String>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return toAjax(false);
        }

        boolean flag = false;
        for (Map<String, String> map : list) {
            flag = gameService.updateFunctionSet(map.get("name"), map.get("value"));
        }
        return toAjax(flag);
    }

    @RequiresPermissions("games:lotteryManage:view")
    @GetMapping("/lotteryManage")
    public String lotteryManage(ModelMap mmap) {
        List<GameKindItem> gameKindItemList = accountInfoService.getGameKindList();
        mmap.put("gameKindItemList",gameKindItemList);
        return prefix + "/lottery_manage";
    }

    @RequiresPermissions("games:lotteryManage:list")
    @PostMapping("/lotteryManage/list")
    @ResponseBody
    public TableDataInfo lotteryManageList(LotteryManage manage) {
        startPage();
        List<LotteryManage> list = gameService.queryLotteryManage(manage);
        List<CaiPiaoDiZhi> cpList = gameService.getCaiPiaoDiZhi(-1);
        for (LotteryManage lotteryManage : list) {
            int roundCount = getRoundByExpect(cpList, lotteryManage.getCode(), lotteryManage.getExpect(),
                    lotteryManage.getGroupId());
            lotteryManage.setRoundCount("第" + roundCount + "场");
            if (lotteryManage.getOpenCode().length() == 0) {
                lotteryManage.setStatus("未开奖");
            } else {
                lotteryManage.setStatus("已开奖");
            }
        }
        return getDataTable(list);
    }

    @RequiresPermissions("games:lotteryManage:edit")
    @Log(title = "游戏管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/updateOpenNumber")
    public AjaxResult updateOpenNumber(@RequestBody Map<String, String> map) {
        int id = Integer.parseInt(StringUtils.defaultString(map.get("id"), "-1"));
        String openCode = map.get("openCode");
        if (id == -1) {
            return error("开奖有误!");
        }

        if (!openCode.contains(",")) {
            return error("开奖码不包含逗号!");
        }

        String openCodes[] = openCode.split(",");
        if (openCodes.length != 5) {
            return error("开奖有误!");
        }

        int count = gameService.updateCaiPiaoJieGuo(openCode, id);
        if (count > 0) {
            return success("开奖成功");
        }
        return error("开奖失败!");
    }

    @GetMapping("/lotteryManageDetail/{code}/{expect}")
    public String lotteryManageDetail(@PathVariable("code") String code,
                                      @PathVariable("expect") String expect, ModelMap mmap) {
        mmap.put("code", code);
        mmap.put("expect", expect);

        Map<String, String> map = gameService.getRecordDrawInfoByCodeAndExpect(code, expect);
        mmap.put("map", map);
        return prefix + "/lottery_manage_detail";
    }

    public int getRoundByExpect(List<CaiPiaoDiZhi> cpList, String code, String expect, int groupID) {
        int nRoundCount = 0;
        String strRoundCount = expect.substring(expect.length() - 3, expect.length());
        //判断百位是否为0
        if (strRoundCount.substring(0, 1).equals("0")) {
            //判断十位是否为0
            if (strRoundCount.substring(1, strRoundCount.length() - 1).equals("0")) {
                //取最后一位
                nRoundCount = Integer.parseInt(strRoundCount.substring(strRoundCount.length() - 1, strRoundCount.length()));
            } else {
                nRoundCount = Integer.parseInt(strRoundCount.substring(strRoundCount.length() - 2, strRoundCount.length()));
            }
        } else {
            nRoundCount = Integer.parseInt(strRoundCount);
        }

        int cpCount = 0;
        for (CaiPiaoDiZhi diZhi : cpList) {
            if (diZhi.getGroupId() == groupID) {
                cpCount++;
            }
        }

        int SortID = 0;
        for (CaiPiaoDiZhi diZhi : cpList) {
            if (diZhi.getCode().equals(code)) {
                SortID = diZhi.getSortId();
                break;
            }
        }
        nRoundCount = (nRoundCount - 1) * cpCount + SortID;
        return nRoundCount;
    }

    @RequiresPermissions("games:lotteryTime:list")
    @GetMapping("/lotteryTime")
    public String lotteryTime(ModelMap map) {
        List<GameKindItem> list = accountInfoService.getGameKindList();
        map.put("gameKindItemList", list);

        String startTime = "08:00:00";
        List<CaiPiaoDiZhi> caiPiaoDiZhiList = gameService.getCaiPiaoDiZhi(123);
        if (CollectionUtils.isNotEmpty(caiPiaoDiZhiList)) {
            startTime = caiPiaoDiZhiList.get(0).getStartTime();
        }

       startTime = getTime(startTime);

        int cbEndTime123 = 0;
        int cbEndTime124 = 0;
        List<Game2CaiPiaoParam> piaoParamList = gameService.getGame2CaiPiaoParamList(-1);
        for (Game2CaiPiaoParam param : piaoParamList) {
            if (param.kindID == 123) {
                cbEndTime123 = param.totalEndTime;
                break;
            }
        }

        for (Game2CaiPiaoParam param : piaoParamList) {
            if (param.kindID == 124) {
                cbEndTime124 = param.totalEndTime;
                break;
            }
        }

        map.put("startTime", startTime);
        map.put("cbEndTime123", cbEndTime123);
        map.put("cbEndTime124", cbEndTime124);
        return prefix + "/lottery_time";
    }

    public String getTime(String startTime) {
        Date d = DateUtils.dateTime("HH:mm:ss", startTime);
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(d);
        nowTime.add(Calendar.MINUTE, 5);
        startTime = DateUtils.parseDateToStr("HH:mm:ss", nowTime.getTime());
        return startTime;
    }

    @RequiresPermissions("games:lotteryTime:list")
    @PostMapping("/lotteryTime/list")
    @ResponseBody
    public TableDataInfo lotteryTimeList(LotteryManage manage) {
        startPage();
        List<LotteryManage> list = gameService.queryLotteryManage(manage);
        List<CaiPiaoDiZhi> cpList = gameService.getCaiPiaoDiZhi(-1);
        for (LotteryManage lotteryManage : list) {
            int roundCount = getRoundByExpect(cpList, lotteryManage.getCode(), lotteryManage.getExpect(),
                    lotteryManage.getGroupId());
            lotteryManage.setRoundCount("第" + roundCount + "场");
        }
        return getDataTable(list);
    }

    @RequiresPermissions("games:lotteryTime:caiPiaoTime")
    @Log(title = "彩票时间表", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/setCaiPiaoTimeByKindID")
    public AjaxResult setCaiPiaoTimeByKindID(@RequestBody Map<String, String> map) {
        try {
            int kindId = Integer.parseInt(StringUtils.defaultString(map.get("kindId"), "-1"));
            String startTime = map.get("startTime");
            List<CaiPiaoDiZhi> caiPiaoDiZhiList = gameService.getCaiPiaoDiZhi(kindId);
            if (CollectionUtils.isNotEmpty(caiPiaoDiZhiList)) {
                for (CaiPiaoDiZhi diZhi : caiPiaoDiZhiList) {
                    gameService.updateCaiPiaoDiZhi(startTime, diZhi.getId());
                    startTime = getTime(startTime);
                }
            }

            Map<String, String> param = Maps.newHashMap();
            param.put("strErrorDescribe", "");
            gameService.getInitFalseCaiPiaoJieGuo(param);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return error("操作成功!");
    }

    @RequiresPermissions("games:lotteryTime:endTimeTime")
    @Log(title = "彩票时间表", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/setEndTimeTime123")
    public AjaxResult setEndTimeTime123(@RequestBody Map<String, String> map) {
        try {
            int kindId = Integer.parseInt(StringUtils.defaultString(map.get("kindId"), "-1"));
            String startTime = map.get("startTime");
            List<CaiPiaoDiZhi> caiPiaoDiZhiList = gameService.getCaiPiaoDiZhi(kindId);
            if (CollectionUtils.isNotEmpty(caiPiaoDiZhiList)) {
                for (CaiPiaoDiZhi diZhi : caiPiaoDiZhiList) {
                    gameService.updateCaiPiaoDiZhi(startTime, diZhi.getId());
                    startTime = getTime(startTime);
                }
            }

            Map<String, String> param = Maps.newHashMap();
            param.put("strErrorDescribe", "");
            gameService.getInitFalseCaiPiaoJieGuo(param);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return error("操作成功!");
    }
}
