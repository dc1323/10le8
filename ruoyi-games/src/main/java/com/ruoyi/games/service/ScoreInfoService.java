package com.ruoyi.games.service;

import com.ruoyi.games.domain.ScoreInfo;

import java.util.List;
import java.util.Map;

public interface ScoreInfoService {

    Map<String,Object> selectScoreInfoList(ScoreInfo scoreInfo);

    /**
     * 获取玩家盈亏表
     * @param scoreInfo
     * @return
     */
    Map<String,Object> queryUserRDScore(ScoreInfo scoreInfo);

    /**
     * 总输赢详情
     * @param userID
     * @param startDate
     * @param endDate
     * @return
     */
    List<ScoreInfo> getScoreDetail(Integer userID,String startDate,String endDate);

}
