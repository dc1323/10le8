package com.ruoyi.web.controller.game;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.TableData;
import com.ruoyi.games.service.AppService;
import com.ruoyi.games.service.TableDataService;
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
 * @description:
 * @date 2020/12/6 17:48
 */
@Controller
@RequestMapping("/games/tabledata")
public class TableDataController extends BaseController {

    private String prefix = "games/tabledata";

    @Autowired
    private TableDataService tableDataService;

    @RequiresPermissions("games:tabledata:view")
    @GetMapping("/view")
    public String view(ModelMap mmap) {
        return prefix + "/view";
    }

    @RequiresPermissions("games:tabledata:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TableData info) {
        List<TableData> list = tableDataService.getTableDataList();
        return getDataTable(list);
    }

    @RequiresPermissions("games:tabledata:clear")
    @Log(title = "清理数据", businessType = BusinessType.DELETE)
    @PostMapping("/cleartabledate")
    @ResponseBody
    public AjaxResult oneKeyDeleteFor(String operaType) {
        if (StringUtils.isEmpty(operaType)) {
            return AjaxResult.error("操作有误!");
        } else {
            return tableDataService.oneKeyDeleteFor(operaType);
        }
    }

}
