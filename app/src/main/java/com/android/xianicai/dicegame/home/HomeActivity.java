package com.android.xianicai.dicegame.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.gameroom.GameRoomActivity;
import com.android.xianicai.dicegame.gameroom.provider.data.RoomDetailBean;
import com.android.xianicai.dicegame.home.presenter.impl.UserPresenterImpl;
import com.android.xianicai.dicegame.home.provider.data.CreatRoomBean;
import com.android.xianicai.dicegame.home.provider.data.UserBean;
import com.android.xianicai.dicegame.home.view.HomeView;
import com.android.xianicai.dicegame.pay.PayActivity;
import com.android.xianicai.dicegame.utils.ConfirmDialog;
import com.android.xianicai.dicegame.utils.StringUtil;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
//        statAnimator();
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
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(mImageLightTop, "translationX", 1200, -300);
        transXAnim.setRepeatCount(-1);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(transXAnim);
        set.setDuration(4000);
        set.start();
    }

    @Override
    public void login(UserBean userBean) {
        mUserBean = userBean;
        mUserId = userBean.getResult().getUserId();
        mImageHead.setRoundImage(userBean.getResult().getUserLogo(),4);
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
    public void joinRoomSuccess(RoomDetailBean roomBean) {
        if (roomBean != null && StringUtil.isNotBlank(roomBean.getResult().getRoomId())) {
            mDialog.dismiss();
            GameRoomActivity.start(this, mUserId, roomBean.getResult().getRoomId());
            finish();
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
                addDiamond();
                break;
            case R.id.image_add_gold:
                PayActivity.start(this, mUserId);
                break;
            case R.id.image_finish:
                onBackPressed();
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
                GameRoomActivity.start(HomeActivity.this, mUserId, "123456");
//                if (dialog != null) {
//                    //创建房间
//                    mUserPresenter.creatRomm(mUserId);
//                    dialog.dismiss();
//                }
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
    private void addDiamond() {
        new ConfirmDialog(this).setMessage("充值钻石请联系客服微信：touwang001").setSingleButtonListener(new ConfirmDialog.OnConfirmDialogClickListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}