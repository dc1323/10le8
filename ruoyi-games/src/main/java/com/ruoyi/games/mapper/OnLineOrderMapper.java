package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.OnLineOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/8 19:15
 */
@Repository
public interface OnLineOrderMapper {

    List<OnLineOrder> getOnLineOrderList(@Param("userID") Integer userID,
                                         @Param("pageNum") Integer pageNum,
                                         @Param("pageSize") Integer pageSize);

    int getOnLineOrderCount(@Param("userID") Integer userID);

}
