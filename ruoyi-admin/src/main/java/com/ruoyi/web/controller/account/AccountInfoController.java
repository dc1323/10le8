package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.service.AccountInfoService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/games/account")
public class AccountInfoController extends BaseController {

    private String prefix = "games/account";

    @Autowired
    private AccountInfoService accountInfoService;

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


}
