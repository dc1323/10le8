package com.ruoyi.games.service;

import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/8 19:35
 */
public interface OnLineOrderService {

    AjaxResult getShareDetailList(Integer userID,Integer gameID,Integer pageIndex, Integer pageSize);

    AjaxResult getUserDistillList(Integer userID,Integer gameID,Integer pageIndex, Integer pageSize);

    AjaxResult getAgentExtension(Integer userID, Integer gameID);

}
