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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class NumberChangeLayout extends LinearLayout {

    @BindView(R.id.image_minus)
    ImageView mImageMinus;
    @BindView(R.id.image_add)
    ImageView mImageAdd;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    private int mLayoutLeftImge;
    private int mLayoutRightImge;
    private int goldCount = 100;

    public NumberChangeLayout(Context context) {
        this(context, null);
    }

    public NumberChangeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberChangeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberChangeLayout);
        mLayoutLeftImge = ta.getResourceId(R.styleable.NumberChangeLayout_leftImage, 0);
        mLayoutRightImge = ta.getResourceId(R.styleable.NumberChangeLayout_rightImage, 0);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.widget_change_number, this);
        ButterKnife.bind(this);
        if (mLayoutLeftImge != 0) {
            mImageMinus.setImageResource(mLayoutLeftImge);
        }
        if (mLayoutRightImge != 0) {
            mImageAdd.setImageResource(mLayoutRightImge);
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
                    mTvNumber.setText((number - goldCount) + "");
                }
                break;
            case R.id.image_add:
                mTvNumber.setText((goldCount + number) + "");
                break;
        }
    }
/**
 * 设置增加和减少的 倍数（同时也是初始化的值）
 * */
    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
        mTvNumber.setText(goldCount + "");
    }

    public void setLeftRirghtImg(@Nullable int leftImg, @Nullable int rightImg) {
        mImageMinus.setImageResource(leftImg);
        mImageAdd.setImageResource(rightImg);
    }

    public int getGoldCount() {
        return Integer.parseInt(mTvNumber.getText().toString())/goldCount;
    }
}
