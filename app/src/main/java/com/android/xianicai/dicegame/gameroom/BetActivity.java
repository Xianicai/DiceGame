package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.presenter.impl.BetpresenterImpl;
import com.android.xianicai.dicegame.gameroom.provider.data.BeatItemBean;
import com.android.xianicai.dicegame.gameroom.provider.data.BetBean;
import com.android.xianicai.dicegame.gameroom.view.BetView;
import com.android.xianicai.dicegame.gameroom.view.adapter.BetAdapter;
import com.android.xianicai.dicegame.utils.ListUtil;
import com.android.xianicai.dicegame.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class BetActivity extends BaseActivity implements BetView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private String mUserId;
    private String mRoomId;
    private int[] itemId = {BetBean.BET_ID_SIZE_BIG, BetBean.BET_ID__SIZE_SMALL, BetBean.BET_ID_SINGLEDOUBLE_SINGLE, BetBean.BET_ID_SINGLEDOUBLE_DOUBLE, BetBean.BET_ID_NUMBER_NINE,
            BetBean.BET_ID_NUMBER_TEN, BetBean.BET_ID_NUMBER_ELEVEN, BetBean.BET_ID_NUMBER_TWELVE, BetBean.BET_ID_NUMBER_SEVEN, BetBean.BET_ID_NUMBER_EIGHT, BetBean.BET_ID_NUMBER_THIRTEEN,
            BetBean.BET_ID_NUMBER_FOURTEEN, BetBean.BET_ID_NUMBER_FIVE, BetBean.BET_ID_NUMBER_SIX, BetBean.BET_ID_NUMBER_FIFTEEN, BetBean.BET_ID_NUMBER_SIXTEEN, BetBean.BET_ID_NUMBER_FOUR,
            BetBean.BET_ID_NUMBER_SEVENTEEN, BetBean.BET_ID_LEOPARD_ONE,};
    private int[] icon = {R.mipmap.icon_size_big, R.mipmap.icon_size_small, R.mipmap.icon_single,
            R.mipmap.icon_doubl, R.mipmap.icon_number_nine, R.mipmap.icon_number_ten, R.mipmap.icon_number_eleven, R.mipmap.icon_number_twelve,
            R.mipmap.icon_number_seven, R.mipmap.icon_number_eight, R.mipmap.icon_number_thirteen, R.mipmap.icon_number_fourteen,
            R.mipmap.icon_number_five, R.mipmap.icon_number_six, R.mipmap.icon_number_fifteen, R.mipmap.icon_number_sixteen,
            R.mipmap.icon_number_four, R.mipmap.icon_number_seventeen, R.mipmap.icon_lepoard};
    private BetpresenterImpl mBetpresenter;
    private BetAdapter mBetAdapter;

    @Override
    public int getlayoutId() {
        return R.layout.activity_bet;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra("userId");
        mRoomId = getIntent().getStringExtra("roomId");
        mBetpresenter = new BetpresenterImpl();
        mBetpresenter.bindView(this);

        List<BetBean> betBeanList = new ArrayList<>();
        int idIndex = 0;
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
                    itemCount = 1;
                    break;
            }
            for (int j = 0; j < itemCount; j++) {
                BeatItemBean beatItemBean = new BeatItemBean();
                beatItemBean.icon = icon[idIndex];
                beatItemBean.id = itemId[idIndex];
                beatItemBean.goldCount = 0;
                betBean.mBeatItemBeen.add(beatItemBean);
                idIndex++;
            }
            betBeanList.add(betBean);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mBetAdapter = new BetAdapter(this, betBeanList);
        mRecyclerview.setAdapter(mBetAdapter);

    }

    @Override
    public void setBet() {
        ToastUtil.showMessage("下注成功");
        finish();
    }


    public static void start(Context context, String userId, String roomId) {
        context.startActivity(new Intent(context, BetActivity.class).putExtra("userId", userId).putExtra("roomId", roomId));
    }


    public void bet() {
        List<BetBean> betBeanList = mBetAdapter.betBeanList;
        if (ListUtil.isNotEmpty(betBeanList)) {
            Map<String, String> map = new HashMap<>();
            map.put("betBig", betBeanList.get(0).mBeatItemBeen.get(0).goldCount + "");
            map.put("betSmall", betBeanList.get(0).mBeatItemBeen.get(1).goldCount + "");
            map.put("betSingle", betBeanList.get(1).mBeatItemBeen.get(0).goldCount + "");
            map.put("betDouble", betBeanList.get(1).mBeatItemBeen.get(1).goldCount + "");
            map.put("betNine", betBeanList.get(2).mBeatItemBeen.get(0).goldCount + "");
            map.put("betTen", betBeanList.get(2).mBeatItemBeen.get(1).goldCount + "");
            map.put("betEleven", betBeanList.get(2).mBeatItemBeen.get(2).goldCount + "");
            map.put("betTwelve", betBeanList.get(2).mBeatItemBeen.get(3).goldCount + "");
            map.put("betSeven", betBeanList.get(2).mBeatItemBeen.get(4).goldCount + "");
            map.put("betEight", betBeanList.get(2).mBeatItemBeen.get(5).goldCount + "");
            map.put("betThirteen", betBeanList.get(2).mBeatItemBeen.get(6).goldCount + "");
            map.put("betFourteen", betBeanList.get(2).mBeatItemBeen.get(7).goldCount + "");
            map.put("betFive", betBeanList.get(2).mBeatItemBeen.get(8).goldCount + "");
            map.put("betSix", betBeanList.get(2).mBeatItemBeen.get(9).goldCount + "");
            map.put("betFifteen", betBeanList.get(2).mBeatItemBeen.get(10).goldCount + "");
            map.put("betSixteen", betBeanList.get(2).mBeatItemBeen.get(11).goldCount + "");
            map.put("betFour", betBeanList.get(2).mBeatItemBeen.get(12).goldCount + "");
            map.put("betSeventeen", betBeanList.get(2).mBeatItemBeen.get(13).goldCount + "");
            map.put("betLeopard", betBeanList.get(3).mBeatItemBeen.get(0).goldCount + "");
            map.put("gameTimes", GameRoomActivity.mGameTimes +1+ "");
            mBetpresenter.setBet(mUserId, mRoomId, map);
        }
    }


    @OnClick(R.id.image_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}