package com.ruoyi.web.controller.game;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.service.AppService;
import com.ruoyi.games.service.HorseLampService;
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

    @Autowired
    private HorseLampService horseLampService;

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

    @RequiresPermissions("games:app:roomsrecord")
    @GetMapping("/roomsrecord")
    public String roomsRecord(ModelMap mmap) {
        List<GameKindItem> gameKindItemList = appService.getGameList();
        mmap.put("startDate", DateUtils.getMonthFirstDay());
        mmap.put("endDate", DateUtils.getMonthLastDay());
        mmap.put("gameKindItemList", gameKindItemList);
        return prefix + "/roomsrecord";
    }

    @RequiresPermissions("games:app:roomsrecordlist")
    @PostMapping("/roomsrecordlist")
    @ResponseBody
    public TableDataInfo roomsRecordList(RoomRecord info) {
        startPage();
        List<RoomRecord> list = appService.getRoomsRecordList(info);
        return getDataTable(list);
    }

    @RequiresPermissions("games:app:androidconfiginfo")
    @GetMapping("/androidconfiginfo")
    public String androidConfigInfo(ModelMap mmap) {
        List<AndroidConfigInfo> roomList = appService.getRoomAll();
        List<GameKindItem> gameKindItemList = appService.getGameList();
        mmap.put("roomList", roomList);
        mmap.put("gameKindItemList", gameKindItemList);
        return prefix + "/androidconfiginfo";
    }

    @RequiresPermissions("games:app:androidconfiginfolist")
    @PostMapping("/androidconfiginfolist")
    @ResponseBody
    public TableDataInfo androidConfigInfoList(AndroidConfigInfo info) {
        startPage();
        List<AndroidConfigInfo> list = appService.getAndroidConfigInfoList(info);
        return getDataTable(list);
    }

    @RequiresPermissions("games:app:create")
    @GetMapping("/create")
    public String create(ModelMap mmap) {
        List<GameKindItem> gameKindItemList = appService.getGameList();
        mmap.put("gameKindItemList", gameKindItemList);
        return prefix + "/create";
    }

}
