package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameItem;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @date 2020/12/19 9:06
 */
@Repository
public interface GameItemMapper {

    GameItem getGameItemByGameId(Integer gameID);

}
