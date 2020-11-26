package com.ruoyi.web.controller.game;

import com.ruoyi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 游戏管理
 */
@Controller
@RequestMapping("/games/game")
public class GameController extends BaseController {
    private String prefix = "games/game";

    @RequiresPermissions("games:game:view")
    @GetMapping()
    public String functionSet() {
        return prefix + "/function_set";
    }

}
