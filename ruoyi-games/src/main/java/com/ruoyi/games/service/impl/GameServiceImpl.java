package com.ruoyi.games.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.CaiPiaoDiZhi;
import com.ruoyi.games.domain.GameFunctionSet;
import com.ruoyi.games.domain.LotteryManage;
import com.ruoyi.games.mapper.GameMapper;
import com.ruoyi.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;

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
    public List<CaiPiaoDiZhi> getCaiPiaoDiZhi() {
        return gameMapper.getCaiPiaoDiZhi();
    }

    @Override
    public List<LotteryManage> queryLotteryManage(LotteryManage manager) {
        return gameMapper.queryLotteryManage(manager);
    }

    @Override
    public int updateCaiPiaoJieGuo(String openCode, int id) {
        return gameMapper.updateCaiPiaoJieGuo(openCode, id);
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
}
