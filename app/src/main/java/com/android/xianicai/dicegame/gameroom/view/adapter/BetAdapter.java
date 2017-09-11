package com.android.xianicai.dicegame.gameroom.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.gameroom.BetActivity;
import com.android.xianicai.dicegame.gameroom.provider.data.BetBean;

import java.util.List;

/**
 * Created by Zhanglibin on 2017/9/5.
 */

public class BetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BetBean> betBeanList;
    public BetItemAdapter mBetItemAdapter;


    public BetAdapter(Context context, List<BetBean> betBeanList) {
        this.context = context;
        this.betBeanList = betBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 4) {
            return -1;
        } else {
            return -2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView;
        if (viewType == -1) {
            mView = LayoutInflater.from(context).inflate(R.layout.bet_item, parent, false);
            return new BetVH(mView);
        } else {
            mView = LayoutInflater.from(context).inflate(R.layout.bet_item_image, parent, false);
            return new BetImageVH(mView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < 4) {
            ((BetVH)holder).mTvTitle.setText(betBeanList.get(position).title);
            ((BetVH)holder).mRecyclerView.setHasFixedSize(true);
            ((BetVH)holder).mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            mBetItemAdapter = new BetItemAdapter(context, betBeanList.get(position).mBeatItemBeen);
            ((BetVH)holder).mRecyclerView.setAdapter(mBetItemAdapter);
        } else {
            ((BetImageVH)holder).mImageBet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((BetActivity) context).bet();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class BetVH extends RecyclerView.ViewHolder {


        private final RecyclerView mRecyclerView;
        private final TextView mTvTitle;
        private final ImageView mImageBet;

        public BetVH(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mImageBet = (ImageView) itemView.findViewById(R.id.image_bet);
        }
    }

    class BetImageVH extends RecyclerView.ViewHolder {
        private final ImageView mImageBet;

        public BetImageVH(View itemView) {
            super(itemView);
            mImageBet = (ImageView) itemView.findViewById(R.id.image_bet);
        }
    }
}
