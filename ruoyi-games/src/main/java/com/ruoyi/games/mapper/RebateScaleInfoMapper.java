package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RebateScaleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebateScaleInfoMapper {

    void insertBatch(List<RebateScaleInfo> list);

    List<RebateScaleInfo> getRebateScaleInfo();

    void update(RebateScaleInfo info);

}
