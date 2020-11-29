package com.ruoyi.web.controller.account;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.ScoreInfo;
import com.ruoyi.games.service.AccountInfoService;
import com.ruoyi.games.service.ScoreInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games/account")
public class AccountInfoController extends BaseController {

    private String prefix = "games/account";

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private ScoreInfoService scoreInfoService;

    @RequiresPermissions("games:account:view")
    @GetMapping()
    public String infos() {
        return prefix + "/infos";
    }

    @RequiresPermissions("games:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountInfo info) {
        PageDomain pageDomain = buildPage();
        StringBuilder stringBuilder = new StringBuilder(" 1 = 1 ");

        if (StringUtils.isNotEmpty(info.getKeyWord())) {
            stringBuilder.append(" AND (UserID like '%" + info.getKeyWord() + "%' OR GameID LIKE '%" + info.getKeyWord() + "%' OR Accounts LIKE '%" + info.getKeyWord() + "%' )");
        }
        int total = 0;
        Map<String, Object> dataMap = accountInfoService.selectAccountPage(stringBuilder.toString(), pageDomain.getPageSize(), pageDomain.getPageNum());
        List<AccountInfo> list = (List<AccountInfo>) dataMap.get("dataList");
        total = (int) dataMap.get("total");
        return getPageDataTable(list, total);
    }

    /**
     * 查看上级
     *
     * @param userId
     * @param mmap
     * @return
     */
    @RequiresPermissions("games:account:prev")
    @GetMapping("/{userId}/prev")
    public String prev(@PathVariable("userId") Integer userId, String keyWord, ModelMap mmap) {
        AccountInfo accountInfo = accountInfoService.selectAccountByUserID(userId);
        mmap.put("accountInfo", accountInfo);
        return prefix + "/prev";
    }

    @PostMapping("/prevlist")
    @ResponseBody
    public TableDataInfo prevlist(AccountInfo info) {
        PageDomain pageDomain = buildPage();
        StringBuilder stringBuilder = new StringBuilder(" 1 = 1 ");
        stringBuilder.append(" AND GameID = " + info.getPlayingGame());
        if (StringUtils.isNotEmpty(info.getKeyWord())) {
            stringBuilder.append(" AND (UserID like '%" + info.getKeyWord() + "%' OR GameID LIKE '%" + info.getKeyWord() + "%' OR Accounts LIKE '%" + info.getKeyWord() + "%' )");
        }
        int total = 0;
        Map<String, Object> dataMap = accountInfoService.selectAccount(stringBuilder.toString(), pageDomain.getPageSize(), pageDomain.getPageNum());
        List<AccountInfo> list = (List<AccountInfo>) dataMap.get("dataList");
        total = (int) dataMap.get("total");
        return getPageDataTable(list, total);
    }

    /**
     * 查看下级
     *
     * @param userId
     * @param mmap
     * @return
     */
    @RequiresPermissions("games:account:next")
    @GetMapping("/{userId}/next")
    public String next(@PathVariable("userId") Integer userId, ModelMap mmap) {
        AccountInfo accountInfo = accountInfoService.selectAccountByUserID(userId);
        mmap.put("accountInfo", accountInfo);
        return prefix + "/next";
    }
    @PostMapping("/nextlist")
    @ResponseBody
    public TableDataInfo nextlist(AccountInfo info) {
        PageDomain pageDomain = buildPage();
        StringBuilder stringBuilder = new StringBuilder(" 1 = 1 ");
        stringBuilder.append(" AND PlayingGame = " + info.getGameID());
        if (StringUtils.isNotEmpty(info.getKeyWord())) {
            stringBuilder.append(" AND (UserID like '%" + info.getKeyWord() + "%' OR GameID LIKE '%" + info.getKeyWord() + "%' OR Accounts LIKE '%" + info.getKeyWord() + "%' )");
        }
        int total = 0;
        Map<String, Object> dataMap = accountInfoService.selectAccount(stringBuilder.toString(), pageDomain.getPageSize(), pageDomain.getPageNum());
        List<AccountInfo> list = (List<AccountInfo>) dataMap.get("dataList");
        total = (int) dataMap.get("total");
        return getPageDataTable(list, total);
    }

