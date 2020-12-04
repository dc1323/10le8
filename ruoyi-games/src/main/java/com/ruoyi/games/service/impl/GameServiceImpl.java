package com.ruoyi.games.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.mapper.GameMapper;
import com.ruoyi.games.mapper.GameRoomInfoMapper;
import com.ruoyi.games.service.GameService;
import org.apache.commons.collections.CollectionUtils;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private GameRoomInfoMapper gameRoomInfoMapper;

    @Value("${GameServerUrl}")
    private String gameServerUrl;

    @Override
    public List<GameFunctionSet> queryFunctionSet() {
        return gameMapper.queryFunctionSet();
    }

    @Override
    public boolean updateFunctionSet(String statusName, String statusValue) {
        if (StringUtils.isEmpty(statusName) || StringUtils.isEmpty(statusValue)) {
            return false;
        }
        return gameMapper.updateFunctionSet(statusName, statusValue) > 0 ? true : false;
    }

    @Override
    public List<CaiPiaoDiZhi> getCaiPiaoDiZhi(int kindId) {
        return gameMapper.getCaiPiaoDiZhi(kindId);
    }

    @Override
    public List<LotteryManage> queryLotteryManage(LotteryManage manager) {
        return gameMapper.queryLotteryManage(manager);
    }

    @Override
    public int updateParamTime(Game2CaiPiaoParam param) {
        return gameMapper.updateParamTime(param);
    }

    @Override
    public int updateCaiPiaoJieGuo(String openCode, int id) {
        return gameMapper.updateCaiPiaoJieGuo(openCode, id);
    }

    @Override
    public int updateCaiPiaoDiZhi(String startTime, int id) {
        return gameMapper.updateCaiPiaoDiZhi(startTime, id);
    }

    @Override
    public Map<String, String> getRecordDrawInfoByCodeAndExpect(String code, String expect) {
        Map<String, String> param = new HashMap<String, String>();
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(expect)) {
            return param;
        }

        param.put("code", code);
        param.put("expect", expect);
        return gameMapper.getRecordDrawInfoByCodeAndExpect(param);
    }

    @Override
    public Map<String, String> getInitFalseCaiPiaoJieGuo(Map<String, String> param) {
        return gameMapper.getInitFalseCaiPiaoJieGuo(param);
    }

    @Override
    public List<Game2CaiPiaoParam> getGame2CaiPiaoParamList(int kindId) {
        return gameMapper.getGame2CaiPiaoParamList(kindId);
    }

    @Override
    public int updateGame2CaiPiaoParam(Game2CaiPiaoParam param) {
        return gameMapper.updateGame2CaiPiaoParam(param);
    }

    @Override
    public List<GameRoomInfo> getGameRoomInfoByKindId(Integer kindId) {
        return gameRoomInfoMapper.getGameRoomInfoByKindId(kindId);
    }

    @Override
    public int updateCustomRuleByKindId(String customRule, int kindId) {
        return gameRoomInfoMapper.updateCustomRuleByKindId(customRule, kindId);
    }

    @Override
    public List<GameRoomInfo> getRoomsList() {
        return gameRoomInfoMapper.getRoomsList();
    }

    @Override
    public boolean updateGameRoomCBEndTime(int kindId, boolean needRestart ){
        List<GameRoomInfo> roomInfos = getGameRoomInfoByKindId(kindId);
        if (CollectionUtils.isEmpty(roomInfos)) {
            return false;
        }

        List<Game2CaiPiaoParam> piaoParams = getGame2CaiPiaoParamList(kindId);
        if (CollectionUtils.isEmpty(piaoParams)) {
            return false;
        }

        Game2CaiPiaoParam param = piaoParams.get(0);
        int cbFreeTime = param.cbFreeTime;
        int cbBetTime = param.cbBetTime;
        int cbEndTime = param.cbEndTime;

        GameRoomInfo model = roomInfos.get(0);
        Map<String, Integer> dic = getRules(model.getCustomRule().substring(0, 32));
        String surplusStr = model.getCustomRule().substring(32, model.getCustomRule().length());
        dic.put("cbFreeTime", cbFreeTime);
        dic.put("cbBetTime", cbBetTime);
        dic.put("cbEndTime", cbEndTime);
        String customRule = appendString(dic, surplusStr);
        int count = updateCustomRuleByKindId(customRule, kindId);
        if (count > 0) {
            if (needRestart) {
                for (int i = 0; i < roomInfos.size(); i++){
                    startGameService(roomInfos.get(i).getServerID());
                }
            }
            return true;
        }
        return false;
    }

    private void startGameService(int serverId) {
        String postdata = "{\"msgid\":10, \"content\":{\"ServerID\": " + serverId + "}}";
        HttpUtils.sendPost(gameServerUrl, postdata);
    }

    public Map<String, Integer> getRules(String customRule) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (customRule.length() > 32) {
            return map;
        }

        map.put("cbFreeTime", Integer.parseInt(getNewSubstring(customRule.substring(0, 4)),16));
        map.put("cbBetTime", Integer.parseInt(getNewSubstring(customRule.substring(4, 8)),16));
        map.put("cbEndTime", Integer.parseInt(getNewSubstring(customRule.substring(8, 12)),16));
        map.put("cbDWZWin", Integer.parseInt(getNewSubstring(customRule.substring(12, 16)),16));
        map.put("cbXWZWin", Integer.parseInt(getNewSubstring(customRule.substring(16, 20)),16));
        map.put("cbWZWin",  Integer.parseInt(getNewSubstring(customRule.substring(20, 24)),16));
        map.put("cbDLLost", Integer.parseInt(getNewSubstring(customRule.substring(24, 28)),16));
        map.put("cbXLLost", Integer.parseInt(getNewSubstring(customRule.substring(28, 32)),16));
        return map;
    }

    public String appendString(Map<String, Integer> dic, String surplusStr) {
        String outString = "";
        for (String key : dic.keySet()){
            Integer value = dic.get(key);
            outString += getNewSubstring(StringUtils.intTohex(value));
        }
        outString += surplusStr;
        return outString;
    }

    public String getNewSubstring(String needString){
        String frontStr = needString.substring(0, 2);
        String laterStr = needString.substring(2, needString.length());
        String str = laterStr + frontStr;
        return str;
    }

    @Override
    public int getRoundByExpect(List<CaiPiaoDiZhi> cpList, String code, String expect, int groupID) {
        int nRoundCount = 0;
        String strRoundCount = expect.substring(expect.length() - 3, expect.length());
        //判断百位是否为0
        if (strRoundCount.substring(0, 1).equals("0")) {
            //判断十位是否为0
            if (strRoundCount.substring(1, strRoundCount.length() - 1).equals("0")) {
                //取最后一位
                nRoundCount = Integer.parseInt(strRoundCount.substring(strRoundCount.length() - 1, strRoundCount.length()));
            } else {
                nRoundCount = Integer.parseInt(strRoundCount.substring(strRoundCount.length() - 2, strRoundCount.length()));
            }
        } else {
            nRoundCount = Integer.parseInt(strRoundCount);
        }

        int cpCount = 0;
        for (CaiPiaoDiZhi diZhi : cpList) {
            if (diZhi.getGroupId() == groupID) {
                cpCount++;
            }
        }

        int SortID = 0;
        for (CaiPiaoDiZhi diZhi : cpList) {
            if (diZhi.getCode().equals(code)) {
                SortID = diZhi.getSortId();
                break;
            }
        }
        nRoundCount = (nRoundCount - 1) * cpCount + SortID;
        return nRoundCount;
    }

    @Override
    public List<Game2CaiPiaoParam> queryGame2CaiPiaoParamList(Game2CaiPiaoParam param) {
        return gameMapper.queryGame2CaiPiaoParamList(param);
    }
}
