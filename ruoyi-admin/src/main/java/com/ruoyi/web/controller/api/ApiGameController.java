package com.ruoyi.web.controller.api;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description:
 * @date 2020/12/6 21:54
 */
@Api("游戏管理")
@RestController
@RequestMapping("/api/game")
public class ApiGameController extends BaseController {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private OnLineOrderService onLineOrderService;

    @Autowired
    private AgentExtensionService agentExtensionService;

    @Autowired
    private MembersService membersService;

    @Autowired
    private FilledService filledService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RotationImageService rotationImageService;

    @Autowired
    private HorseLampService horseLampService;

    @ApiOperation("绑定上级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "playingGame", value = "上级游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping("/bindsuperior")
    @ResponseBody
    public AjaxResult bindSuperior(@RequestParam Integer userID, @RequestParam Integer gameID, @RequestParam Integer playingGame) {
        return accountInfoService.bindPlayingGame(userID, gameID, playingGame);
    }

    @ApiOperation("用户申请提款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "distillMoney", value = "提款金额", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/userdistillali")
    @ResponseBody
    public AjaxResult userDistillAli(@RequestParam  Integer userID, @RequestParam Integer gameID,
                                     @RequestParam Double distillMoney, @RequestParam String bankCardNumber) {
        return accountInfoService.userDistillAli(userID, gameID, distillMoney, bankCardNumber);
    }

    @ApiOperation("绑定银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bankUserName", value = "户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bankTypeName", value = "银行类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneCode", value = "验证码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/bindbank")
    @ResponseBody
    public AjaxResult BindBank(@RequestParam Integer userID, @RequestParam Integer gameID, @RequestParam String bankName,
                               @RequestParam String bankCardNumber, @RequestParam String bankUserName,
                               @RequestParam String bankTypeName, @RequestParam String phoneNumber, @RequestParam String phoneCode) {
        if (StringUtils.isEmpty(bankCardNumber) || StringUtils.isEmpty(bankUserName) || StringUtils.isEmpty(bankTypeName)) {
            return AjaxResult.error("操作失败,参数不合法!");
        }
        return accountInfoService.bindBank(userID, gameID, bankName, bankCardNumber, bankUserName, bankTypeName, phoneNumber, phoneCode);
    }

    @ApiOperation("领取佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping("/cachout")
    @ResponseBody
    public AjaxResult cachOut(@RequestParam Integer userID,@RequestParam Integer gameID) {
        return accountInfoService.cachOut(userID, gameID);
    }

    @ApiOperation("获取充值记录")
    @GetMapping("/getsharedetaillist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    public AjaxResult getShareDetailList(Integer userID,
                                         Integer gameID,
                                         Integer pageIndex,
                                         Integer pageSize) {
        return onLineOrderService.getShareDetailList(userID, gameID, pageIndex, pageSize);
    }

    @ApiOperation("获取提现记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getuserdistilllist")
    public AjaxResult getUserDistillList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        return onLineOrderService.getUserDistillList(userID, gameID, pageIndex, pageSize);
    }

    @ApiOperation("获取推广信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getagentextension")
    public AjaxResult getAgentExtension(Integer userID, Integer gameID) {
        return agentExtensionService.agentExtension(userID, gameID);
    }

    @ApiOperation("获取团队成员")
    @GetMapping("/getmemberslist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sGameID", value = "下级游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    public AjaxResult getMembersList(Integer userID,
                                     Integer gameID,
                                     Integer sGameID,
                                     Integer pageIndex,
                                     Integer pageSize) {
        Map<String, Object> dataMap = membersService.getMembersList(userID, gameID, sGameID, pageIndex, pageSize);
        List<Members> list = (List<Members>) dataMap.get("dataList");
        int total = (int) dataMap.get("total");
        return AjaxResult.success("获取成功", getPageDataTable(list, total));
    }

    @ApiOperation("获取提款时间")
    @GetMapping("/getDistillTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    public AjaxResult getDistillTime(Integer userID,
                                     Integer gameID) {
        SystemFunctionStatusInfo info = accountInfoService.getInfoByStatusName("DistillsTime");
        if (null == info || StringUtils.isEmpty(info.getStatusValue())) {
            return AjaxResult.error("获取失败");
        }

        String[] arryTime = info.getStatusValue().split("-");
        String minTime = arryTime[0];
        String maxTime = arryTime[1];

        Map<String, String> map = Maps.newHashMap();
        map.put("minTime", minTime);
        map.put("maxTime", maxTime);
        return AjaxResult.success("获取成功", map);
    }

    @ApiOperation("修改昵称")
    @GetMapping("/updateNickName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "nickName", value = "昵称", required = true, dataType = "String", paramType = "query"),
    })
    public AjaxResult UpdateNickName(Integer userID,
                                     Integer gameID,
                                     String nickName) {
        int count = accountInfoService.updateNickNameByUserID(nickName, userID);
        if (count <= 0) {
            return AjaxResult.error("修改昵称失败");
        }
        return AjaxResult.success("修改成功");
    }

    @ApiOperation("获取充值客服")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getrechargecs")
    public AjaxResult getRechargeCS(Integer userID, Integer gameID) {
        List<Map<String, Object>> result = null;
        List<Customer> list = filledService.getCustomers();
        if (null != list && list.size() > 0) {
            result = new ArrayList<>();
            for (Customer info : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("type", info.getTypeID());
                map.put("name", info.getCustomerValue());
                map.put("title", info.getTypeName());
                result.add(map);
            }
        }
        return AjaxResult.success("获取成功", result);
    }

