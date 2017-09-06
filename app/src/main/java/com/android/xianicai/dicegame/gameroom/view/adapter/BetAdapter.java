package com.android.xianicai.dicegame.gameroom.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.gameroom.provider.data.BetBean;

import java.util.List;

/**
 * Created by Zhanglibin on 2017/9/5.
 */

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.BetVH> {
    private Context context;
    private List<BetBean> betBeanList;

    public BetAdapter(Context context, List<BetBean> betBeanList) {
        this.context = context;
        this.betBeanList = betBeanList;
    }

    @Override
    public BetVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bet_item, parent, false);
        BetVH holder = new BetVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BetVH holder, int position) {
        holder.mTvTitle.setText(betBeanList.get(position).title);
        holder.mRecyclerView.setHasFixedSize(true);
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
//        List<BeatItemBean> mBeatItemBeen = new ArrayList<>();
        BetItemAdapter betItemAdapter = new BetItemAdapter(context, betBeanList.get(position).mBeatItemBeen);
        holder.mRecyclerView.setAdapter(betItemAdapter);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BetVH extends RecyclerView.ViewHolder {


        private final RecyclerView mRecyclerView;
        private final TextView mTvTitle;

        public BetVH(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
