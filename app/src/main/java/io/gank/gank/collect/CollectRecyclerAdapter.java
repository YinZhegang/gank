package io.gank.gank.collect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.gank.gank.R;
import io.gank.gank.sqlBean.ResourceBean;

/**
 * Created by yinzhegang on 2019/3/26.
 */

public class CollectRecyclerAdapter extends RecyclerView.Adapter <CollectRecyclerAdapter.CollectViewHoder>{
    Context context;
    List<ResourceBean> items;
    @Override
    public CollectViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectViewHoder(LayoutInflater.from(context).inflate(R.layout.sort_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CollectViewHoder holder, int position) {
            holder.sourceTitle.setText(items.get(position).getDesc());
            holder.sourceTime.setText(items.get(position).getCreate());
            holder.sourceWriter.setText(items.get(position).getWho());


    }

    public CollectRecyclerAdapter(Context context, List<ResourceBean> items) {
        this.context = context;
        this.items=items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CollectViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.source_title)
        public TextView sourceTitle;
        @BindView(R.id.source_time)
        public TextView sourceTime;
        @BindView(R.id.source_writer)
        public TextView sourceWriter;
        public CollectViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
