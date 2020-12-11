package com.ruoyi.games.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.games.domain.*;
import com.ruoyi.games.mapper.AndroidConfigInfoMapper;
import com.ruoyi.games.mapper.GameKindItemMapper;
import com.ruoyi.games.mapper.GameRoomInfoMapper;
import com.ruoyi.games.mapper.RoomRecordMapper;
import com.ruoyi.games.service.AppService;
import com.ruoyi.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/3 21:20
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private GameRoomInfoMapper gameRoomInfoMapper;

    @Autowired
    private GameKindItemMapper gameKindItemMapper;

    @Autowired
    private RoomRecordMapper roomRecordMapper;

    @Autowired
    private AndroidConfigInfoMapper androidConfigInfoMapper;

    @Value("${GameServerUrl}")
    private String gameServerUrl;

    @Override
    public List<GameRoomInfo> getGameOnlineRooms(GameRoomInfo info) {
        List<GameRoomInfo> infos = gameRoomInfoMapper.getOnlineRoomsList(info);
        if (null != infos && infos.size() > 0) {
            String param = "msgid=3";
            String onlineRoom = "";//HttpUtils.sendPost(gameServerUrl, param);
            JSONArray onlineRooms = JSONArray.parseArray(onlineRoom);
            int kindName123 = 0;
            int kindName124 = 0;
            if (null != onlineRooms && onlineRooms.size() > 0) {
                for (int i = 0; i < onlineRooms.size(); i++) {
                    JSONObject jsonObject = onlineRooms.getJSONObject(i);
                    Integer kindID = jsonObject.getInteger("KindID");
                    if (Objects.equals(kindID, 123)) {
                        kindName123++;
                    }
                    if (Objects.equals(kindID, 124)) {
                        kindName124++;
                    }
                }
            }
            for (GameRoomInfo room : infos) {
                Integer serverID = room.getServerID();
                room.setKindName123(kindName123);
                room.setKindName124(kindName124);
                if (null != onlineRooms && onlineRooms.size() > 0) {
                    boolean flag = false;
                    for (int i = 0; i < onlineRooms.size(); i++) {
                        JSONObject jsonObject = onlineRooms.getJSONObject(i);
                        Integer tempServerID = jsonObject.getInteger("ServerID");
                        if (Objects.equals(tempServerID, serverID)) {
                            room.setAddCounts(jsonObject.getIntValue("AddCounts"));
                            room.setUserCount(jsonObject.getIntValue("UserCount"));
                            room.setAndroidCount(jsonObject.getIntValue("AndroidCount"));
                            room.setIsOnline(1);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        room.setAddCounts(0);
                        room.setUserCount(0);
                        room.setAndroidCount(0);
                        room.setIsOnline(0);
                    }
                } else {
                    room.setAddCounts(0);
                    room.setUserCount(0);
                    room.setAndroidCount(0);
                    room.setIsOnline(0);
                }
                List<GameRoomCommission> commissions = gameRoomInfoMapper.getCommissionByServerForAll();
                if (null != commissions && commissions.size() > 0) {
                    boolean f = false;
                    for (GameRoomCommission commission : commissions) {
                        if (Objects.equals(commission.getServerID(), serverID)) {
                            f = true;
                            room.setGameRevenue(commission.getGameRevenue());
                            room.setAgentRevenue(commission.getAgentRevenue());
                            break;
                        }
                    }
                    if (!f) {
                        room.setGameRevenue(new BigDecimal(0));
                        room.setAgentRevenue(new BigDecimal(0));
                    }
                } else {
                    room.setGameRevenue(new BigDecimal(0));
                    room.setAgentRevenue(new BigDecimal(0));
                }
            }
        }
        return infos;
    }

    @Override
    public List<GameKindItem> getGameList() {
        List<GameKindItem> list = gameKindItemMapper.getGameList();
        GameKindItem temp3 = new GameKindItem();
        temp3.setKindID(0);
        temp3.setKindName("选择游戏类型");
        list.add(0, temp3);
        return list;
    }

    @Override
    public List<RoomRecord> getRoomsRecordList(RoomRecord roomRecord) {
        return roomRecordMapper.getRoomsRecordList(roomRecord);
    }

    @Override
    public List<AndroidConfigInfo> getAndroidConfigInfoList(AndroidConfigInfo info) {
        return androidConfigInfoMapper.getAndroidConfigList(info);
    }

    @Override
    public List<AndroidConfigInfo> getRoomAll() {
        List<AndroidConfigInfo> list = androidConfigInfoMapper.getRoomAll();
        AndroidConfigInfo info = new AndroidConfigInfo();
        info.setKindName("全部房间");
        info.setKindID(0);
        list.add(0,info);
        return list;
    }

    @Override
    public List<GameRoomInfo> getGameRoomByName(String serverName) {
        return gameRoomInfoMapper.getGameRoomByName(serverName);
    }

    @Override
    public int updateAndroidConfigByID(AndroidConfigInfo info) {
        return androidConfigInfoMapper.updateAndroidConfigByID(info);
    }

    @Override
    public AndroidConfigInfo getAndroidConfigInfo(AndroidConfigInfo info) {
        return androidConfigInfoMapper.getAndroidConfigInfo(info);
    }

    @Override
    public int updateAndroidConfigureById(int nullity, int batchID) {
        return androidConfigInfoMapper.updateAndroidConfigureById(nullity, batchID);
    }

    @Override
    public AndroidConfigInfo getConfigInfoById(int batchID) {
        return androidConfigInfoMapper.getConfigInfoById(batchID);
    }

    @Override
    public Map<String, Object> createAndroidConfig(AndroidConfigInfo android) {
        Map<String, String> param = new HashMap<>();
        param.put("wServerID", android.getKindID() + "");
        param.put("dwServiceMode", android.getServiceMode() + "");
        param.put("dwAndroidCount", android.getAndroidCount() + "");
        param.put("dwEnterTime", android.getEnterTime() + "");
        param.put("dwLeaveTime", android.getLeaveTime() + "");
        param.put("dwEnterMinInterval", android.getEnterMinInterval() + "");
        param.put("dwEnterMaxInterval", android.getEnterMaxInterval() + "");
        param.put("dwLeaveMinInterval", android.getLeaveMinInterval() + "");
        param.put("dwLeaveMaxInterval", android.getLeaveMaxInterval() + "");
        param.put("lTakeMinScore", android.getTakeMinScore() + "");
        param.put("lTakeMaxScore", android.getTakeMaxScore() + "");
        param.put("dwSwitchMinInnings", android.getSwitchMinInnings() + "");
        param.put("dwSwitchMaxInnings", android.getSwitchMaxInnings() + "");
        param.put("dwAndroidCountMember0", android.getAndroidCount() + "");
        param.put("dwAndroidCountMember1", "0");
        param.put("dwAndroidCountMember2", "0");
        param.put("dwAndroidCountMember3", "0");
        param.put("dwAndroidCountMember4", "0");
        param.put("dwAndroidCountMember5", "0");
        param.put("CellScore", android.getCellScore() + "");
        param.put("strErrorDescribe", "");
        return androidConfigInfoMapper.createAndroidConfig(param);
    }
}
