package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RecordAchievement;
import com.ruoyi.games.domain.RecordAchievementDetail;
import com.ruoyi.games.domain.ShareDetailInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordAchievementMapper {

    List<RecordAchievement> getRecordAchievementInfo(RecordAchievement recordAchievement);

    List<RecordAchievementDetail> queryRecordAchievementDetail(RecordAchievementDetail detail);

    List<RecordAchievement> getShareDetailInfo(RecordAchievement recordAchievement);

    RecordAchievement getShareDetailInfoSum(RecordAchievement recordAchievement);

    List<ShareDetailInfo> getShareDetailInfoList(@Param("userID") Integer userID,
                                                 @Param("index") int index,
                                                 @Param("size") int size);

    int getShareDetailInfoCount(@Param("userID") Integer userID);

}
