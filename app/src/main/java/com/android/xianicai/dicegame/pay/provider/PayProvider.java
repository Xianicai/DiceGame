package com.android.xianicai.dicegame.pay.provider;

import com.android.xianicai.dicegame.pay.provider.data.ReqPayOrder;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/31.
 */

public interface PayProvider {
    void getWeiXInOrder(String usrId, String price,  ReqPayOrder reqPayOrder,NetAsynTask.CallBack callBack);
}
