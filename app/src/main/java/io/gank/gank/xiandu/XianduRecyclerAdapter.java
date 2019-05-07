package io.gank.gank.xiandu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.gank.gank.R;

/**
 * Created by yinzhegang on 2019/3/19.
 */

public class XianduRecyclerAdapter extends RecyclerView.Adapter <XianduRecyclerAdapter.XianduViewHoder> {
    OnItemClickListener listener;
    List<CategoriesData.ResultsBean> items;
    Context context;
    @Override
    public XianduViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XianduViewHoder(LayoutInflater.from(context).inflate(R.layout.xiandu_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(XianduViewHoder holder, final int position) {
        Picasso.with(context).load(items.get(position).getIcon())
                .centerCrop()
                .fit()
                .into(holder.categoryIcon);
        holder.categoryTitle.setText(items.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    listener.itemClick(view,items.get(position).getId());
            }
        });
    }

    public XianduRecyclerAdapter(Context context, List<CategoriesData.ResultsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class XianduViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.category_icon)
        public ImageView categoryIcon;
        @BindView(R.id.category_title)
        public TextView categoryTitle;
        public XianduViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    interface OnItemClickListener{
        void itemClick(View view,String id);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
