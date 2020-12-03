package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameRoomInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoomInfoMapper {

    GameRoomInfo getGameRoomInfo(Integer serverID);

    GameRoomInfo getGameRoomInfoByUserID(Integer userID);

    List<GameRoomInfo> getGameRoomInfoByKindId(Integer kindId);

    int updateCustomRuleByKindId(@Param("customRule") String customRule, @Param("kindId") int kindId);
}
