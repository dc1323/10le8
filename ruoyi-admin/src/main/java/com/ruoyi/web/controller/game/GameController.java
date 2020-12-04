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
            int roundCount = gameService.getRoundByExpect(cpList, lotteryManage.getCode(), lotteryManage.getExpect(),
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
            int roundCount = gameService.getRoundByExpect(cpList, lotteryManage.getCode(), lotteryManage.getExpect(),
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
        return success("操作成功!");
    }

    @RequiresPermissions("games:lotteryTime:endTimeTime123")
    @Log(title = "彩票时间表", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/setEndTimeTime123")
    public AjaxResult setEndTimeTime123(@RequestBody Map<String, String> map) {
        try {
            int kindId = Integer.parseInt(StringUtils.defaultString(map.get("kindId"), "-1"));
            int totalEndTime = Integer.parseInt(StringUtils.defaultString(map.get("totalEndTime"), "0"));

            if (totalEndTime == 0) {
                return error("设置截止时间失败");
            }

            int totalTime = 0;
            int cbFreeTime = 0;
            kindId = 123;
            List<Game2CaiPiaoParam> caiPiaoDiZhiList = gameService.getGame2CaiPiaoParamList(kindId);
            if (CollectionUtils.isNotEmpty(caiPiaoDiZhiList)) {
                Game2CaiPiaoParam param = caiPiaoDiZhiList.get(0);
                totalTime = param.totalTime;
                cbFreeTime = param.cbFreeTime;
            }

            int cbEndTime = totalTime - totalEndTime;
            if (cbEndTime <= 0) {
                return error("设置截止时间失败,截止时间不能大于总时间:" + totalTime + "秒");
            }

            if ((cbEndTime + totalEndTime) > totalTime) {
                return error("设置截止时间失败,官方五分钟时间设定超过规定的300秒");
            }

            int cbBetTime = totalEndTime - cbFreeTime;
            if (cbBetTime <= 0) {
                return error("设置截止时间失败,截止时间不能大于总时间:" + cbFreeTime + "秒");
            }

            Game2CaiPiaoParam param = new Game2CaiPiaoParam();
            param.setKindID(kindId);
            param.setTotalEndTime(totalEndTime);
            param.setCbBetTime(cbBetTime);
            param.setCbEndTime(cbEndTime);
            int count = gameService.updateGame2CaiPiaoParam(param);
            if (count > 0 && gameService.updateGameRoomCBEndTime(kindId, false)) {
                Map<String, String> paramMap = Maps.newHashMap();
                paramMap.put("strErrorDescribe", "");
                gameService.getInitFalseCaiPiaoJieGuo(paramMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return success("操作成功!");
    }

    @RequiresPermissions("games:lotteryTime:endTimeTime124")
    @Log(title = "彩票时间表", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/setEndTimeTime124")
    public AjaxResult setEndTimeTime124(@RequestBody Map<String, String> map) {
        try {
            int kindId = Integer.parseInt(StringUtils.defaultString(map.get("kindId"), "-1"));
            int totalEndTime = Integer.parseInt(StringUtils.defaultString(map.get("totalEndTime"), "0"));
            if (totalEndTime == 0) {
                return error("设置截止时间失败");
            }

            kindId = 124;
            int totalTime = 0;
            int cbFreeTime = 0;
            List<Game2CaiPiaoParam> caiPiaoDiZhiList = gameService.getGame2CaiPiaoParamList(kindId);
            if (CollectionUtils.isNotEmpty(caiPiaoDiZhiList)) {
                Game2CaiPiaoParam param = caiPiaoDiZhiList.get(0);
                totalTime = param.totalTime;
                cbFreeTime = param.cbFreeTime;
            }

            int cbEndTime = totalTime - totalEndTime;
            if (cbEndTime <= 0) {
                return error("设置截止时间失败,截止时间不能大于总时间:" + totalTime + "秒");
            }

            if ((cbEndTime + totalEndTime) > totalTime) {
                return error("设置截止时间失败,官方五分钟时间设定超过规定的300秒");
            }

            int cbBetTime = totalEndTime - cbFreeTime;
            if (cbBetTime <= 0) {
                return error("设置截止时间失败,截止时间不能小于离场时间:" + cbFreeTime + "秒");
            }

            Game2CaiPiaoParam param = new Game2CaiPiaoParam();
            param.setKindID(kindId);
            param.setTotalEndTime(totalEndTime);
            param.setCbBetTime(cbBetTime);
            param.setCbEndTime(cbEndTime);
            int count = gameService.updateGame2CaiPiaoParam(param);
            if (count > 0 && gameService.updateGameRoomCBEndTime(kindId, false)) {
                Map<String, String> paramMap = Maps.newHashMap();
                paramMap.put("strErrorDescribe", "");
                gameService.getInitFalseCaiPiaoJieGuo(paramMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return success("操作成功!");
    }

    @RequiresPermissions("games:gameParams:page")
    @GetMapping("/gameParams")
    public String gameParams() {
        return prefix + "/game_params";
    }

    @RequiresPermissions("games:gameParams:list")
    @PostMapping("/gameParams/list")
    @ResponseBody
    public TableDataInfo gameParamsList(Game2CaiPiaoParam param) {
        startPage();
        List<Game2CaiPiaoParam> piaoParamList = gameService.queryGame2CaiPiaoParamList(param);
        return getDataTable(piaoParamList);
    }

    @GetMapping("/gameParams/{id}")
    public String gameParamsDetail(@PathVariable("id") String id, ModelMap map) {
        Game2CaiPiaoParam param = new Game2CaiPiaoParam();
        param.setId(Integer.parseInt(id));

        List<Game2CaiPiaoParam> piaoParamList = gameService.queryGame2CaiPiaoParamList(param);
        map.put("param", piaoParamList.get(0));
        return prefix + "/game_params_edit";
    }

    @RequiresPermissions("games:gameParams:edit")
    @Log(title = "游戏参数", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/updateParam")
    public AjaxResult updateParam(@RequestBody Map<String, String> map) {
        try {
            int id = Integer.parseInt(StringUtils.defaultString(map.get("id"), "-1"));
            int cbFreeTime = Integer.parseInt(StringUtils.defaultString(map.get("cbFreeTime"), "0"));
            if (cbFreeTime <= 0 || cbFreeTime > 255) {
                return error("操作失败,离场时间有误");
            }

            Game2CaiPiaoParam param = new Game2CaiPiaoParam();
            param.setId(id);
            List<Game2CaiPiaoParam> params = gameService.queryGame2CaiPiaoParamList(param);
            if (CollectionUtils.isEmpty(params)) {
                return error("请选择游戏类型");
            }

            param = params.get(0);
            //重新设置下注时间
            int cbBetTime = param.totalEndTime - cbFreeTime;
            if (cbBetTime <= 0 || cbBetTime > 255) {
                return error("操作失败,游戏时间有误");
            }

            List<GameRoomInfo> gameRoomInfos = gameService.getGameRoomInfoByKindId(param.kindID);
            if (CollectionUtils.isEmpty(gameRoomInfos)) {
                return error("操作失败,规则不存在");
            }

            param.setCbFreeTime(cbFreeTime);
            int count = gameService.updateParamTime(param);
            if(count >0 && gameService.updateGameRoomCBEndTime(param.kindID, true)) {
                return error("操作成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return success("操作失败!");
    }
}
