package com.android.xianicai.dicegame.pay.provider.impl;

import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.pay.provider.PayProvider;
import com.android.xianicai.dicegame.pay.provider.data.ReqPayOrder;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/8/31.
 */

public class PayProviderImpl implements PayProvider {
    @Override
    public void getWeiXInOrder(String userId, String price,  ReqPayOrder reqPayOrder,NetAsynTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("price", price);
        NetAsynTask.connectByPost(Urls.POST_CREAT_WEIXIN_ODER, map, reqPayOrder, callBack);
    }
}
