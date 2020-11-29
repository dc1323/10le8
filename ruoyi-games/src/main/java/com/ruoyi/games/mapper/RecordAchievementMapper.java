package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordAchievementMapper {

    List<RecordAchievement> getRecordAchievementInfo(RecordAchievement recordAchievement);

    List<RecordAchievementDetail> queryRecordAchievementDetail(RecordAchievementDetail detail);

    List<RecordAchievement> getShareDetailInfo(RecordAchievement recordAchievement);

    RecordAchievement getShareDetailInfoSum(RecordAchievement recordAchievement);

}
