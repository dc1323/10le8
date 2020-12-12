package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.HorseLamp;
import com.ruoyi.games.mapper.HorseLampMapper;
import com.ruoyi.games.service.HorseLampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @date 2020/12/9 14:42
 */
@Service
public class HorseLampServiceImpl implements HorseLampService {

    @Autowired
    private HorseLampMapper horseLampMapper;

    @Override
    public List<HorseLamp> getHorseLampList(HorseLamp horseLamp) {
        return horseLampMapper.getHorseLampList(horseLamp);
    }

    @Override
    public void saveHorseLamp(HorseLamp horseLamp) {
        horseLampMapper.saveHorseLamp(horseLamp);
    }

    @Override
    public void deleteHorseLamp(String[] ids) {
        if (null != ids && ids.length > 0) {
            for (String id : ids) {
                Integer iid = Integer.parseInt(id);
                horseLampMapper.deleteHorseLamp(iid);
            }
        }
    }

    @Override
    public List<HorseLamp> getHorseLampTop(int topNumber) {
        return horseLampMapper.getHorseLampTop(topNumber);
    }
}
