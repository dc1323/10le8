package com.ruoyi.web.controller.account;

import com.ruoyi.common.core.controller.BaseController;
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
        startPage();
        if(null == info){
            info = new AccountInfo();
            info.setPlayingGame("-1");
        }else{
            if(StringUtils.isEmpty(info.getPlayingGame())){
                info.setPlayingGame("-1");
            }
        }
        List<AccountInfo> list = accountInfoService.selectAccountList(info);
        return getDataTable(list);
    }


}
