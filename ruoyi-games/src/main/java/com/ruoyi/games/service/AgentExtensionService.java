package com.ruoyi.games.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.games.domain.AgentExtension;

/**
 * @author liuyang17
 * @description: 推广
 * @date 2020/12/9 9:48
 */
public interface AgentExtensionService {

    AjaxResult agentExtension(Integer userID, Integer gameID);

}
