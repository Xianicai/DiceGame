package com.android.xianicai.dicegame.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;

/**
 * Created by Zhanglibin on 2017/9/2.
 */

public class ConfirmDialog {
    TextView mTvMsg;
    ImageView mImageSure;
    ImageView mImageCancle;
    ImageView mImageKnow;
    private Activity mContext;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private View mView;
    // 内容
    private String mMessage;
    private OnConfirmDialogClickListener mBtn1Listener;
    private OnConfirmDialogClickListener mBtn2Listener;
    private OnConfirmDialogClickListener mSingleBtnListener;
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
    private final View mLayout;

    public ConfirmDialog(Activity context) {
        this.mContext = context;
        mBuilder = new AlertDialog.Builder(context);
        mView = LayoutInflater.from(context).inflate(R.layout.confirm_dialog, null);
        mLayout = mView.findViewById(R.id.layout);
        mTvMsg = (TextView) mView.findViewById(R.id.tv_msg);
        mImageSure = (ImageView) mView.findViewById(R.id.image_sure);
        mImageCancle = (ImageView) mView.findViewById(R.id.image_cancle);
        mImageKnow = (ImageView) mView.findViewById(R.id.image_know);
        mBuilder.setView(mView);
//         初始化Dialog的宽度，屏幕的6/7
        mDialogHight = Mobile.SCREEN_HEIGHT * 8/ 10;
    }

    /**
     * 设置内容
     */
    public ConfirmDialog setMessage(String message) {
        this.mMessage = message;
        if (StringUtil.isNotBlank(mMessage)) {
            mTvMsg.setText(mMessage);
        }
        return this;
    }

    /**
     * 设置单个按钮Dialog的文字和点击事件
     *
     * @param l 点击事件，点击事件为空则会设置一个默认事件---点击关闭Dialog
     */
    public ConfirmDialog setSingleButtonListener(OnConfirmDialogClickListener l) {
        this.mSingleBtnListener = l;
        mBtnNum = 1;
        return this;
    }

    /**
     * 设置两个按钮Dialog的文字和点击事件，点击事件为空则会设置一个默认事件---点击关闭Dialog
     *
     * @param l1 第一个按钮的点击事件
     * @param l2 第二个按钮的点击事件
     */
    public ConfirmDialog setTwoButtonListener(OnConfirmDialogClickListener l1, OnConfirmDialogClickListener l2) {
        this.mBtn1Listener = l1;
        this.mBtn2Listener = l2;
        mBtnNum = 2;
        return this;
    }

    /**
     * 显示Dialog
     */
    public void show() {
        if (!mHasInit) {
            // 初始化message
            if (mHasInit && StringUtil.isNotBlank(mMessage)) {
                mTvMsg.setText(mMessage);
            }
            // 初始化按钮
            // 1个按钮时
            if (mBtnNum == 1) {
                if (mSingleBtnListener == null) {
                    mImageKnow.setOnClickListener(new DialogDismissListener());
                } else {
                    mImageKnow.setOnClickListener(new OnDialogButtonClick(mSingleBtnListener));
                }
                mImageKnow.setVisibility(View.VISIBLE);
                mLayout.setVisibility(View.GONE);
                mImageSure.setVisibility(View.GONE);
                mImageCancle.setVisibility(View.GONE);
            } // 2个按钮时
            else {
                if (mBtn1Listener == null) {
                    mImageSure.setOnClickListener(new DialogDismissListener());
                } else {
                    mImageSure.setOnClickListener(new OnDialogButtonClick(mBtn1Listener));
                }
                if (mBtn2Listener == null) {
                    mImageCancle.setOnClickListener(new DialogDismissListener());
                } else {
                    mImageCancle.setOnClickListener(new OnDialogButtonClick(mBtn2Listener));
                }
                mImageKnow.setVisibility(View.GONE);
                mLayout.setVisibility(View.VISIBLE);
                mImageSure.setVisibility(View.VISIBLE);
                mImageCancle.setVisibility(View.VISIBLE);
            }
            mDialog = mBuilder.create();
            mDialog.setOnDismissListener(mOnDismissListener);
            mDialog.setCancelable(mCancelable);
            mDialog.setCanceledOnTouchOutside(mCancelable);
            mHasInit = true;

        }

        try {
            if (mContext != null && !mContext.isFinishing()) {
                mDialog.show();
                // 设置Dialog宽度
                WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
                lp.height = mDialogHight;
                mDialog.getWindow().setAttributes(lp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dialog是否显示了
     */
    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }


    /**
     * 隐藏Dialog
     */
    public void dismiss() {
        mDialog.dismiss();
    }

    private class DialogDismissListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mDialog.dismiss();
        }

    }


    public interface OnConfirmDialogClickListener {
        void onClick(ConfirmDialog dialog, View v);
    }

    private class OnDialogButtonClick implements View.OnClickListener {
        private OnConfirmDialogClickListener mOnConfirmDialogClickListener;

        public OnDialogButtonClick(OnConfirmDialogClickListener l) {
            this.mOnConfirmDialogClickListener = l;
        }

        @Override
        public void onClick(View v) {
            if (mOnConfirmDialogClickListener != null) {
                mOnConfirmDialogClickListener.onClick(ConfirmDialog.this, v);
            }
        }
    }
}
