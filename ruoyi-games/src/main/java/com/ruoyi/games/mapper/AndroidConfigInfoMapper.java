package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AndroidConfigInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/7 23:07
 */
@Repository
public interface AndroidConfigInfoMapper {

    List<AndroidConfigInfo> getAndroidConfigList(AndroidConfigInfo info);

    List<AndroidConfigInfo> getRoomAll();

    int updateAndroidConfigByID(AndroidConfigInfo info);

    Map<String, Object> createAndroidConfig(Map<String, String> param);

    AndroidConfigInfo getAndroidConfigInfo(AndroidConfigInfo info);

    int updateAndroidConfigureById(@Param("nullity") int nullity, @Param("batchID") int batchID);

    AndroidConfigInfo getConfigInfoById(@Param("batchID") int batchID);
}
