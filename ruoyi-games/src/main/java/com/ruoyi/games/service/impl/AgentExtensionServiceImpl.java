package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.games.domain.AgentExtension;
import com.ruoyi.games.mapper.AccountInfoMapper;
import com.ruoyi.games.mapper.AgentExtensionMapper;
import com.ruoyi.games.service.AgentExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/9 9:49
 */
@Service
public class AgentExtensionServiceImpl implements AgentExtensionService {

    @Autowired
    private AgentExtensionMapper agentExtensionMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Override
    public AjaxResult agentExtension(Integer userID, Integer gameID) {
        Map<String,Object> param = new HashMap<>();
        param.put("userID",userID);
        param.put("gameID",gameID);
        List<AgentExtension> list = agentExtensionMapper.getAgentExtension(param);
        if(null != list && list.size() > 0){
            String commission = accountInfoMapper.getCanCachOut(param);
            AgentExtension agentExtension = list.get(0);
            agentExtension.setCommission(commission);
            return AjaxResult.success("获取成功",agentExtension);
        }
        return AjaxResult.success("未获取到数据");
    }
}
