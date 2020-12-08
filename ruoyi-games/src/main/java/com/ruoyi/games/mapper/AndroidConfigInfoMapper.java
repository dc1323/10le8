package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AndroidConfigInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/7 23:07
 */
@Repository
public interface AndroidConfigInfoMapper {

    List<AndroidConfigInfo> getAndroidConfigList(AndroidConfigInfo info);

    List<AndroidConfigInfo> getRoomAll();

}
