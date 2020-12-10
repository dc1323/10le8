package com.ruoyi.games.service;

import com.ruoyi.games.domain.Members;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/10 23:04
 */
public interface MembersService {

    Map<String, Object> getMembersList(Integer userID, Integer gameID, Integer sGameID, int pageSize, int pageIndex);

}
