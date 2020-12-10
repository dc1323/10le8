package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.PlatformInfo;
import com.ruoyi.games.mapper.PlatformInfoMapper;
import com.ruoyi.games.service.PlatformInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 * @description:
 * @date 2020/12/5 11:37
 */
@Service
public class PlatformInfoServiceImpl implements PlatformInfoService {

    @Autowired
    private PlatformInfoMapper platformInfoMapper;

    @Override
    public PlatformInfo getPlatformInfo() {
        List<PlatformInfo> list = platformInfoMapper.getPlatformInfo();
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
