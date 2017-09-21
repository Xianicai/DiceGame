package com.android.xianicai.dicegame.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.utils.Mobile;
import com.android.xianicai.dicegame.utils.StringUtil;

/**
 * Created by Zhanglibin on 2017/9/21.
 */

public class TipsDialog extends AlertDialog {

    TextView mTvMsg;
    ImageView mImageSure;
    ImageView mImageCancle;
    ImageView mImageKnow;
    //    private Activity mContext;
//    private AlertDialog.Builder mBuilder;
//    private AlertDialog mDialog;
//    private View mView;
    // 内容
    private String mMessage;
    private DialogInterface.OnDismissListener mOnDismissListener;
    /**
     * 按钮数量
     */
    private int mBtnNum = 1;
    /**
     * 是否已经初始化
     */
    private boolean mHasInit = false;

    private boolean mCancelable = true;

    private int mDialogWidth;
    private int mDialogHight;
    private View mView;
    private setOnTwoListener mViewListener;
    private setOnSingleListener singleListener;

    public TipsDialog setTwoListener(setOnTwoListener viewListener) {
        mViewListener = viewListener;
        return this;
    }

    public TipsDialog setSingleListener(setOnSingleListener singleListener) {
        this.singleListener = singleListener;
        return this;
    }

    public TipsDialog(Context context) {
        this(context, 0);
    }

    protected TipsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected TipsDialog(Context context, @StyleRes int themeResId) {
        super(context, R.style.confirm_dialog);
        mView = View.inflate(context, R.layout.layout, null);
        mTvMsg = (TextView) mView.findViewById(R.id.tv_msg);
        mImageSure = (ImageView) mView.findViewById(R.id.image_sure);
        mImageCancle = (ImageView) mView.findViewById(R.id.image_cancle);
        mImageKnow = (ImageView) mView.findViewById(R.id.image_know);
        mImageSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onSureClicked(TipsDialog.this);

            }
        });
        mImageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onCancleClicked(TipsDialog.this);
            }
        });
        mImageKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singleListener.onSingleClicked(TipsDialog.this);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        mDialogHight = Mobile.SCREEN_HEIGHT * 8 / 10;
        mDialogWidth = Mobile.SCREEN_WIDTH * 6 / 10;
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.height = mDialogHight;
        lp.width = mDialogWidth;
        this.getWindow().setAttributes(lp);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
    }

    /**
     * 设置内容
     */
    public TipsDialog setMsg(String message) {
        this.mMessage = message;
        if (StringUtil.isNotBlank(mMessage)) {
            mTvMsg.setText(mMessage);
        }
        return this;
    }

    /**
     * 显示Dialog
     */
    public TipsDialog showTwo() {
        mImageSure.setVisibility(View.VISIBLE);
        mImageCancle.setVisibility(View.VISIBLE);
        mImageKnow.setVisibility(View.GONE);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        show();
        return this;
    }

    /**
     * 显示Dialog
     */
    public TipsDialog showSingle() {
        mImageSure.setVisibility(View.GONE);
        mImageCancle.setVisibility(View.GONE);
        mImageKnow.setVisibility(View.VISIBLE);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        show();
        return this;
    }

    public interface setOnTwoListener {
        void onSureClicked(TipsDialog dialog);

        void onCancleClicked(TipsDialog dialog);
    }

    public interface setOnSingleListener {

        void onSingleClicked(TipsDialog dialog);
    }
}
