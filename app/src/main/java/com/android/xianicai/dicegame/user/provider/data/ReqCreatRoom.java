package com.android.xianicai.dicegame.user.provider.data;

import android.support.annotation.NonNull;

import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.utils.netutil.ReqCommon;

import org.json.JSONObject;

/**
 * Created by Zhanglibin on 2017/9/2.
 */

public class ReqCreatRoom extends ReqCommon<RoomDetailBean> {
    @Override
    protected void parseResult(@NonNull JSONObject jsonObject) throws Exception {
        RoomDetailBean bean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),RoomDetailBean.class);
        setT(bean);
    }
}
