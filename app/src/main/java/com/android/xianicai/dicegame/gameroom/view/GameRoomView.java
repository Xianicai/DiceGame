package com.android.xianicai.dicegame.gameroom.view;

import com.android.xianicai.dicegame.base.basemvp.BaseView;
import com.android.xianicai.dicegame.gameroom.provider.data.GameResultBean;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.gameroom.provider.data.CheckRoomBean;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomView extends BaseView {
    void getGameRoomDetail(RoomDetailBean RoomBean);
    void dismissRoom();
    void startGame(GameResultBean gameResultBean);
    void quitRoom();
    void checkedRoom(CheckRoomBean countBean);
    void gameReady(boolean b);
}
