package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AccountInfo;
import com.ruoyi.games.domain.GameFunctionSet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameFunctionSetMapper {

    List<GameFunctionSet> queryFunctionSet();

    int updateFunctionSet(@Param("statusName") String statusName, @Param("statusValue") String statusValue);
}
