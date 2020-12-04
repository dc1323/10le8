package com.ruoyi.web.controller.game;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.service.AppService;
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
 * @author liuyang17
 * @description:
 * @date 2020/12/3 20:38
 */
@Controller
@RequestMapping("/games/app")
public class AppController extends BaseController {

    private String prefix = "games/app";

    @Autowired
    private AppService appService;

    @RequiresPermissions("games:app:onlinerooms")
    @GetMapping("/onlinerooms")
    public String onlineRooms(ModelMap mmap) {
        List<GameKindItem> gameKindItemList = appService.getGameList();
        mmap.put("gameKindItemList", gameKindItemList);
        return prefix + "/onlinerooms";
    }

    @RequiresPermissions("games:app:onlineroomlist")
    @PostMapping("/onlineroomlist")
    @ResponseBody
    public TableDataInfo onlineRoomList(GameRoomInfo info) {
        startPage();
        List<GameRoomInfo> list = appService.getGameOnlineRooms(info);
        return getDataTable(list);
    }

}
