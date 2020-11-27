package com.ruoyi.games.service;

import com.ruoyi.games.domain.ScoreInfo;

import java.util.Map;

public interface ScoreInfoService {

    Map<String,Object> selectScoreInfoList(ScoreInfo scoreInfo);

}
