package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.ScoreInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreInfoMapper {

    List<ScoreInfo> getWinVsLossList(Map<String,Object> param);


}
