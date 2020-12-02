package com.ruoyi.games.service;

import com.ruoyi.games.domain.CaiPiaoDiZhi;
import com.ruoyi.games.domain.GameFunctionSet;
import com.ruoyi.games.domain.LotteryManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GameService {

    List<GameFunctionSet> queryFunctionSet();

    boolean updateFunctionSet(String statusName, String statusValue);

    List<CaiPiaoDiZhi> getCaiPiaoDiZhi();

    List<LotteryManage> queryLotteryManage(LotteryManage manager);

    int updateCaiPiaoJieGuo(String openCode, int id);

    Map<String, String> getRecordDrawInfoByCodeAndExpect(String code, String expect);
}
