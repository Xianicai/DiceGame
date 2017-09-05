package com.android.xianicai.dicegame.home.provider.data;

import android.support.annotation.NonNull;

import com.android.xianicai.dicegame.utils.netutil.ReqCommon;

import org.json.JSONObject;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class ReqUser extends ReqCommon<UserBean> {
    @Override
    protected void parseResult(@NonNull JSONObject jsonObject) throws Exception {
        UserBean bean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),UserBean.class);
        setT(bean);
    }
}
