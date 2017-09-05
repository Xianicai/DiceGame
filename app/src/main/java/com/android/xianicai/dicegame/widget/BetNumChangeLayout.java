package com.android.xianicai.dicegame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.gameroom.GameRoomActivity;
import com.android.xianicai.dicegame.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class BetNumChangeLayout extends LinearLayout {

    @BindView(R.id.image_minus)
    ImageView mImageMinus;
    @BindView(R.id.image_add)
    ImageView mImageAdd;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.image_icon)
    ImageView mImageIcon;

    private int goldCount = 100;
    private int mTopICon;

    public BetNumChangeLayout(Context context) {
        this(context, null);
    }

    public BetNumChangeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BetNumChangeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberChangeLayout);
        mTopICon = ta.getResourceId(R.styleable.BetNumChangeLayout_imageIcon, 0);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.bet_change_number, this);
        ButterKnife.bind(this);
        if (mTopICon != 0) {
            mImageIcon.setImageResource(mTopICon);
        }

        mTvNumber.setText(goldCount + "");
    }

    @OnClick({R.id.image_minus, R.id.image_add})
    public void onViewClicked(View view) {
        int number;
        number = Integer.parseInt(mTvNumber.getText().toString());
        switch (view.getId()) {
            case R.id.image_minus:

                if (number > goldCount) {
                    GameRoomActivity.goldcount += 100;
                    mTvNumber.setText((number - goldCount) + "");
                }

                break;
            case R.id.image_add:
                if (GameRoomActivity.goldcount < 100) {
                    ToastUtil.showMessage("您的金币不足");
                } else {
                    mTvNumber.setText((goldCount + number) + "");
                    GameRoomActivity.goldcount -= 100;
                }
                break;
        }

    }

    /**
     * 设置增加和减少的 倍数（同时也是初始化的值）
     */
    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
        mTvNumber.setText(goldCount + "");
    }

    public void setTopICon(@Nullable int topICon) {
        mImageIcon.setImageResource(topICon);
    }

    public int getGoldCount() {
        return Integer.parseInt(mTvNumber.getText().toString());
    }
}
