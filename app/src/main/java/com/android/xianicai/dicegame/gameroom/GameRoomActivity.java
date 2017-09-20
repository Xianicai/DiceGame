package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.presenter.impl.GameRoomPresenterImpl;
import com.android.xianicai.dicegame.gameroom.provider.data.CheckRoomBean;
import com.android.xianicai.dicegame.gameroom.provider.data.GameResultBean;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.gameroom.view.GameRoomView;
import com.android.xianicai.dicegame.utils.ConfirmDialog;
import com.android.xianicai.dicegame.utils.StringUtil;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;
import com.android.xianicai.dicegame.widget.loading.ShapeLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class GameRoomActivity extends BaseActivity implements GameRoomView {
    public static int goldcount = 1000;

    public static int mGameTimes = 0;
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
    @BindView(R.id.image_quit_room)
    ImageView mImageQuitRoom;
    private GameRoomPresenterImpl mRoomPresenter;
    private String mUserId;
    private String mRoomId;
    private RoomDetailBean mDetailBean;
    private Handler mHandler;
    private int mMemberCount;
    private String mLastResult;
    private int mRoomState = 0;
    private boolean mIsExitRoom = false;
    private AnimationDrawable mAnimation;
    private ConfirmDialog mResultDialog;
    private ShapeLoadingDialog mShapeLoadingDialog;

    @Override
    public int getlayoutId() {
        return R.layout.activity_game_room;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mImgeDice.setImageResource(R.drawable.anim_dice);
        mAnimation = (AnimationDrawable) mImgeDice.getDrawable();
        mImgeDice.setVisibility(View.GONE);
        mUserId = getIntent().getStringExtra("userId");
        mRoomId = getIntent().getStringExtra("roomId");
        mDetailBean = new RoomDetailBean();
        mRoomPresenter = new GameRoomPresenterImpl();
        mRoomPresenter.bindView(this);
        mRoomPresenter.getGameRoomDetail(mUserId, mRoomId);
    }


    @Override
    public void getGameRoomDetail(RoomDetailBean roomDetailBean) {
        mDetailBean = roomDetailBean;
        mMemberCount = roomDetailBean.getResult().getMemberCount();
        mGameTimes = roomDetailBean.getResult().getGameTimes();
        mLastResult = roomDetailBean.getResult().getLastResult();
        goldcount = roomDetailBean.getResult().getUserGoldCount();
        //检查房间情况
        mRoomPresenter.checkedRoom(mUserId, mRoomId);

        if (roomDetailBean.getResult().getUserType() == 0) {
            mImageOwerLogo.setVisibility(View.GONE);
            mImageBet.setVisibility(View.GONE);
            mImageQuitRoom.setVisibility(View.GONE);
            mImageStartGame.setVisibility(View.VISIBLE);
            mImageDissmiaaRoom.setVisibility(View.VISIBLE);
        } else {
            mImageOwerLogo.setVisibility(View.VISIBLE);
            mImageBet.setVisibility(View.VISIBLE);
            mImageQuitRoom.setVisibility(View.VISIBLE);
            mImageStartGame.setVisibility(View.GONE);
            mImageDissmiaaRoom.setVisibility(View.GONE);
        }
        mTvMemberCount.setText(mMemberCount + "/50");
        mTvRoomNumber.setText("房间号：" + roomDetailBean.getResult().getRoomId());
        if (StringUtil.isNotBlank(roomDetailBean.getResult().getLastResult())) {
            mTvLastResult.setText("上一期骰点：" + mLastResult);
        }
        mImageOwerLogo.setImage(roomDetailBean.getResult().getOwnerLogo());
        mImageUserLogo.setImage(roomDetailBean.getResult().getUserLogo());
        mTvUserName.setText(roomDetailBean.getResult().getUserName());
        mTvUserId.setText("ID：" + roomDetailBean.getResult().getUserId());
        mTvGoldCount.setText(goldcount + "");
    }

    @Override
    public void dismissRoom() {
        mRoomState = 1;
        finish();
    }

    @Override
    public void startGame(GameResultBean gameResultBean) {
    }

    @Override
    public void quitRoom() {
        mIsExitRoom = true;
        finish();
    }

    @Override
    public void checkedRoom(CheckRoomBean countBean) {

        //游戏房间是否被解散 0正常状态  1被解散
        mRoomState = countBean.getResult().getRoomState();
        if (countBean.getResult().getRoomState() == 1) {
            ToastUtil.showMessage(countBean.getMessage());
            finish();
            return;
        }
        //房间人数是否发生变化
        if (mMemberCount != countBean.getResult().getMemberCount()) {
            mMemberCount = countBean.getResult().getMemberCount();
            mTvMemberCount.setText(mMemberCount + "/50");
        }
        //游戏结果是否是发生变化(新一局游戏结果)
        if (mGameTimes != countBean.getResult().getGameTimes()) {
            //将上次的提示框销毁掉
            if (mResultDialog != null) {
                mResultDialog.dismiss();
            }
            mGameTimes = countBean.getResult().getGameTimes();
            mLastResult = countBean.getResult().getGameResult();
            startAnimation(countBean);

        }
        if (goldcount != countBean.getResult().getGoldCount()) {
            goldcount = countBean.getResult().getGoldCount();
            mTvGoldCount.setText(goldcount + "");
        }
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mRoomPresenter.checkedRoom(mUserId, mRoomId);
            }
        }, 2000);
    }

    /**
     * 骰子动画
     */
    private void startAnimation(final CheckRoomBean countBean) {
        cancelLoading();
        mImgeDice.setVisibility(View.VISIBLE);
        mAnimation.start();
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mAnimation.stop();
                mImgeDice.setVisibility(View.GONE);
                resultDialog(countBean);
                mTvLastResult.setText("上一期骰点：" + mLastResult);
            }
        }, 3000);
    }

    @OnClick({R.id.image_start_game, R.id.image_bet, R.id.image_dissmiaa_room, R.id.image_add_gold, R.id.image_quit_room})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_start_game:
                showLoading();
                mRoomPresenter.startGame(mUserId, mRoomId, mGameTimes + 1);
                break;
            case R.id.image_bet:
                BetActivity.start(this, mUserId, mRoomId);
                break;
            case R.id.image_dissmiaa_room:
                dismissRoomClidked();
                break;
            case R.id.image_add_gold:
