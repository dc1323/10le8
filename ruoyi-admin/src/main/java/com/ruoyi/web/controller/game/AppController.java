package com.ruoyi.web.controller.game;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.service.AppService;
import com.ruoyi.games.service.GameService;
import com.ruoyi.games.service.HorseLampService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
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

    @Autowired
    private GameService gameService;

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

        AndroidConfigInfo param = new AndroidConfigInfo();
        param.setBatchID(-1);
        mmap.put("param", param);
        return prefix + "/create";
    }

    @Log(title = "创建机器人", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/add")
    public AjaxResult add(@RequestBody AndroidConfigInfo info) {
        try {
            if (info.getCellScore() == 0) {
                return error("底注有误");
            }

            if (info.getAndroidCount() == 0) {
                return error("机器人有误");
            }

            if (info.getTakeMinScore() == 0) {
                return error("携带分数有误");
            }

            String serverName = info.getKindName() + "_" + info.getCellScore();
            List<GameRoomInfo> gameRoomInfos = appService.getGameRoomByName(serverName);
            if (null == gameRoomInfos || gameRoomInfos.size() == 0) {
                return error("游戏房间不存在,请先创建房间");
            }

            if (gameRoomInfos.size() > 1) {
                return error("当前游戏存在多个底注相同的房间,请删除多余的房间");
            }

            GameRoomInfo gameRoomInfo = gameRoomInfos.get(0);
            long minEnterScore = gameRoomInfo.getMinEnterScore();
            if (minEnterScore > info.getTakeMinScore()) {
                return error("机器人携带分数不能低于房间最小入场分数");
            }

            info.setServiceMode(7);
            info.setEnterTime(0);
            info.setLeaveTime(86400);
            info.setSwitchMinInnings(3);
            info.setSwitchMaxInnings(15);

            if (info.getBatchID() > 0) {
                int count = appService.updateAndroidConfigByID(info);
                if (count > 0) {
                    return success("操作成功!");
                }
                return error("操作失败!");
            } else {
                Map<String, Object> message = appService.createAndroidConfig(info);
                if(message != null && message.size() > 0) {
                    return success("操作成功!");
                }
                return error("操作失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
    }

    @Log(title = "更新机器人配置", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping(value="/updateAndroidConfig")
    public AjaxResult updateAndroidConfig(@RequestBody AndroidConfigInfo info) {
        try {
            if (info.getBatchID() <= 0) {
                return error("操作失败");
            }

            if (info.getServerID() <= 0) {
                return error("操作失败");
            }

            if (info.getNullity() > 1) {
                return error("操作失败");
            }

            AndroidConfigInfo configInfo = appService.getAndroidConfigInfo(info);
            if (null == configInfo) {
                return error("操作失败");
            }

            int count = appService.updateAndroidConfigureById(info.getNullity(), info.getBatchID());
            if (count > 0) {
                gameService.startGameService(info.getServerID());
            }
            return error("操作失败");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return error("操作失败!");
        }
    }

    @GetMapping("/config/{id}")
    public String config(@PathVariable("id") String id, ModelMap mmap) {
        List<GameKindItem> gameKindItemList = appService.getGameList();
        mmap.put("gameKindItemList", gameKindItemList);

        AndroidConfigInfo param = appService.getConfigInfoById(Integer.parseInt(id));

        if (param.getEnterMinInterval() > 0) {
            param.setCheckEnter(1);
        }

        if (param.getLeaveMinInterval() > 0) {
            param.setCheckLeave(1);
        }
        mmap.put("param", param);
        return prefix + "/create";
    }

}
