package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.TableData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/6 17:31
 */
@Repository
public interface TableDataMapper {

    List<TableData> getTableDataList();

    void oneKeyDeleteFor(Map<String,Object> param);

}
