package com.android.xianicai.dicegame.gameroom.presenter;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomPresenter {
    void getGameRoomDetail(String userId, String roomId);
    void dismissRoom(String userId, String roomId);
    void startGame(String userId, String roomId);
    void quitRoom(String userId, String roomId);
}
