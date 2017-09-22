package com.android.xianicai.dicegame.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.base.BaseApplication;
import com.android.xianicai.dicegame.gameroom.GameRoomActivity;
import com.android.xianicai.dicegame.home.presenter.impl.UserPresenterImpl;
import com.android.xianicai.dicegame.home.provider.data.CreatRoomBean;
import com.android.xianicai.dicegame.home.provider.data.UserBean;
import com.android.xianicai.dicegame.home.view.HomeView;
import com.android.xianicai.dicegame.utils.Mobile;
import com.android.xianicai.dicegame.utils.StringUtil;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.image_head)
    GlideImageView mImageHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_id)
    TextView mTvUserId;
    @BindView(R.id.tv_diamond_count)
    TextView mTvDiamondCount;
    @BindView(R.id.tv_gold_count)
    TextView mTvGoldCount;
    @BindView(R.id.image_add_gold)
    ImageView mImageAddGold;
    @BindView(R.id.image_creat_room)
    ImageView mImageCreatRoom;
    @BindView(R.id.image_join_room)
    ImageView mImageJoinRoom;
    @BindView(R.id.image_light_top)
    ImageView mImageLightTop;
    @BindView(R.id.image_light_bottom)
    ImageView mImageLightBottom;
    @BindView(R.id.tv_home_notice)
    TextView mTvHomeNotice;
    @BindView(R.id.image_notice)
    ImageView mImageNotice;
    private UserPresenterImpl mUserPresenter;
    private String mUserId;
    private UserBean mUserBean;
    private EditDialog mEditDialog;

    @Override
    public int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {


        statAnimator();
        mUserBean = new UserBean();
        mUserId = getIntent().getStringExtra("userId");
        mUserPresenter = new UserPresenterImpl();
        mUserPresenter.bindView(this);
//        mUserPresenter.login(code, "1");
        mUserPresenter.refreshUser(mUserId);
    }

    /**
     * 开始动画
     */
    private void statAnimator() {
        ObjectAnimator transXAnimTop = ObjectAnimator.ofFloat(mImageLightTop, "translationX", Mobile.SCREEN_WIDTH, -Mobile.SCREEN_WIDTH);
        ObjectAnimator transXAnimBot = ObjectAnimator.ofFloat(mImageLightBottom, "translationX", -Mobile.SCREEN_WIDTH, Mobile.SCREEN_WIDTH);
        transXAnimTop.setRepeatCount(-1);
        transXAnimBot.setRepeatCount(-1);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(transXAnimTop, transXAnimBot);
        set.setDuration(4000);
        set.start();
    }

    @Override
    public void login(UserBean userBean) {
        mUserBean = userBean;
        mUserId = userBean.getResult().getUserId();
        mImageHead.setImage(userBean.getResult().getUserLogo());
        mTvUserName.setText(userBean.getResult().getUserName());
        mTvUserId.setText("ID:" + userBean.getResult().getUserId());
        mTvDiamondCount.setText(userBean.getResult().getDiamondCount() + "");
        mTvGoldCount.setText(userBean.getResult().getGoldCount() + "");

    }

    @Override
    public void loginFaild() {
//        finish();
    }

    @Override
    public void creatRoom(CreatRoomBean creatRoomBean) {
        if (creatRoomBean != null && StringUtil.isNotBlank(creatRoomBean.getResult().getRoomId())) {
            mUserBean.getResult().setDiamondCount(mUserBean.getResult().getDiamondCount() - 10);
            mTvDiamondCount.setText(mUserBean.getResult().getDiamondCount() + "");
            GameRoomActivity.start(this, mUserId, creatRoomBean.getResult().getRoomId());
        }
    }

    @Override
    public void joinRoomSuccess(CreatRoomBean roomBean) {
        if (roomBean != null && StringUtil.isNotBlank(roomBean.getResult().getRoomId())) {
            if (mEditDialog != null) {
                mEditDialog.dismiss();
            }
            GameRoomActivity.start(this, mUserId, roomBean.getResult().getRoomId());
        }

    }

    @Override
    public void joinRoomFaild(String msg) {
        ToastUtil.showMessage(msg);
        if (mEditDialog != null) {
            mEditDialog.dismiss();
        }
    }

    @Override
    public void refreshUser(UserBean userBean) {
        mUserBean = userBean;
        mUserId = userBean.getResult().getUserId();
        mImageHead.setImage(userBean.getResult().getUserLogo());
        mTvUserName.setText(userBean.getResult().getUserName());
        mTvUserId.setText("ID:" + userBean.getResult().getUserId());
        mTvDiamondCount.setText(userBean.getResult().getDiamondCount() + "");
        mTvGoldCount.setText(userBean.getResult().getGoldCount() + "");

    }

    //跳转到APP首页
    public static void start(Context context, String userId) {
        context.startActivity(new Intent(context, HomeActivity.class).putExtra("userId", userId));

    }

    @OnClick({R.id.image_add_diamond, R.id.image_add_gold, R.id.image_finish, R.id.image_creat_room, R.id.image_join_room, R.id.image_renovate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_add_diamond:
                addDiamond("钻石");
                break;
            case R.id.image_add_gold:
                addDiamond("金币");
//                PayActivity.start(this, mUserId);
                break;
            case R.id.image_finish:
                onBackPressed();
                //分享到微信好友
//                sharToWeixin(0);
                //分享到微信朋友圈
//                sharToWeixin(1);
                break;
            case R.id.image_creat_room:
                creatRoom();
                break;
            case R.id.image_join_room:
                joinRoom();
                break;
            case R.id.image_renovate:
                mUserPresenter.refreshUser(mUserId);
                break;
        }
    }

    /**
     * 加入房间
     */
    private void joinRoom() {

        mEditDialog = new EditDialog(this).setTwoListener(new EditDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(EditDialog dialog, String str) {
                if (StringUtil.isNotBlank(str) && str.length() == 6) {
                    mUserPresenter.joinRoom(mUserId, str);
                } else {
                    ToastUtil.showMessage("房间号有误，请重新输入");
                }
            }

            @Override
            public void onCancleClicked(EditDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();
        mEditDialog.setEditListener(new EditDialog.setOnEditListener() {
            @Override
            public void onEditClicked(EditText editText) {
                showSoftInput(HomeActivity.this, editText);
            }
        });
    }

    /**
     * 创建房间
     */
    private void creatRoom() {
        new TipsDialog(this).setMsg("创建房间将消费10个钻石，是否创建？").setTwoListener(new TipsDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(TipsDialog dialog) {
                //创建房间
                mUserPresenter.creatRomm(mUserId);
                dialog.dismiss();
            }

            @Override
            public void onCancleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();
    }

    /**
     * 充值砖石
     */
    private void addDiamond(String msg) {
        new TipsDialog(this).setMsg("充值" + msg + "请联系客服微信：touwang001").setSingleListener(new TipsDialog.setOnSingleListener() {
            @Override
            public void onSingleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showSingle();
    }

    @Override
    public void onBackPressed() {
        new TipsDialog(this).setMsg("是否关闭游戏?").setTwoListener(new TipsDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(TipsDialog dialog) {
                dialog.dismiss();
                finish();
            }

            @Override
            public void onCancleClicked(TipsDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();

    }

    private void sharToWeixin(int flag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "https://fir.im/qn61";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "骰子娱乐";
        msg.description = "最好玩的游戏";
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        BaseApplication.api.sendReq(req);
    }

    /**
     * 弹起软键盘
     */
    public static void showSoftInput(Context activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}