//                PayActivity.start(this, mUserId);
                addDiamond();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDetailBean != null && mDetailBean.getResult().getUserType() == 0) {
            if (mRoomState != 1) {
                mRoomPresenter.dismissRoom(mUserId, mRoomId);
            }
        } else {
            if (!mIsExitRoom) {
                mRoomPresenter.quitRoom(mUserId, mRoomId);
            }

        }

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

    /**
     * 充值金币
     */
    private void addDiamond() {
        new ConfirmDialog(this).setMessage("充值金币请联系客服微信：touwang001").setSingleButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 游戏结果
     */
    private void resultDialog(CheckRoomBean countBean) {
        String mMsg;
        if (mDetailBean.getResult().getUserType() == 0) {
            mMsg = "本期游戏结果：" + countBean.getResult().getGameResult();
        } else {
            mMsg = "本期游戏结果：" + countBean.getResult().getGameResult() + "\n\n您的亏盈情况： " + countBean.getResult().getResultGain();
        }
        mResultDialog = new ConfirmDialog(this).setMessage(mMsg).setSingleButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        });
        mResultDialog.show();
    }

    /**
     * showloading
     */
    private void showLoading() {
        if (mShapeLoadingDialog == null) {
            mShapeLoadingDialog = new ShapeLoadingDialog(this);
        }
        mShapeLoadingDialog.setLoadingText("loading...");
        mShapeLoadingDialog.show();
    }

    /**
     * cancelLoading
     */
    private void cancelLoading() {
        if (mShapeLoadingDialog != null) {
            mShapeLoadingDialog.dismiss();
        }
    }

    public static void start(Context context, String userId, String roomId) {
        context.startActivity(new Intent(context, GameRoomActivity.class).putExtra("userId", userId).putExtra("roomId", roomId));
    }

    @Override
    protected void onResume() {
        //检查房间情况
        if (mRoomPresenter != null) {
            mRoomPresenter.checkedRoom(mUserId, mRoomId);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (mDetailBean != null && mDetailBean.getResult().getUserType() == 0) {
            dismissRoomClidked();
        } else {
            exitRoom();
        }
    }
}
