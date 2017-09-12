package com.android.xianicai.dicegame.gameroom.provider.impl;

import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.provider.GameRoomProvider;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqGameResult;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public class GameRoomProviderImpl implements GameRoomProvider {
    @Override
    public void getGameRoomDetail(String userId, String roomId, ReqRoomDetail reqRoomDetail, NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        NetAsynTask.connectByPost(Urls.POST_ROOM_DETAIL, map, reqRoomDetail, callBack);
    }

    @Override
    public void dismissRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        NetAsynTask.connectByPost(Urls.POST_DISMISS_ROOM, map, reqBase, callBack);
    }

    @Override
    public void startGame(String userId, String roomId,int gameTimes, ReqGameResult reqGameResult, NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        map.put("gameTimes", gameTimes+"");
        NetAsynTask.connectByPost(Urls.POST_STAR_GAME, map, reqGameResult, callBack);
    }

    @Override
    public void quitRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        NetAsynTask.connectByPost(Urls.POST_QUITE_ROOM, map, reqBase, callBack);
    }

    @Override
    public void setBet(String userId, String roomId,Map<String,String> map, ReqBase reqBase, NetAsynTask.CallBack callBack) {
        map.put("userId", userId);
        map.put("roomId", roomId);
        NetAsynTask.connectByPost(Urls.POST_BET_GAME, map, reqBase, callBack);
    }
}
