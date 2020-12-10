package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.SystemSecurity;
import com.ruoyi.games.service.SystemSecurityService;
import com.ruoyi.system.domain.SysOperLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ruoyi
 * @description:
 * @date 2020/12/10 20:45
 */
@Controller
@RequestMapping("/monitor/log")
public class OperationLogController extends BaseController {

    private String prefix = "monitor/log";

    @Autowired
    private SystemSecurityService systemSecurityService;

    @RequiresPermissions("monitor:log:view")
    @GetMapping()
    public String operlog(ModelMap mmap) {
        mmap.put("startDate", DateUtils.getDate() + " 00:00:00");
        mmap.put("endDate", DateUtils.getDate() + " 23:59:59");
        return prefix + "/actionlog";
    }

    @RequiresPermissions("monitor:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SystemSecurity systemSecurity) {
        startPage();
        List<SystemSecurity> list = systemSecurityService.getSystemSecurityList(systemSecurity);
        return getDataTable(list);
    }

}
