package com.ruoyi.games.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.GameKindItem;
import com.ruoyi.games.domain.GameRoomInfo;
import com.ruoyi.games.domain.ScoreInfo;
import com.ruoyi.games.mapper.GameKindItemMapper;
import com.ruoyi.games.mapper.GameRoomInfoMapper;
import com.ruoyi.games.mapper.ScoreInfoMapper;
import com.ruoyi.games.service.ScoreInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScoreInfoServiceImpl implements ScoreInfoService {
    private static final Logger log = LoggerFactory.getLogger(ScoreInfoServiceImpl.class);

    @Autowired
    private ScoreInfoMapper scoreInfoMapper;

    @Autowired
    private GameKindItemMapper gameKindItemMapper;

    @Autowired
    private GameRoomInfoMapper gameRoomInfoMapper;


    @Override
    public Map<String, Object> selectScoreInfoList(ScoreInfo scoreInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>(5);
        param.put("gameID", null == scoreInfo.getGameID() ? 0 : scoreInfo.getGameID());
        param.put("userType", 0 == scoreInfo.getUserType() ? -1 : scoreInfo.getUserType());
        param.put("scoreType", scoreInfo.getScoreType());
        param.put("pageSize", scoreInfo.getPageSize());
        param.put("pageIndex", scoreInfo.getPageIndex());
        List<ScoreInfo> scoreInfos = scoreInfoMapper.getWinVsLossList(param);
        if (null != scoreInfos && scoreInfos.size() > 0) {
            for (ScoreInfo score : scoreInfos) {
                GameKindItem gameKindItem = gameKindItemMapper.getGameKindItemInfoByUserID(score.getUserID());
                String kindName = null;
                String serverName = null;
                if (null == gameKindItem || StringUtils.isEmpty(gameKindItem.getKindName())) {
                    kindName = null;
                } else {
                    kindName = gameKindItem.getKindName();
                }
                GameRoomInfo gameRoomInfo = gameRoomInfoMapper.getGameRoomInfoByUserID(score.getUserID());
                if (null == gameRoomInfo || StringUtils.isEmpty(gameRoomInfo.getServerName())) {
                    serverName = null;
                } else {
                    serverName = gameRoomInfo.getServerName();
                }
                score.setEnterIp(kindName);
                score.setKindName(kindName);
                score.setServerName(serverName);
            }
        }
        resultMap.put("total", param.get("recordCount"));
        resultMap.put("dataList", scoreInfos);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryUserRDScore(ScoreInfo scoreInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>(5);
        param.put("startDate", scoreInfo.getStartDate());
        param.put("endDate", scoreInfo.getEndDate());
        param.put("scoreType", scoreInfo.getScoreType());
        param.put("pageSize", scoreInfo.getPageSize());
        param.put("pageIndex", scoreInfo.getPageIndex());
        List<ScoreInfo> scoreInfos = scoreInfoMapper.queryUserRDScore(param);
        resultMap.put("pageCount", param.get("pageCount"));
        resultMap.put("total", param.get("recordCount"));
        resultMap.put("dataList", scoreInfos);
        return resultMap;
    }

    @Override
    public List<ScoreInfo> getScoreDetail(Integer userID, String startDate, String endDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("startDate", DateUtils.dateTime("yyyy-MM-dd HH:mm:ss",startDate));
        param.put("endDate", DateUtils.dateTime("yyyy-MM-dd HH:mm:ss",endDate));
        List<ScoreInfo> scoreInfos = scoreInfoMapper.queryScoreDetail(param);
        String errorMsg = (String) param.get("strErr");
        if (StringUtils.isNotEmpty(errorMsg)) {
            log.error("查询总输赢详情出错：" + errorMsg);
            return null;
        }
        return scoreInfos;
    }
}
