package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomBean;
import com.android.xianicai.dicegame.gameroom.view.GameRoomView;

public class GameRoomActivity extends BaseActivity implements GameRoomView {

    @Override
    public int getlayoutId() {
        return R.layout.activity_game_room;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }


    public static void start(Context context, String userId) {
        context.startActivity(new Intent(context, GameRoomActivity.class).putExtra("userId", userId));
    }

    @Override
    public void getGameRoomDetail(RoomBean RoomBean) {

    }
}
