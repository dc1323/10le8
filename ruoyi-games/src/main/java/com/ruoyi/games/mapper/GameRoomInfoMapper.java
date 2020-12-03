package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameRoomCommission;
import com.ruoyi.games.domain.GameRoomInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
