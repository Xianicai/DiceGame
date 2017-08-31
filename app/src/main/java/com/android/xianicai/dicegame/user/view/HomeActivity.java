package com.android.xianicai.dicegame.user.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.user.presenter.impl.UserPresenterImpl;
import com.android.xianicai.dicegame.user.provider.data.UserBean;

public class HomeActivity extends BaseActivity implements HomeView {

    @Override
    public int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        String code = getIntent().getStringExtra("code");
        UserPresenterImpl userPresenter = new UserPresenterImpl();
        userPresenter.bindView(this);
        userPresenter.login(code, "1");
    }

    @Override
    public void login(UserBean userBean) {

    }

    //跳转到APP首页
    public static void start(Context context, String code) {
        context.startActivity(new Intent(context, HomeActivity.class).putExtra("code", code));

    }
}
