package com.ruoyi.games.service;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;

import java.util.List;

public interface RecordAchievementService {

    List<RecordAchievement> getRecordAchievementInfo(RecordAchievement recordAchievement);

    List<RecordAchievementDetail> queryRecordAchievementDetail(RecordAchievementDetail detail);

}
