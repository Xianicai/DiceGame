package com.android.xianicai.dicegame.user.view;

import com.android.xianicai.dicegame.base.basemvp.BaseView;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.user.provider.data.UserBean;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface HomeView extends BaseView {
    void login(UserBean userBean);
    void creatRoom(RoomDetailBean roomBean);
    void joinRoomSuccess(RoomDetailBean roomBean);
    void JoinRommFaild(String msg);
}
