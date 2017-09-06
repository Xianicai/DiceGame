package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.presenter.impl.GameRoomPresenterImpl;
import com.android.xianicai.dicegame.gameroom.provider.data.GameResultBean;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.gameroom.view.GameRoomView;
import com.android.xianicai.dicegame.pay.PayActivity;
import com.android.xianicai.dicegame.utils.ConfirmDialog;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class GameRoomActivity extends BaseActivity implements GameRoomView {
    public static int goldcount = 1000;

    @BindView(R.id.tv_room_number)
    TextView mTvRoomNumber;
    @BindView(R.id.tv_member_count)
    TextView mTvMemberCount;
    @BindView(R.id.tv_last_result)
    TextView mTvLastResult;
    @BindView(R.id.image_ower_logo)
    GlideImageView mImageOwerLogo;
    @BindView(R.id.imge_dice)
    ImageView mImgeDice;
    @BindView(R.id.image_start_game)
    ImageView mImageStartGame;
    @BindView(R.id.image_bet)
    ImageView mImageBet;
    @BindView(R.id.image_dissmiaa_room)
    ImageView mImageDissmiaaRoom;
    @BindView(R.id.tv_gold_count)
    TextView mTvGoldCount;
    @BindView(R.id.image_add_gold)
    ImageView mImageAddGold;
    @BindView(R.id.image_user_logo)
    GlideImageView mImageUserLogo;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_id)
    TextView mTvUserId;
    @BindView(R.id.image_bg_gold)
    ImageView mImageBgGold;
    @BindView(R.id.image_gold)
    ImageView mImageGold;
    @BindView(R.id.image_quit_room)
    ImageView mImageQuitRoom;
    private GameRoomPresenterImpl mRoomPresenter;
    private String mUserId;
    private String mRoomId;

    @Override
    public int getlayoutId() {
        return R.layout.activity_game_room;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra("userId");
        mRoomId = getIntent().getStringExtra("roomId");
        mRoomPresenter = new GameRoomPresenterImpl();
        mRoomPresenter.bindView(this);
        mRoomPresenter.getGameRoomDetail(mUserId, mRoomId);
    }


    @Override
    public void getGameRoomDetail(RoomDetailBean RoomBean) {

    }

    @Override
    public void dismissRoom() {
        finish();
    }

    @Override
    public void startGame(GameResultBean gameResultBean) {

    }

    @Override
    public void quitRoom() {
        finish();
    }

    @OnClick({R.id.image_start_game, R.id.image_bet, R.id.image_dissmiaa_room, R.id.image_add_gold, R.id.image_quit_room})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_start_game:
                mRoomPresenter.startGame(mUserId, mRoomId);
                break;
            case R.id.image_bet:
                BetActivity.start(this, mUserId, mRoomId);
                break;
            case R.id.image_dissmiaa_room:
                dismissRoomClidked();
                break;
            case R.id.image_add_gold:
                PayActivity.start(this, mUserId);
                break;
            case R.id.image_quit_room:
                exitRoom();

                break;
        }
    }

    /**
     * 退出房间
     */
    private void exitRoom() {
        new ConfirmDialog(this).setMessage("是否退出当前房间?").setTwoButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                mRoomPresenter.quitRoom(mUserId, mRoomId);
                dialog.dismiss();

            }
        }, new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 解散房间的点击事件
     */
    private void dismissRoomClidked() {
        new ConfirmDialog(this).setMessage("是否解散当前房间，解散后无法恢复").setTwoButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                mRoomPresenter.dismissRoom(mUserId, mRoomId);
                dialog.dismiss();

            }
        }, new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();

    }

    public static void start(Context context, String userId, String roomId) {
        context.startActivity(new Intent(context, GameRoomActivity.class).putExtra("userId", userId).putExtra("roomId", roomId));
    }
}
