package com.android.xianicai.dicegame.user.provider.impl;

import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.user.provider.UserProvider;
import com.android.xianicai.dicegame.user.provider.data.ReqUser;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class UserProviderImpl implements UserProvider {
    @Override
    public void login(String code, String phone, NetAsynTask.CallBack callBack,ReqUser reqUser) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("phone", phone);
        NetAsynTask.connectByGet(Urls.WEIXIN_LOGIN, map, reqUser, callBack);
    }
}
