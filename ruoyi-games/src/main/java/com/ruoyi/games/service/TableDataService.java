package com.ruoyi.games.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.games.domain.TableData;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/6 17:45
 */
public interface TableDataService {

    List<TableData> getTableDataList();

    AjaxResult oneKeyDeleteFor(String endDate);

}
