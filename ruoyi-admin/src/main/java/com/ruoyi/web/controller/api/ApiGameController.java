package com.ruoyi.web.controller.api;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.Members;
import com.ruoyi.games.domain.SystemFunctionStatusInfo;
import com.ruoyi.games.service.AccountInfoService;
import com.ruoyi.games.service.AgentExtensionService;
import com.ruoyi.games.service.MembersService;
import com.ruoyi.games.service.OnLineOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ApiOperation("绑定上级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "playingGame", value = "上级游戏ID", required = true, dataType = "int", paramType = "body")
    })
    @PostMapping("/bindsuperior")
    @ResponseBody
    public AjaxResult bindSuperior(Integer userID, Integer gameID, Integer playingGame) {
        return accountInfoService.bindPlayingGame(userID, gameID, playingGame);
    }

    @ApiOperation("用户申请提款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "distillMoney", value = "提款金额", required = true, dataType = "Double", paramType = "body"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/userdistillali")
    @ResponseBody
    public AjaxResult userDistillAli(Integer userID, Integer gameID, Double distillMoney, String bankCardNumber) {
        return accountInfoService.userDistillAli(userID, gameID, distillMoney, bankCardNumber);
    }

    @ApiOperation("绑定银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "bankUserName", value = "户名", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "bankTypeName", value = "银行类型", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "phoneCode", value = "验证码", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/bindbank")
    @ResponseBody
    public AjaxResult BindBank(Integer userID, Integer gameID, String bankName,
                               String bankCardNumber, String bankUserName,
                               String bankTypeName, String phoneNumber, String phoneCode) {
        if (StringUtils.isEmpty(bankCardNumber) || StringUtils.isEmpty(bankUserName) || StringUtils.isEmpty(bankTypeName)) {
            return AjaxResult.error("操作失败,参数不合法!");
        }
        return accountInfoService.bindBank(userID, gameID, bankName, bankCardNumber, bankUserName, bankTypeName, phoneNumber, phoneCode);
    }

    @ApiOperation("领取佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "int", paramType = "body"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "int", paramType = "body")
    })
    @PostMapping("/cachout")
    @ResponseBody
    public AjaxResult cachOut(Integer userID, Integer gameID) {
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

}
