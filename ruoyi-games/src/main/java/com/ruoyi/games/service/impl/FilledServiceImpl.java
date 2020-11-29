package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.mapper.RecordAchievementMapper;
import com.ruoyi.games.service.FilledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilledServiceImpl implements FilledService {
    private static final Logger log = LoggerFactory.getLogger(FilledServiceImpl.class);

    @Autowired
    private RecordAchievementMapper recordAchievementMapper;

    @Override
    public List<RecordAchievement> getShareDetailInfo(RecordAchievement info) {
        return recordAchievementMapper.getShareDetailInfo(info);
    }

    @Override
    public RecordAchievement getShareDetailInfoSum(RecordAchievement info) {
        return recordAchievementMapper.getShareDetailInfoSum(info);
    }
}
