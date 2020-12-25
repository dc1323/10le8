package com.ruoyi.games.service;

import com.aliyuncs.exceptions.ClientException;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.games.domain.*;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AccountInfoService {

    /**
     * 根据条件分页查询账号信息
     *
     * @param strWhere  查询条件
     * @param pageSize  一页多少条
     * @param pageIndex 第几页
     * @return
     */
    Map<String, Object> selectAccountPage(String strWhere, int pageSize, int pageIndex);

    /**
     * 分页查询上下级账号信息
     *
     * @param strWhere
     * @param pageSize
     * @param pageIndex
     * @return
     */
    Map<String, Object> selectAccount(String strWhere, int pageSize, int pageIndex);

    /**
     * 根据userID查询账号信息
     *
     * @param userID
     * @return
     */
    AccountInfo selectAccountByUserID(Integer userID);

    /**
     * 冻结/解冻
     *
     * @param userIDs
     * @param operType
     */
    int freezeAccount(String userIDs, int operType);

    int grantTreasure(Integer userID, String gold);

    /**
     * 设置特殊账号
     *
     * @param userIDs
     */
    int setTeShu(String userIDs);

    /**
     * 取消特殊账号
     *
     * @param userIDs
     */
    int qxTeShu(String userIDs);

    /**
     * 获取游戏卡线玩家
     *
     * @param info
     * @return
     */
    List<AccountInfo> getOnlieList(AccountInfo info);

    List<GameKindItem> getGameList();

    List<GameKindItem> getGameKindList();

    /**
     * 清除卡线
     *
     * @param userIDs
     * @return
     */
    int unlock(String userIDs);

    SystemFunctionStatusInfo getInfoByStatusName(String statusName);

    List<RebateScaleInfo> getRebateScaleInfo();

    AjaxResult rebateScaleInfo(RebateInfo rebateInfo);

    AjaxResult bindPlayingGame(Integer userID, Integer gameID, Integer playingGame);

    AjaxResult userDistillAli(Integer userID, Integer gameID, Double distillMoney, String bankCardNumber);

    AjaxResult bindBank(Integer userID, Integer gameID, String bankName,
                        String bankCardNumber, String bankUserName,
                        String bankTypeName, String phoneNumber, String phoneCode);

    AjaxResult cachOut(Integer userID, Integer gameID);

    int updateNickNameByUserID(String nickName, int userID);

    AjaxResult registerAccount(String phoneNumber, String phoneCode, String passWord, String machineID,
                               String nickName, int playingGame, HttpServletRequest request);

    Map<String, String> registerAccountByMessage(Map<String, String> param);

    AjaxResult sendMsg(String phoneNumber, int userID, int typeID);

    AjaxResult getBankList(Integer userID, Integer gameID, Integer pageIndex, Integer pageSize);
}
