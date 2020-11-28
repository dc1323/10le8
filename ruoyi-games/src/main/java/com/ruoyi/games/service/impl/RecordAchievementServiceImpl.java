package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;
import com.ruoyi.games.mapper.RecordAchievementMapper;
import com.ruoyi.games.service.RecordAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordAchievementServiceImpl implements RecordAchievementService {

    @Autowired
    private RecordAchievementMapper recordAchievementMapper;


    @Override
    public List<RecordAchievement> getRecordAchievementInfo(RecordAchievement recordAchievement) {
        return recordAchievementMapper.getRecordAchievementInfo(recordAchievement);
    }

    @Override
    public List<RecordAchievementDetail> queryRecordAchievementDetail(RecordAchievementDetail detail) {
        return recordAchievementMapper.queryRecordAchievementDetail(detail);
    }
}
