package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.RoomRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/4 22:56
 */
@Repository
public interface RoomRecordMapper {

    List<RoomRecord> getRoomsRecordList(RoomRecord roomRecord);

}
