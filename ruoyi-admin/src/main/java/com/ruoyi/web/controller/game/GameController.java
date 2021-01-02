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
import com.ruoyi.games.service.HorseLampService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private HorseLampService horseLampService;
    @Value("${DataBaseName}")
    private String dataBaseName;
    @Value("${DataBaseAddr}")
    private String dataBaseAddr;
    @Value("${ServiceMachine}")
    private String serviceMachine;

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
    @PostMapping(value="/function_set/edit")
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
                cbFreeTime = param.cbCloseAccountTime;
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
                cbFreeTime = param.cbCloseAccountTime;
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

    @RequiresPermissions("games:gameCreate:page")
    @GetMapping("/gameCreate")
    public String gameCreate() {
        return prefix + "/game_create";
    }

    @RequiresPermissions("games:gameCreate:list")
    @PostMapping("/gameCreate/list")
    @ResponseBody
    public TableDataInfo gameCreateList() {
        startPage();
        List<GameRoomInfo> roomInfoList = gameService.getRoomsList();
        for (GameRoomInfo info : roomInfoList) {
            info.setRevenueRatio(info.getRevenueRatio()/10);
//            info.setRevenueRatio(info.getRevenueRatio());
            info.setServerLevel(Integer.parseInt(info.getServerLevel()) >= 10 ? "自定义房间" : "随机匹配房间");
            info.setNullityStatus(Integer.parseInt(info.getNullity()) == 1? "已停止" : "已启动");
        }
        return getDataTable(roomInfoList);
    }

    // @Log(title = "游戏创建", businessType = BusinessType.UPDATE)
    @Log(title = "房间设置", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/stopGame")
    public AjaxResult stopGame(@RequestBody Map<String, String> map) {
        try {
            int nullity = Integer.parseInt(StringUtils.defaultString(map.get("nullity"), "-1"));
            int serverID = Integer.parseInt(StringUtils.defaultString(map.get("serverID"), "-1"));
            if (nullity == -1) {
                return error("操作有误!");
            }

            int count = gameService.updateNullityByKindId(nullity, serverID);
            count = gameService.updateAndroidConfigureByKindId(nullity, serverID);
//            if (count > 0) {
            if (count >= 0) {
                if (nullity == 1) {
                    gameService.stopGameService(serverID);
                } else {
                    gameService.startGameService(serverID);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
        return success("操作成功!");
    }

    @Log(title = "游戏删除", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/delGame")
    public AjaxResult delGame(@RequestBody Map<String, String> map) {
        try {
            int serverID = Integer.parseInt(StringUtils.defaultString(map.get("serverID"), "-1"));
            if (serverID == -1) {
                return error("数据有误!");
            }

            if(gameService.stopGameServiceForBool(serverID)) {
                int count = gameService.deleteGameRoomInfo(serverID);
                count = gameService.deleteAndroidConfigure(serverID);
                if (count > 0) {
                    return success("删除成功!");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("删除失败!");
        }
        return success("删除成功!");
    }

    @GetMapping("/gameCreate/add")
    public String gameCreateAdd(ModelMap map) {
        GameRoomInfo param = new GameRoomInfo();
        List<GameKindItem> gameKindItemList = accountInfoService.getGameKindList();
        map.put("gameKindItemList",gameKindItemList);
        map.put("info", param);
        return prefix + "/game_info_edit";
    }

    @GetMapping("/doEditGame/{id}")
    public String doEditGame(@PathVariable("id") String serverID, ModelMap map) {
        GameRoomInfo info = gameService.getGameRoomInfo(Integer.parseInt(serverID));
        Map<String, Integer> dic = gameService.getRules(info.getCustomRule().substring(0, 32));
        info.setCbFreeTime(dic.get("cbFreeTime"));
        info.setCbBetTime(dic.get("cbBetTime"));
        info.setCbEndTime(dic.get("cbEndTime"));
        info.setCbDWZWin(dic.get("cbDWZWin"));
        info.setCbXWZWin(dic.get("cbXWZWin"));
        info.setCbWZWin(dic.get("cbWZWin"));
        info.setCbDLLost(dic.get("cbDLLost"));
        info.setCbXLLost(dic.get("cbXLLost"));

        String attachFiled = info.getAttachFiled();
        if (attachFiled.indexOf(',') > -1) {
            String arry[] = attachFiled.split(",");
            info.setCbDWZWin2Nine(Integer.parseInt(arry[0]));
            info.setCbWZWin2Eight(Integer.parseInt(arry[1]));
            info.setCbXLLost2Eigbht(Integer.parseInt(arry[2]));
        }

        List<GameKindItem> gameKindItemList = accountInfoService.getGameKindList();
        map.put("gameKindItemList",gameKindItemList);
        map.put("info", info);
        return prefix + "/game_info_edit";
    }

    @Log(title = "游戏编辑", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/editOrAdd")
    public AjaxResult editOrAdd(GameRoomInfo info) {
        String oper = "修改";

        try {
            float revenueRatio = info.getRevenueRatio() * 10;
            if (revenueRatio > 1000 || revenueRatio < 0) {
                return error("抽水比例不能超过100%且不能小于0!");
            }
            info.setRevenueRatio((int)revenueRatio);

            if (info.getCbDWZWin() != info.getCbDWZWin2Nine() * 9) {
                return error("大王炸赢金额与九人每人赔的金额总计不相等!");
            }

            if ((info.getCbXWZWin() + info.getCbWZWin()) != info.getCbXLLost2Eigbht() * 8) {
                return error("小王炸赢与小炸加每八人赔总计金额不相等!");
            }

            if ((info.getCbDLLost() + info.getCbXLLost()) != info.getCbWZWin2Eight() * 8) {
                return error("大雷赔与小雷赔加每八人赢总计金额不相等!");
            }

            String cbArry = info.getCbDWZWin2Nine() + "," + info.getCbXLLost2Eigbht() + "," + info.getCbWZWin2Eight();
            String cmd = info.getCmd();
            List<Game2CaiPiaoParam> caiPiaoDiZhiList = gameService.getGame2CaiPiaoParamList(info.getKindID());
            Game2CaiPiaoParam param = caiPiaoDiZhiList.get(0);

            int cbFreeTime = param.cbCloseAccountTime;
            int cbBetTime = param.cbEnterTime;
            int cbEndTime = param.cbStartTime;

            if (cmd.equals("update") && StringUtils.isEmpty(info.getServerLevel())) {
                GameRoomInfo gameRoomInfo1 = gameService.getGameRoomInfo(info.getServerID());
                info.setServerLevel(gameRoomInfo1.getServerLevel());
            }

            if (info.getCbDWZWin() < 0 || info.getCbXWZWin() < 0 || info.getCbWZWin() < 0
                    || info.getCbDLLost() < 0 || info.getCbXLLost() < 0 || info.getRevenueRatio() < 0
                    || cbFreeTime < 0 || cbBetTime < 0 || cbEndTime < 0) {
                return error("大王炸赢或小王炸赢或小炸赢或大雷输或小雷输或离场时间或游戏时间或结算时间有误!");
            }

            if (info.getMinEnterScore() <= 0) {
                return error("底注有误!");
            }

            int cellscore = (int)Float.parseFloat(info.getCellScore());
            if (cellscore <= 0) {
                return error("底注有误!");
            }
//            if (Integer.parseInt(StringUtils.defaultString(info.getCellScore(), "0")) <= 0) {
//                return error("底注有误!");
//            }

            if ((info.getCbDWZWin() % 9) != 0) {
                return error("大王炸炸赢必须为9的倍数!");
            }

            if (((info.getCbXWZWin() + info.getCbWZWin()) % 8) != 0) {
                return error("小王炸赢和小炸赢之和必须为8的倍数!");
            }

            if (((info.getCbDLLost() + info.getCbXLLost()) % 8) != 0) {
                return error("大雷输和小雷输之和必须为8的倍数!");
            }

            String rule = gameService.getRule(info.getKindID());
            if (StringUtils.isEmpty(rule)) {
                return error("服务规则有误,请联系技术人员!");
            }

            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("cbFreeTime", cbFreeTime);
            map.put("cbBetTime", cbBetTime);
            map.put("cbEndTime", cbEndTime);
            map.put("cbDWZWin", info.getCbDWZWin());
            map.put("cbXWZWin", info.getCbXWZWin());
            map.put("cbWZWin", info.getCbWZWin());
            map.put("cbDLLost", info.getCbDLLost());
            map.put("cbXLLost", info.getCbXLLost());
            String strCustom = gameService.appendString(map);
            strCustom = StringUtils.rightPad(strCustom, 2048, "0");

            info.setServerRule(Integer.parseInt(rule));
            info.setAttachUserRight(info.getKindID());
            info.setServerPort(gameService.getServerPort(info));
            info.setDataBaseName(dataBaseName);
            info.setDataBaseAddr(dataBaseAddr);
            info.setCustomRule(strCustom);
            info.setServiceMachine(serviceMachine);
            info.setAttachFiled(cbArry);

            if (!cmd.equals("add") && !cmd.equals("update")) {
                return error("操作有误");
            }

            if (info.getCmd().equals("add")) {
                oper = "新增";
            } else {
                oper = "修改";
            }

            if (cmd.equals("add")) {
                Map<String, Object> result = gameService.createRoom(info);
                if(result.size() > 0) {
                    int serverID = Integer.parseInt(result.get("ServerID").toString());
                    if (serverID > 0) {
                        //gameService.startGameService(serverID);
                    }
                }
            } else {
                int count = gameService.updateGameRoom(info);
                if (count > 0) {
                    return success(oper + "成功!");
                } else {
                    return error(oper + "失败!");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error(oper + "失败!");
        }
        return success(oper + "成功!");
    }


    /**
     * @description: 跑马灯
     * @author liuyang
     * @date 2020/12/9 14:44
     */
    @PostMapping("/horselamplist")
    @ResponseBody
    public TableDataInfo horseLampList(HorseLamp info) {
        startPage();
        List<HorseLamp> list = horseLampService.getHorseLampList(info);
        return getDataTable(list);
    }

    @PostMapping("/horselamp/remove")
    @ResponseBody
    public AjaxResult removeHorseLamp(String[] ids){
        horseLampService.deleteHorseLamp(ids);
        return AjaxResult.success();
    }

    @GetMapping("/horselamp")
    public String horselamp(ModelMap mmap) {
        return prefix + "/horse_lamp_add";
    }

    @PostMapping("/horselamp/save")
    @ResponseBody
    public AjaxResult saveHorseLamp(HorseLamp horseLamp){
        horseLamp.setStartTime(DateUtils.getNowDate());
        horseLamp.setEndTime(DateUtils.getNowDate());
        horseLampService.saveHorseLamp(horseLamp);
        return AjaxResult.success();
    }

}
