package com.ruoyi.games.service;

import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/3 21:20
 */
public interface AppService {

    List<GameRoomInfo> getGameOnlineRooms(GameRoomInfo info);

    List<GameKindItem> getGameList();


}
