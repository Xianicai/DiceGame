package com.android.xianicai.dicegame.home.presenter;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface UserPresenter  {
    void login(String code, String phone);
    void creatRomm(String userId);
    void joinRoom(String userId, String roomId);
}
