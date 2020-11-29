package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.GameKindItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameKindItemMapper {

    GameKindItem getGameKindItemInfo(Integer kindID);

    GameKindItem getGameKindItemInfoByUserID(Integer userID);

    List<GameKindItem> getGameList();

}
