package com.android.xianicai.dicegame.gameroom.provider.data;

import android.support.annotation.NonNull;

import com.android.xianicai.dicegame.utils.netutil.ReqCommon;

import org.json.JSONObject;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class ReqGameResult extends ReqCommon<GameResultBean> {
    @Override
    protected void parseResult(@NonNull JSONObject jsonObject) throws Exception {
        GameResultBean bean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),GameResultBean.class);
        setT(bean);
    }
}
