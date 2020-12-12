package com.ruoyi.games.service;

import com.ruoyi.games.domain.HorseLamp;

import java.util.List;

/**
 * @description:
 * @date 2020/12/9 14:42
 */
public interface HorseLampService {

    List<HorseLamp> getHorseLampList(HorseLamp horseLamp);

    void saveHorseLamp(HorseLamp horseLamp);

    void deleteHorseLamp(String[] ids);

    List<HorseLamp> getHorseLampTop(int topNumber);

}
