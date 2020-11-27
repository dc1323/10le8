package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameKindItem;
import org.springframework.stereotype.Repository;

@Repository
public interface GameKindItemMapper {

    GameKindItem getGameKindItemInfo(Integer kindID);

    GameKindItem getGameKindItemInfoByUserID(Integer userID);

}
