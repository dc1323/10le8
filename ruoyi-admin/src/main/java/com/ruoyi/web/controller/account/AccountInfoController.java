package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.service.AccountInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

        if(StringUtils.isNotEmpty(info.getKeyWord()))
        {
            stringBuilder.append(" AND (UserID like '%"+info.getKeyWord()+"%' OR GameID LIKE '%"+info.getKeyWord()+"%' OR Accounts LIKE '%"+info.getKeyWord()+"%' )");
        }
        int total = 0;
        Map<String,Object> dataMap = accountInfoService.selectAccountPage(stringBuilder.toString(),pageDomain.getPageSize(),pageDomain.getPageNum());
        List<AccountInfo> list = (List<AccountInfo>)dataMap.get("dataList");
        total = (int)dataMap.get("total");
        return getPageDataTable(list,total);
    }


}
