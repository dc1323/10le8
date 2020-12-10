package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;
import com.ruoyi.games.domain.UserDistills;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDistillsMapper {

    List<UserDistills> getUserDistills(UserDistills userDistills);

    UserDistills getUserDistillsSum(UserDistills userDistills);

    Integer accept(Map<String, Object> param);

    Integer noAccept(Map<String, Object> param);

    Integer distillPay(Map<String, Object> param);

    List<UserDistills> getUserDistillsList(@Param("userID") Integer userID,
                                           @Param("index") Integer index,
                                           @Param("size") Integer size);

    int getUserDistillsCount(@Param("userID") Integer userID);

}