    /**
     * 冻结
     * @param userIDs
     * @return
     */
    @RequiresPermissions("games:account:freeze")
    @Log(title = "冻结账号", businessType = BusinessType.UPDATE)
    @PostMapping("/freeze")
    @ResponseBody
    public AjaxResult freeze(String userIDs) {
        try {
            return toAjax(accountInfoService.freezeAccount(userIDs,0));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 解冻
     * @param userIDs
     * @return
     */
    @RequiresPermissions("games:account:unfreeze")
    @Log(title = "解冻账号", businessType = BusinessType.UPDATE)
    @PostMapping("/unfreeze")
    @ResponseBody
    public AjaxResult unfreeze(String userIDs) {
        try {
            return toAjax(accountInfoService.freezeAccount(userIDs,1));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 跳转到游戏充值界面
     * @param userId
     * @param mmap
     * @return
     */
    @RequiresPermissions("games:account:grant")
    @GetMapping("/{userId}/grant")
    public String grantView(@PathVariable("userId") Integer userId, ModelMap mmap) {
        AccountInfo accountInfo = accountInfoService.selectAccountByUserID(userId);
        mmap.put("accountInfo", accountInfo);
        return prefix + "/grant";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("games:account:grant")
    @Log(title = "游戏充值", businessType = BusinessType.UPDATE)
    @PostMapping("/grant")
    @ResponseBody
    public AjaxResult grant(Integer userID, String gold) {
        return toAjax(accountInfoService.grantTreasure(userID, gold));
    }

    /**
     * 胜负查看
     * @return
     */
    @RequiresPermissions("games:score:view")
    @GetMapping("/score/winvsloss")
    public String winVsLoss(){
        return prefix + "/winvsloss";
    }

    /**
     * 玩家异常
     * @return
     */
    @RequiresPermissions("games:score:list")
    @GetMapping("/score/specialuser")
    public String specialUser(){
        return prefix + "/specialuser";
    }

    /**
     * 胜负查看数据
     * @param info
     * @return
     */
    @RequiresPermissions("games:score:list")
    @PostMapping("/score/list")
    @ResponseBody
    public TableDataInfo list(ScoreInfo info) {
        PageDomain pageDomain = buildPage();
        info.setPageSize(pageDomain.getPageSize());
        info.setPageIndex(pageDomain.getPageNum());
        int total = 0;
        Map<String, Object> dataMap = scoreInfoService.selectScoreInfoList(info);
        List<AccountInfo> list = (List<AccountInfo>) dataMap.get("dataList");
        total = (int) dataMap.get("total");
        return getPageDataTable(list, total);
    }

    /**
     * 设置特殊账号
     * @param userIDs
     * @return
     */
    @RequiresPermissions("games:account:setteshu")
    @Log(title = "设置特殊账号", businessType = BusinessType.UPDATE)
    @PostMapping("/score/setteshu")
    @ResponseBody
    public AjaxResult setTeShu(String userIDs) {
        try {
            return toAjax(accountInfoService.setTeShu(userIDs));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 取消特殊账号
     * @param userIDs
     * @return
     */
    @RequiresPermissions("games:account:qxteshu")
    @Log(title = "设置特殊账号", businessType = BusinessType.UPDATE)
    @PostMapping("/score/qxteshu")
    @ResponseBody
    public AjaxResult qxTeShu(String userIDs) {
        try {
            return toAjax(accountInfoService.qxTeShu(userIDs));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 跳转到卡线玩家管理
     * @param mmap
     * @return
     */
    @RequiresPermissions("games:account:onlineview")
    @GetMapping("/online")
    public String online(ModelMap mmap){
        List<GameKindItem> gameKindItemList = accountInfoService.getGameList();
        mmap.put("gameKindItemList",gameKindItemList);
        return prefix + "/online";
    }

    @RequiresPermissions("games:account:onlinelist")
    @PostMapping("/onlinelist")
    @ResponseBody
    public TableDataInfo onlineList(AccountInfo info) {
        startPage();
        List<AccountInfo> list = accountInfoService.getOnlieList(info);
        return getDataTable(list);
    }

    /**
     * 清除卡线
     * @param userIDs
     * @return
     */
    @RequiresPermissions("games:account:unlock")
    @Log(title = "设置特殊账号", businessType = BusinessType.UPDATE)
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlock(String userIDs) {
        try {
            return toAjax(accountInfoService.unlock(userIDs));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

}
