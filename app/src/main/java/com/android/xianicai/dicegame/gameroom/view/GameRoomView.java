package com.android.xianicai.dicegame.gameroom.view;

import com.android.xianicai.dicegame.base.basemvp.BaseView;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomBean;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomView extends BaseView {
    void getGameRoomDetail(RoomBean RoomBean);
}
