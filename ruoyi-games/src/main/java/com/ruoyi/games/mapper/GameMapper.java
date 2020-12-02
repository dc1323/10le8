package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.CaiPiaoDiZhi;
import com.ruoyi.games.domain.GameFunctionSet;
import com.ruoyi.games.domain.LotteryManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameMapper {

    List<GameFunctionSet> queryFunctionSet();

    int updateFunctionSet(@Param("statusName") String statusName, @Param("statusValue") String statusValue);

    List<CaiPiaoDiZhi> getCaiPiaoDiZhi();

    List<LotteryManage> queryLotteryManage(LotteryManage manager);

    int updateCaiPiaoJieGuo(@Param("openCode") String openCode, @Param("id") int id);

    Map<String, String> getRecordDrawInfoByCodeAndExpect(Map<String, String> param);
}
