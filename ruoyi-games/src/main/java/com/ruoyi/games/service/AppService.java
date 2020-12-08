package com.ruoyi.games.service;

import com.ruoyi.games.domain.AndroidConfigInfo;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.RoomRecord;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/3 21:20
 */
public interface AppService {

    List<GameRoomInfo> getGameOnlineRooms(GameRoomInfo info);

    List<GameKindItem> getGameList();

    List<RoomRecord> getRoomsRecordList(RoomRecord roomRecord);

    List<AndroidConfigInfo> getAndroidConfigInfoList(AndroidConfigInfo info);

    List<AndroidConfigInfo> getRoomAll();

}
