package com.android.xianicai.dicegame.home.view;

import com.android.xianicai.dicegame.base.basemvp.BaseView;
import com.android.xianicai.dicegame.home.provider.data.CreatRoomBean;
import com.android.xianicai.dicegame.home.provider.data.UserBean;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface HomeView extends BaseView {
    void login(UserBean userBean);
    void loginFaild();
    void creatRoom(CreatRoomBean creatRoomBean);
    void joinRoomSuccess(CreatRoomBean roomBean);
    void joinRoomFaild(String msg);
}
