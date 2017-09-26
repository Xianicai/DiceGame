package com.android.xianicai.dicegame.gameroom.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.utils.glide.GlideImageView;

/**
 * Created by Zhanglibin on 2017/9/26.
 */

public class LeftAdappter extends RecyclerView.Adapter<LeftAdappter.LeftVH> {
    private  Context mContext;

    public LeftAdappter(Context context) {
        mContext = context;
    }


    @Override
    public LeftVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_user_item, parent, false);
        LeftVH viewHolder = new LeftVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeftVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class LeftVH extends ViewHolder {

        private final GlideImageView mUserLogo;
        private final TextView mUserNname;
        private final TextView mUserId;

        public LeftVH(View itemView) {
            super(itemView);
            mUserLogo = (GlideImageView) itemView.findViewById(R.id.image_user_logo);
            mUserNname = (TextView) itemView.findViewById(R.id.tv_user_name);
            mUserId = (TextView) itemView.findViewById(R.id.tv_user_id);


        }
    }
}
