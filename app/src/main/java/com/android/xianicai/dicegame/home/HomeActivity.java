package com.android.xianicai.dicegame.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
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
import com.android.xianicai.dicegame.utils.ConfirmDialog;
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
    private UserPresenterImpl mUserPresenter;
    private AlertDialog mDialog;
    private String mUserId;
    private UserBean mUserBean;

    @Override
    public int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        statAnimator();
        mUserBean = new UserBean();
        String code = getIntent().getStringExtra("code");
        mUserPresenter = new UserPresenterImpl();
        mUserPresenter.bindView(this);
        mUserPresenter.login(code, "1");
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
        mImageHead.setRoundImage(userBean.getResult().getUserLogo(), 4);
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
            GameRoomActivity.start(this, mUserId, creatRoomBean.getResult().getRoomId());
        }
    }

    @Override
    public void joinRoomSuccess(CreatRoomBean roomBean) {
        if (roomBean != null && StringUtil.isNotBlank(roomBean.getResult().getRoomId())) {
            mDialog.dismiss();
            GameRoomActivity.start(this, mUserId, roomBean.getResult().getRoomId());
        }

    }

    @Override
    public void joinRoomFaild(String msg) {
        ToastUtil.showMessage(msg);

    }

    //跳转到APP首页
    public static void start(Context context, String code) {
        context.startActivity(new Intent(context, HomeActivity.class).putExtra("code", code));

    }

    @OnClick({R.id.image_add_diamond, R.id.image_add_gold, R.id.image_finish, R.id.image_creat_room, R.id.image_join_room})
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
        }
    }

    /**
     * 加入房间
     */
    private void joinRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.join_room_dialog, null);
        ImageView imageSure = (ImageView) view.findViewById(R.id.image_sure);
        ImageView imageCancle = (ImageView) view.findViewById(R.id.image_cancle);
        final EditText edRoomNumber = (EditText) view.findViewById(R.id.ed_room_number);
        // 设置视图
        builder.setView(view);
        mDialog = builder.create();
        // 确定
        imageSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomId = edRoomNumber.getText().toString();
                if (StringUtil.isNotBlank(roomId) && roomId.length() == 6) {
                    mUserPresenter.joinRoom(mUserId, roomId);
                } else {
                    ToastUtil.showMessage("房间号有误，请重新输入");
                }
            }
        });
        // 取消
        imageCancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    /**
     * 创建房间
     */
    private void creatRoom() {
        new ConfirmDialog(this).setMessage("创建房间将消费10个钻石，是否创建？").setTwoButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                if (dialog != null) {
                    //创建房间
                    mUserPresenter.creatRomm(mUserId);
                    dialog.dismiss();
                }
            }
        }, new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 充值砖石
     */
    private void addDiamond(String msg) {
        new ConfirmDialog(this).setMessage("充值" + msg + "请联系客服微信：touwang001").setSingleButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void onBackPressed() {
        new ConfirmDialog(this).setMessage("是否关闭游戏?").setTwoButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
                finish();
            }
        }, new ConfirmDialog.OnConfirmDialogClickListener() {
            @Override
            public void onClick(ConfirmDialog dialog, View v) {
                dialog.dismiss();
            }
        }).show();
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

}