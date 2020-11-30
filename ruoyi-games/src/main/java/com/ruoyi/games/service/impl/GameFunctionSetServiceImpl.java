package com.ruoyi.games.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.games.domain.GameFunctionSet;
import com.ruoyi.games.mapper.GameFunctionSetMapper;
import com.ruoyi.games.service.GameFunctionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameFunctionSetServiceImpl implements GameFunctionSetService {
    @Autowired
    private GameFunctionSetMapper gameFunctionSetMapper;

    @Override
    public List<GameFunctionSet> queryFunctionSet() {
        return gameFunctionSetMapper.queryFunctionSet();
    }

    @Override
    public boolean updateFunctionSet(String statusName, String statusValue) {
        if (StringUtils.isEmpty(statusName) || StringUtils.isEmpty(statusValue)) {
            return false;
        }
        return gameFunctionSetMapper.updateFunctionSet(statusName, statusValue) > 0 ? true : false;
    }
}
