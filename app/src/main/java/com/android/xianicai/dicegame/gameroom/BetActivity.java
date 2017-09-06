package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.provider.data.BeatItemBean;
import com.android.xianicai.dicegame.gameroom.provider.data.BetBean;
import com.android.xianicai.dicegame.gameroom.view.BetView;
import com.android.xianicai.dicegame.gameroom.view.adapter.BetAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class BetActivity extends BaseActivity implements BetView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.image_bet)
    ImageView mImageBet;

    private String mUserId;
    private String mRoomId;
    private int[] icon = {R.mipmap.icon_manager_magic};

    @Override
    public int getlayoutId() {
        return R.layout.activity_bet;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra("userId");
        mRoomId = getIntent().getStringExtra("roomId");
        List<BetBean> betBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BetBean betBean = new BetBean();
            betBean.mBeatItemBeen = new ArrayList<>();
            int itemCount = 0;
            switch (i) {
                case 0:
                    betBean.title = "大小";
                    itemCount = 2;
                    break;
                case 1:
                    betBean.title = "单双";
                    itemCount = 2;
                    break;
                case 2:
                    betBean.title = "总数";
                    itemCount = 14;
                    break;
                case 3:
                    betBean.title = "豹子";
                    itemCount = 6;
                    break;
            }
            for (int j = 0; j < itemCount; j++) {
                BeatItemBean beatItemBean = new BeatItemBean();
                beatItemBean.icon = icon[0];
                betBean.mBeatItemBeen.add(beatItemBean);
            }
            betBeanList.add(betBean);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        BetAdapter betAdapter = new BetAdapter(this, betBeanList);
        mRecyclerview.setAdapter(betAdapter);

    }

    @Override
    public void setBet() {

    }

    public static void start(Context context, String userId, String roomId) {
        context.startActivity(new Intent(context, BetActivity.class).putExtra("userId", userId).putExtra("roomId", roomId));
    }
}