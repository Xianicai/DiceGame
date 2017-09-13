package com.android.xianicai.dicegame.home.provider;

import com.android.xianicai.dicegame.home.provider.data.ReqCreatRoom;
import com.android.xianicai.dicegame.home.provider.data.ReqUser;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface UserProvider {
    void login(String code, String phone, ReqUser reqUser, NetAsynTask.CallBack callBack);
    void creatRoom(String userId, ReqCreatRoom reqCreatRoom, NetAsynTask.CallBack callBack);
    void joinRoom(String userId, String roomId, ReqCreatRoom reqCreatRoom, NetAsynTask.CallBack callBack);
}
