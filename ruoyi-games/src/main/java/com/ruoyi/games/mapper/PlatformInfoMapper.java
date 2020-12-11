package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.PlatformInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/5 11:30
 */
@Repository
public interface PlatformInfoMapper {

    List<PlatformInfo> getPlatformInfo();

}
