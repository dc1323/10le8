package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameRoomCommission;
import com.ruoyi.games.domain.GameRoomInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameRoomInfoMapper {

    GameRoomInfo getGameRoomInfo(Integer serverID);

    GameRoomInfo getGameRoomInfoByUserID(Integer userID);

    /**
     * @description: 获取在线房间
     * @author liuyang
     * @date 2020/12/3 21:12
     */
    List<GameRoomInfo> getOnlineRoomsList(GameRoomInfo info);

    /**
     * @description: 获取每日房间抽水
     * @author liuyang
     * @date 2020/12/3 22:28
     */
    List<GameRoomCommission> getCommissionByServerForAll();

    Map<String, Object> createRoom(Map<String, Object> map);

    List<GameRoomInfo> getGameRoomInfoByKindId(Integer kindId);

    int updateCustomRuleByKindId(@Param("customRule") String customRule, @Param("kindId") int kindId);

    List<GameRoomInfo> getRoomsList();

    List<GameRoomInfo> queryRoomList();

    int updateGameRoom(GameRoomInfo info);

    int updateNullityByKindId(@Param("nullity") int nullity, @Param("kindId") int kindId);

    int updateAndroidConfigureByKindId(@Param("nullity") int nullity, @Param("kindId") int kindId);

    int deleteGameRoomInfo(@Param("serverID") int serverID);

    int deleteAndroidConfigure(@Param("serverID") int serverID);

    List<GameRoomInfo> getGameRoomByName(@Param("serverName") String serverName);
}
