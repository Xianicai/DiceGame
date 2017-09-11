package com.android.xianicai.dicegame.gameroom.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.gameroom.provider.data.BeatItemBean;
import com.android.xianicai.dicegame.widget.BetNumChangeLayout;

import java.util.List;

/**
 * Created by Zhanglibin on 2017/9/6.
 */

public class BetItemAdapter extends RecyclerView.Adapter<BetItemAdapter.BeItemtVH> {
    private Context context;
    public List<BeatItemBean> mBeatItemBeen;

    public BetItemAdapter(Context context, List<BeatItemBean> mBeatItemBeen) {
        this.context = context;
        this.mBeatItemBeen = mBeatItemBeen;
    }

    @Override
    public BeItemtVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bet_item_view, parent, false);
        BeItemtVH holder = new BeItemtVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BeItemtVH holder, final int position) {
        holder.mView.setTopICon(mBeatItemBeen.get(position).icon);
        mBeatItemBeen.get(position).goldCount = 0;
        holder.mView.setViewListener(new BetNumChangeLayout.setOnViewListener() {
            @Override
            public void onMinusClicked(int count) {
                mBeatItemBeen.get(position).goldCount = count;
            }

            @Override
            public void onAddClicked(int count) {
                mBeatItemBeen.get(position).goldCount = count;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBeatItemBeen.size();
    }

    class BeItemtVH extends RecyclerView.ViewHolder {


        private final BetNumChangeLayout mView;

        public BeItemtVH(View itemView) {
            super(itemView);
            mView = (BetNumChangeLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
