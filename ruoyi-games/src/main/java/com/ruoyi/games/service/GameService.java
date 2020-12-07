package com.ruoyi.games.service;

import com.ruoyi.games.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GameService {

    List<GameFunctionSet> queryFunctionSet();

    boolean updateFunctionSet(String statusName, String statusValue);

    List<CaiPiaoDiZhi> getCaiPiaoDiZhi(int kindId);

    List<LotteryManage> queryLotteryManage(LotteryManage manager);

    int updateCaiPiaoJieGuo(String openCode, int id);

    int updateCaiPiaoDiZhi(String startTime, int id);

    Map<String, String> getRecordDrawInfoByCodeAndExpect(String code, String expect);

    Map<String, String> getInitFalseCaiPiaoJieGuo(Map<String, String> param);

    List<Game2CaiPiaoParam> getGame2CaiPiaoParamList(int kindId);

    int updateGame2CaiPiaoParam(Game2CaiPiaoParam param);

    List<GameRoomInfo> getGameRoomInfoByKindId(Integer kindId);

    List<Game2CaiPiaoParam> queryGame2CaiPiaoParamList(Game2CaiPiaoParam param);

    int updateCustomRuleByKindId(String customRule, int kindId);

    boolean updateGameRoomCBEndTime(int kindId, boolean needRestart);

    List<GameRoomInfo> getRoomsList();

    int updateParamTime(Game2CaiPiaoParam param);

    int getRoundByExpect(List<CaiPiaoDiZhi> cpList, String code, String expect, int groupID);

    int updateNullityByKindId(int nullity, int kindId);

    int updateAndroidConfigureByKindId(int nullity, int kindId);

    void stopGameService(int serverId);

    void startGameService(int serverId);

    boolean stopGameServiceForBool(int serverID);

    int deleteGameRoomInfo(int serverID);

    int deleteAndroidConfigure(int serverID);

    GameRoomInfo getGameRoomInfo(Integer serverID);

    Map<String, Integer> getRules(String customRule);
}
