package com.android.xianicai.dicegame.user.provider.impl;

import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.user.provider.UserProvider;
import com.android.xianicai.dicegame.user.provider.data.ReqCreatRoom;
import com.android.xianicai.dicegame.user.provider.data.ReqUser;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class UserProviderImpl implements UserProvider {
    @Override
    public void login(String code, String phone, ReqUser reqUser,NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("phone", phone);
        NetAsynTask.connectByPost(Urls.POST_WEIXIN_LOGIN, map, reqUser, callBack);
    }

    @Override
    public void creatRoom(String userId, ReqCreatRoom reqCreatRoom,NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        NetAsynTask.connectByPost(Urls.POST_ROOM_DETAIL, map, reqCreatRoom, callBack);
    }

    @Override
    public void joinRoom(String userId, String roomId, ReqRoomDetail reqRoomDetail, NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roomId", roomId);
        NetAsynTask.connectByPost(Urls.POST_WEIXIN_LOGIN, map, reqRoomDetail, callBack);
    }
}
