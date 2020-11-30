package com.ruoyi.web.controller.game;

import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.games.domain.GameFunctionSet;
import com.ruoyi.games.service.GameFunctionSetService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 游戏管理
 */
@Controller
@RequestMapping("/games/game")
public class GameController extends BaseController {
    private String prefix = "games/game";

    @Autowired
    private GameFunctionSetService gameFunctionSetService;

    @RequiresPermissions("games:game:view")
    @GetMapping()
    public String functionSet(ModelMap mmap) {
        List<GameFunctionSet> list = gameFunctionSetService.queryFunctionSet();
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
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/edit")
    public AjaxResult editSave(@RequestBody List<Map<String, String>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return toAjax(false);
        }

        boolean flag = false;
        for (Map<String, String> map : list) {
            flag = gameFunctionSetService.updateFunctionSet(map.get("name"), map.get("value"));
        }
        return toAjax(flag);
    }
}
