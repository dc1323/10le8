package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.HorseLamp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/9 14:25
 */
@Repository
public interface HorseLampMapper {

    List<HorseLamp> getHorseLampList(HorseLamp horseLamp);

    void saveHorseLamp(HorseLamp horseLamp);

    void deleteHorseLamp(Integer id);

    List<HorseLamp> getHorseLampTop(@Param("topNumber") int topNumber);

}
