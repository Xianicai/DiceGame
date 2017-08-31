package com.android.xianicai.dicegame.user.provider;

import com.android.xianicai.dicegame.user.provider.data.ReqUser;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface UserProvider {
    void login(String code, String phone, NetAsynTask.CallBack callBack,ReqUser reqUser);
}
