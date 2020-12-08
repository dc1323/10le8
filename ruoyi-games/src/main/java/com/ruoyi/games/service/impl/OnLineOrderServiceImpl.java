package com.ruoyi.games.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.games.domain.OnLineOrder;
import com.ruoyi.games.domain.UserDistills;
import com.ruoyi.games.mapper.OnLineOrderMapper;
import com.ruoyi.games.mapper.UserDistillsMapper;
import com.ruoyi.games.service.OnLineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/8 19:35
 */
@Service
public class OnLineOrderServiceImpl implements OnLineOrderService {

    @Autowired
    private OnLineOrderMapper onLineOrderMapper;

    @Autowired
    private UserDistillsMapper userDistillsMapper;

    @Override
    public AjaxResult getShareDetailList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        Integer pageNum = 0;
        if (null == pageSize) {
            pageSize = 10;
        }
        if (pageIndex > 0) {
            pageNum = (pageIndex - 1) * pageSize;
        } else {
            pageNum = 0;
        }
        int total = onLineOrderMapper.getOnLineOrderCount(userID);
        if (total == 0) {
            return AjaxResult.success("获取成功");
        }
        List<OnLineOrder> list = onLineOrderMapper.getOnLineOrderList(userID, pageNum, pageSize);
        TableDataInfo dataInfo = new TableDataInfo(list, total);
        return AjaxResult.success("获取成功", dataInfo);
    }

    @Override
    public AjaxResult getUserDistillList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize) {
        Integer pageNum = 0;
        if (null == pageSize) {
            pageSize = 10;
        }
        if (pageIndex > 0) {
            pageNum = (pageIndex - 1) * pageSize;
        } else {
            pageNum = 0;
        }
        int total = userDistillsMapper.getUserDistillsCount(userID);
        if (total == 0) {
            return AjaxResult.success("获取成功");
        }
        List<UserDistills> list = userDistillsMapper.getUserDistillsList(userID, pageNum, pageSize);
        TableDataInfo dataInfo = new TableDataInfo(list, total);
        return AjaxResult.success("获取成功", dataInfo);
    }
}
