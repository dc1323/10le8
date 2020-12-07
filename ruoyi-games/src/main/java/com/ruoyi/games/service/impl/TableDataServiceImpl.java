package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.games.domain.TableData;
import com.ruoyi.games.mapper.TableDataMapper;
import com.ruoyi.games.service.TableDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/6 17:45
 */
@Service
public class TableDataServiceImpl implements TableDataService {

    @Autowired
    private TableDataMapper tableDataMapper;

    @Override
    public List<TableData> getTableDataList() {
        return tableDataMapper.getTableDataList();
    }

    @Override
    public AjaxResult oneKeyDeleteFor(String opType) {
        String endDate = DateUtils.getMonthFirstDay();
        String startDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,1);
        switch (opType){
            case "three":
                calendar.add(Calendar.MONTH,-3);
                startDate = format.format(calendar.getTime()) + " 00:00:00";
                break;
            case "halfyear":
                calendar.add(Calendar.MONTH,-6);
                startDate = format.format(calendar.getTime()) + " 00:00:00";
                break;
            case "year":
                calendar.add(Calendar.YEAR,-1);
                startDate = format.format(calendar.getTime()) + " 00:00:00";
                break;
            default:
                return AjaxResult.error("操作有误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        tableDataMapper.oneKeyDeleteFor(param);
        String result = (String)param.get("strErr");
        if("清除成功".equalsIgnoreCase(result)){
            return AjaxResult.success(result);
        }
        return AjaxResult.error(result);
    }
}