    @ApiOperation("获取领取记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getrecordachievementlist")
    public AjaxResult getShareDetailInfoList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        return filledService.getShareDetailInfoList(userID, gameID, pageIndex, pageSize);
    }

    @ApiOperation("注册账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneCode", value = "短信码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "machineID", value = "机器码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "nickName", value = "昵称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "playingGame", value = "上级游戏ID(默认为-1)", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/registerAccount")
    public AjaxResult registerAccount(String phoneNumber, String phoneCode, String passWord, String machineID,
                                      String nickName, int playingGame, HttpServletRequest request) {
        return accountInfoService.registerAccount(phoneNumber, phoneCode, passWord,
                machineID, nickName, playingGame, request);
    }

    @ApiOperation("发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "typeID", value = "类型(1:注册,2:绑定银行卡),默认类型为1", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/sendSms")
    public AjaxResult sendSms(String phoneNumber, int userID, int typeID) {
        if (phoneNumber.length() != 11) {
            return AjaxResult.error("手机号码有误");
        }
        return accountInfoService.sendMsg(phoneNumber, userID, typeID);
    }

    @ApiOperation("获取功能设置,默认获取游戏规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "statusName", value = "关键字", dataType = "String", paramType = "query")
    })
    @GetMapping("/getfuncset")
    public AjaxResult getFuncSet(Integer userID, Integer gameID, String statusName) {
        if (StringUtils.isEmpty(statusName)) {
            statusName = "GameRules";
        }
        GameFunctionSet info = gameService.getFunctionSetByKey(statusName);
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取游戏规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getgamerules")
    public AjaxResult getGameRules(Integer userID, Integer gameID) {
        GameFunctionSet info = gameService.getFunctionSetByKey("GameRules");
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取第三方客服链接")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getthirdservice")
    public AjaxResult getThirdService(Integer userID, Integer gameID) {
        GameFunctionSet info = gameService.getFunctionSetByKey("ThirdService");
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getnotice")
    public AjaxResult getNotice(Integer userID, Integer gameID) {
        GameFunctionSet info = gameService.getFunctionSetByKey("Notice");
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getactive")
    public AjaxResult getActive(Integer userID, Integer gameID) {
        GameFunctionSet info = gameService.getFunctionSetByKey("Active");
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取银行列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getbanklist")
    public AjaxResult getBankList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        return accountInfoService.getBankList(userID, gameID, pageIndex, pageSize);
    }

    @ApiOperation("获取轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "topNumber", value = "轮播图片的张数", dataType = "int", paramType = "query")
    })
    @GetMapping("/getrotitionlist")
    public AjaxResult getRotitionList(Integer userID, Integer gameID, Integer topNumber) {
        if (null == topNumber || Objects.equals(0, topNumber)) {
            topNumber = 6;
        }
        List<RotationImage> list = rotationImageService.getRotationImageList(topNumber);
        return AjaxResult.success("获取成功", list);
    }

    @ApiOperation("获取战绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "matchType", value = "匹配类型: 0,系统 1,自定义", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "kindID", value = "游戏类型", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "int", paramType = "query")
    })
    @GetMapping("/getgamerecordbyuser")
    public AjaxResult getGameRecordByUser(Integer userID, Integer gameID, int matchType, Integer kindID, int month) {
        GameRecord gameRecord = gameService.getGameRecordList(userID, kindID, month, matchType);
        return AjaxResult.success("获取成功", gameRecord);
    }

    @ApiOperation("获取跑马灯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "topNumber", value = "跑马灯数量(默认6条)", dataType = "int", paramType = "query")
    })
    @GetMapping("/gethorselamplist")
    public AjaxResult getHorseLampList(Integer userID, Integer gameID, Integer topNumber) {
        if (null == topNumber || Objects.equals(0, topNumber)) {
            topNumber = 6;
        }
        List<HorseLamp> list = horseLampService.getHorseLampTop(topNumber);
        return AjaxResult.success("获取成功", list);
    }

    @ApiOperation("获取用户银行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getgamescoreinfobyuserid")
    public AjaxResult getGameScoreInfoByUserID(Integer userID, Integer gameID) {
        AccountInfo info = accountInfoService.selectAccountByUserID(userID);
        if (null == info) {
            return AjaxResult.success();
        } else {
            return AjaxResult.success("获取成功", info);
        }
    }

    @ApiOperation("获取返利说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getrebatescaleremark")
    public AjaxResult getRebateScaleRemark(Integer userID, Integer gameID) {
        GameFunctionSet info = gameService.getFunctionSetByKey("RevenueRemark");
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getStatusValue());
            return AjaxResult.success("获取成功", map);
        }
    }

    @ApiOperation("获取所有下级返利")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/getSubUserCommission")
    public AjaxResult getSubUserCommission(Integer gameID) {
        List<CommissionInfo> list = accountInfoService.getSubUserCommission(gameID);
        if (null == list) {
            return AjaxResult.success();
        } else {
            return AjaxResult.success("获取成功", list);
        }
    }

    @ApiOperation("获取游戏版本号")
    @GetMapping("/getgameversion")
    public AjaxResult getGameVersion() {
        GameItem info = gameService.getGameItem();
        if (null == info) {
            return AjaxResult.success();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("values", info.getServerVersion());
            return AjaxResult.success("获取成功", map);
        }
    }

}
