package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameRoomInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomInfoMapper {

    GameRoomInfo getGameRoomInfo(Integer serverID);

    GameRoomInfo getGameRoomInfoByUserID(Integer userID);

}
