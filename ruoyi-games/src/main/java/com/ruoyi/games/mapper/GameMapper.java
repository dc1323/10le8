package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameMapper {

    List<GameFunctionSet> queryFunctionSet();

    int updateFunctionSet(@Param("statusName") String statusName, @Param("statusValue") String statusValue);

    List<CaiPiaoDiZhi> getCaiPiaoDiZhi(@Param("kindId") int kindId);

    List<LotteryManage> queryLotteryManage(LotteryManage manager);

    int updateCaiPiaoJieGuo(@Param("openCode") String openCode, @Param("id") int id);

    int updateCaiPiaoDiZhi(@Param("startTime") String startTime, @Param("id") int id);

    Map<String, String> getRecordDrawInfoByCodeAndExpect(Map<String, String> param);

    Map<String, String> getInitFalseCaiPiaoJieGuo(Map<String, String> param);

    List<Game2CaiPiaoParam> getGame2CaiPiaoParamList(@Param("kindId") int kindId);
}
