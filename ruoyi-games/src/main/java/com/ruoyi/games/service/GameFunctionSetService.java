package com.ruoyi.games.service;

import com.ruoyi.games.domain.GameFunctionSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameFunctionSetService {

    List<GameFunctionSet> queryFunctionSet();

    boolean updateFunctionSet(String statusName, String statusValue);
}
