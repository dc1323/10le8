package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AgentExtension;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/9 9:34
 */
@Repository
public interface AgentExtensionMapper {

    List<AgentExtension> getAgentExtension(Map<String,Object> param);

}
