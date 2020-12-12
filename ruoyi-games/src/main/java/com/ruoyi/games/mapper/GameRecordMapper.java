package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/12 9:34
 */
@Repository
public interface GameRecordMapper {

    List<GameRecord> getGameRecordList(@Param("userID") Integer userID,
                                       @Param("nYear") Integer nYear,
                                       @Param("kindID") Integer kindID,
                                       @Param("month") Integer month,
                                       @Param("matchType") Integer matchType);

}
