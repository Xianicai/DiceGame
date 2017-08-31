package com.android.xianicai.dicegame.base.basemvp;

/**
 * Created by Zhanglibin on 2017/4/8.
 */

public interface BaseView {

    void showProgress();

    void showProgress(String message);

    void hideProgress();

    void showMsg(String msg);

}
