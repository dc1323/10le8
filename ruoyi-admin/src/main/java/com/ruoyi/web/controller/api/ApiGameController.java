package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.service.AccountInfoService;
import com.ruoyi.games.service.OnLineOrderService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/6 21:54
 */
@Api("游戏管理")
@Controller
@RequestMapping("/api/game")
public class ApiGameController extends BaseController {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private OnLineOrderService onLineOrderService;

    @ApiOperation("绑定上级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "playingGame", value = "上级游戏ID", required = true, dataType = "Integer")
    })
    @PostMapping("/bindsuperior")
    public AjaxResult bindSuperior(Integer userID, Integer gameID, Integer playingGame) {
        return accountInfoService.bindPlayingGame(userID, gameID, playingGame);
    }

    @ApiOperation("用户申请提款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "distillMoney", value = "提款金额", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String")
    })
    @PostMapping("/userdistillali")
    public AjaxResult userDistillAli(Integer userID, Integer gameID, Double distillMoney, String bankCardNumber) {
        return accountInfoService.userDistillAli(userID, gameID, distillMoney, bankCardNumber);
    }

    @ApiOperation("绑定银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bankUserName", value = "户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bankTypeName", value = "银行类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phoneCode", value = "验证码", required = true, dataType = "String")
    })
    @PostMapping("/bindbank")
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
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer")
    })
    @PostMapping("/cachout")
    public AjaxResult cachOut(Integer userID, Integer gameID) {
        return accountInfoService.cachOut(userID, gameID);
    }

    @ApiOperation("获取充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer")
    })
    @GetMapping("/getsharedetaillist")
    public AjaxResult getShareDetailList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        return onLineOrderService.getShareDetailList(userID, gameID, pageIndex, pageSize);
    }

    @ApiOperation("获取提现记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户标识", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gameID", value = "游戏ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer")
    })
    @GetMapping("/getuserdistilllist")
    public AjaxResult getUserDistillList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        return onLineOrderService.getUserDistillList(userID, gameID, pageIndex, pageSize);
    }

}
