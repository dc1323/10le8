package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RotationImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/12 9:06
 */
@Repository
public interface RotationImageMapper {

    List<RotationImage> getRotationImageList(@Param("topNumber") int topNumber);

}
