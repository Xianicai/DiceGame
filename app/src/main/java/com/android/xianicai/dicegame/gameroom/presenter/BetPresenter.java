package com.android.xianicai.dicegame.gameroom.presenter;

import java.util.Map;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public interface BetPresenter {
    void setBet(String userId, String roomId, Map<String,String> map);
}
