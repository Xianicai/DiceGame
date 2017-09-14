package com.android.xianicai.dicegame.gameroom.provider.data;

import android.support.annotation.NonNull;

import com.android.xianicai.dicegame.utils.netutil.ReqCommon;

import org.json.JSONObject;

/**
 * Created by Zhanglibin on 2017/9/14.
 */

public class ReqCheckRoom extends ReqCommon<CheckRoomBean> {
    @Override
    protected void parseResult(@NonNull JSONObject jsonObject) throws Exception {
        CheckRoomBean bean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),CheckRoomBean.class);
        setT(bean);
    }
}
