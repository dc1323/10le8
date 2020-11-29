package com.ruoyi.games.service;

import com.ruoyi.games.domain.RecordAchievement;

import java.util.List;

public interface FilledService {

    List<RecordAchievement> getShareDetailInfo(RecordAchievement info);

    RecordAchievement getShareDetailInfoSum(RecordAchievement info);

}
