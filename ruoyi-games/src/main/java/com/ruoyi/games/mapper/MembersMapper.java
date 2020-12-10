package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.Members;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/10 22:56
 */
@Repository
public interface MembersMapper {

    List<Members> getMembersList(Map<String,Object> param);

}
