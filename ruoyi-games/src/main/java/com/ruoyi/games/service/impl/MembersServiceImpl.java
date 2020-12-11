package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.Members;
import com.ruoyi.games.mapper.MembersMapper;
import com.ruoyi.games.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/10 23:05
 */
@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Map<String, Object> getMembersList(Integer userID, Integer gameID, Integer sGameID, int pageSize, int pageIndex) {
        Map<String, Object> result = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder(" 1 = 1 ");
        if (null != sGameID && sGameID > 0) {
            stringBuilder.append(" and GameID like '%").append(sGameID).append("%'");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("gameID", gameID);
        param.put("strWhere", stringBuilder.toString());
        param.put("pageSize", pageSize);
        param.put("pageIndex", pageIndex);
        List<Members> members = membersMapper.getMembersList(param);
        result.put("dataList", members);
        result.put("total", param.get("recordCount"));
        return result;
    }
}
