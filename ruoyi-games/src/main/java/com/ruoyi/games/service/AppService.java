package com.ruoyi.games.service;

import com.ruoyi.games.domain.AndroidConfigInfo;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.RoomRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<GameRoomInfo> getGameRoomByName(String serverName);

    int updateAndroidConfigByID(AndroidConfigInfo info);

    Map<String, Object> createAndroidConfig(AndroidConfigInfo info);

    AndroidConfigInfo getAndroidConfigInfo(AndroidConfigInfo info);

    int updateAndroidConfigureById(int nullity, int batchID);

    AndroidConfigInfo getConfigInfoById(int batchID);
}
