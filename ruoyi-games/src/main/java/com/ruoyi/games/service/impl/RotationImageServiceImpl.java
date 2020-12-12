package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.RotationImage;
import com.ruoyi.games.mapper.RotationImageMapper;
import com.ruoyi.games.service.RotationImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @date 2020/12/12 9:10
 */
@Service
public class RotationImageServiceImpl implements RotationImageService {

    @Autowired
    private RotationImageMapper rotationImageMapper;


    @Override
    public List<RotationImage> getRotationImageList(int topNumber) {
        return rotationImageMapper.getRotationImageList(topNumber);
    }
}
