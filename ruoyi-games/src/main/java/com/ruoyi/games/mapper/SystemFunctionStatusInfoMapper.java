package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.SystemFunctionStatusInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemFunctionStatusInfoMapper {

    List<SystemFunctionStatusInfo> getFunctionStatusInfoList(SystemFunctionStatusInfo info);

    void update(SystemFunctionStatusInfo info);

}
