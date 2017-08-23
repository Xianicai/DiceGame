package com.android.xianicai.dicegame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.xianicai.dicegame.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    public int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    //跳转到APP首页
    public static void start(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));

    }
}
