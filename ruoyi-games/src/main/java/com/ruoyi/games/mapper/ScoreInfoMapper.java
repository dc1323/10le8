package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.ScoreInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreInfoMapper {

    List<ScoreInfo> getWinVsLossList(Map<String,Object> param);

    /**
     * 获取玩家盈亏表
     * @param param
     * @return
     */
    List<ScoreInfo> queryUserRDScore(Map<String,Object> param);

    /**
     * 总输赢详情
     * @param param
     * @return
     */
    List<ScoreInfo> queryScoreDetail(Map<String,Object> param);


}
