package com.android.xianicai.dicegame.gameroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.presenter.impl.GameRoomPresenterImpl;
import com.android.xianicai.dicegame.gameroom.provider.data.CheckRoomBean;
import com.android.xianicai.dicegame.gameroom.provider.data.GameResultBean;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.gameroom.view.GameRoomView;
import com.android.xianicai.dicegame.utils.RxBus;
import com.android.xianicai.dicegame.utils.StringUtil;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;
import com.android.xianicai.dicegame.widget.TipsDialog;
import com.android.xianicai.dicegame.widget.loading.LoadingView;
import com.android.xianicai.dicegame.widget.loading.ShapeLoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameRoomActivity extends BaseActivity implements GameRoomView {
    public int goldcount = 0;

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
    @BindView(R.id.image_ower_bg)
    ImageView mImageOwerBg;
    @BindView(R.id.loadView)
    LoadingView mLoadView;
    @BindView(R.id.imge_share)
    ImageView mImgeShare;
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
    private TipsDialog mResultDialog;
    private ShapeLoadingDialog mShapeLoadingDialog;

    @Override
    public int getlayoutId() {
        return R.layout.activity_game_room;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
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
            mImageOwerBg.setVisibility(View.GONE);
            mImageStartGame.setVisibility(View.VISIBLE);
            mImageDissmiaaRoom.setVisibility(View.VISIBLE);
        } else {
            mImageOwerLogo.setVisibility(View.VISIBLE);
            mImageBet.setVisibility(View.VISIBLE);
            mImageQuitRoom.setVisibility(View.VISIBLE);
            mImageOwerBg.setVisibility(View.VISIBLE);
            mImageStartGame.setVisibility(View.GONE);
            mImageDissmiaaRoom.setVisibility(View.GONE);
        }
        mTvMemberCount.setText("房间人数: " + mMemberCount);
        mTvRoomNumber.setText("房间号：" + roomDetailBean.getResult().getRoomId());
        if (StringUtil.isNotBlank(roomDetailBean.getResult().getLastResult())) {
            mTvLastResult.setText("上一期骰点：" + mLastResult);
        } else {
            mTvLastResult.setVisibility(View.GONE);
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
            RxBus.getDefault().post(Constant.RXBUS_CLOSE_BET);
            ToastUtil.showMessage(countBean.getMessage());
            finish();
            return;
        }
        //房间人数是否发生变化
        if (mMemberCount != countBean.getResult().getMemberCount()) {
            mMemberCount = countBean.getResult().getMemberCount();
            mTvMemberCount.setText("房间人数: " + mMemberCount);
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

    @Override
    public void gameReady(boolean b) {
        if (b) {
            mImgeDice.setImageResource(R.drawable.anim_game_ready);
            mAnimation = (AnimationDrawable) mImgeDice.getDrawable();
            mAnimation.start();
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    mRoomPresenter.startGame(mUserId, mRoomId, mGameTimes + 1);
                }
            }, 10000);
            //开始游戏

        }

    }

    /**
     * 骰子动画
     */
    private void startAnimation(final CheckRoomBean countBean) {
        mImgeDice.setImageResource(R.drawable.anim_dice);
        mAnimation = (AnimationDrawable) mImgeDice.getDrawable();
//        mImgeShare.setVisibility(View.GONE);
        mImgeDice.setVisibility(View.VISIBLE);
        mAnimation.start();
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mAnimation.stop();
                mImgeDice.setVisibility(View.GONE);
//                mImgeShare.setVisibility(View.VISIBLE);
                resultDialog(countBean);
                if (StringUtil.isNotBlank(mLastResult)) {
                    mTvLastResult.setVisibility(View.VISIBLE);
                    mTvLastResult.setText("上一期骰点：" + mLastResult);
                }
            }
        }, 3000);
    }

    @OnClick({R.id.image_start_game, R.id.image_bet, R.id.image_dissmiaa_room, R.id.image_add_gold,
            R.id.image_quit_room, R.id.imge_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_start_game:
//                mRoomPresenter.startGame(mUserId, mRoomId, mGameTimes + 1);
//                mRoomPresenter.gameReady(mUserId, mRoomId);
                mImgeDice.setVisibility(View.VISIBLE);
                mImgeDice.setImageResource(R.drawable.anim_game_ready);
                mAnimation = (AnimationDrawable) mImgeDice.getDrawable();
                mAnimation.start();
                if (mHandler == null) {
                    mHandler = new Handler();
                }
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        mAnimation.stop();
                        mImgeDice.setVisibility(View.GONE);
                        mRoomPresenter.startGame(mUserId, mRoomId, mGameTimes + 1);
                    }
                }, 10000);
                break;
            case R.id.image_bet:
                BetActivity.start(this, mUserId, mRoomId, goldcount);
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
            case R.id.imge_share:
                break;
        }
    }

    /**
     * 退出房间
     */
    private void exitRoom() {
        new TipsDialog(this).setMsg("是否退出当前房间?").setTwoListener(new TipsDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(TipsDialog dialog) {
                mRoomPresenter.quitRoom(mUserId, mRoomId);
                dialog.dismiss();
            }

            @Override
            public void onCancleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDetailBean != null && mDetailBean.getResult().getUserType() == 0) {
            if (mRoomState != 1) {
                mRoomPresenter.dismissRoom(mUserId, mRoomId);
            }
        } else {
            if (!mIsExitRoom && mRoomState != 1) {
                mRoomPresenter.quitRoom(mUserId, mRoomId);
            }

        }

    }

    /**
     * 解散房间的点击事件
     */
    private void dismissRoomClidked() {
        new TipsDialog(this).setMsg("是否解散当前房间，解散后无法恢复").setTwoListener(new TipsDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(TipsDialog dialog) {
                mRoomPresenter.dismissRoom(mUserId, mRoomId);
                dialog.dismiss();
            }

            @Override
            public void onCancleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();


    }

    /**
     * 充值金币
     */
    private void addDiamond() {
        new TipsDialog(this).setMsg("充值金币请联系客服微信：touwang001").setSingleListener(new TipsDialog.setOnSingleListener() {
            @Override
            public void onSingleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showSingle();
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
        mResultDialog = new TipsDialog(this).setMsg(mMsg).setSingleListener(new TipsDialog.setOnSingleListener() {
            @Override
            public void onSingleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showSingle();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
