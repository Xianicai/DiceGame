package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.view.BetView;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class BetActivity extends BaseActivity implements BetView {

    private String mUserId;
    private String mRoomId;

    @Override
    public int getlayoutId() {
        return R.layout.activity_bet;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra("userId");
        mRoomId = getIntent().getStringExtra("roomId");
    }

    @Override
    public void setBet() {

    }

    public static void start(Context context, String userId, String roomId) {
        context.startActivity(new Intent(context, BetActivity.class).putExtra("userId", userId).putExtra("roomId", roomId));
    }
}