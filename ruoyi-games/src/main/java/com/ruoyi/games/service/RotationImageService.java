package com.ruoyi.games.service;

import com.ruoyi.games.domain.RotationImage;

import java.util.List;

/**
 * @description:
 * @date 2020/12/12 9:10
 */
public interface RotationImageService {

    List<RotationImage> getRotationImageList(int topNumber);

}
