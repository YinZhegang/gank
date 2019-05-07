package io.gank.gank.sort;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.gank.gank.R;
import io.gank.gank.sort.data.SortData;


/**
 * Created by yinzhegang on 2019/3/15.
 */

public class SortXrecyclerAdapter extends XRecyclerView.Adapter <SortXrecyclerAdapter.SortViewHoder> {
    OnItemClickListener listener;
    private Context context;
    private List<SortData.ResultsBean> items;
    @Override
    public SortViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.e("ooxxss",viewType+"");
        View view;
        switch (viewType){
            case 0:
                view =LayoutInflater.from(context).inflate(R.layout.sort_item,parent,false);
                break;
            case 1:
                view =LayoutInflater.from(context).inflate(R.layout.sort_item_1,parent,false);
                break;
            case 2:
                view =LayoutInflater.from(context).inflate(R.layout.sort_item_2,parent,false);
                break;
            case 3:
                view =LayoutInflater.from(context).inflate(R.layout.sort_item_3,parent,false);
                break;
            default:
                view =LayoutInflater.from(context).inflate(R.layout.sort_item,parent,false);
        }
        SortViewHoder sortViewHoder = new SortViewHoder(view,viewType);
        return sortViewHoder;
    }

    public SortXrecyclerAdapter(Context context, List<SortData.ResultsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(final SortViewHoder holder, final int position) {
        switch (holder.viewType){
            case 0:
                break;
            case 1:
                Picasso.with(context).load(items.get(position).getImages().get(0))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg1);
                break;
            case 2:
                Picasso.with(context).load(items.get(position).getImages().get(0))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg1);
                Picasso.with(context).load(items.get(position).getImages().get(1))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg2);
                break;
            case 3:
                Picasso.with(context).load(items.get(position).getImages().get(0))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg1);
                Picasso.with(context).load(items.get(position).getImages().get(1))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg2);
                Picasso.with(context).load(items.get(position).getImages().get(2))
                        .centerCrop()
                        .fit()
                        .into(holder.sourceImg3);
                break;
        }
                holder.sourceTitle.setText(items.get(position).getDesc());
                String create = items.get(position).getCreatedAt();
                String time = create.substring(0,4)+"年" +create.substring(5,7)+"月"+create.substring(8,10)+"日";
                holder.sourceTime.setText(time);
                holder.sourceWriter.setText(items.get(position).getWho());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.ItemClick(holder.itemView,items.get(position));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
            if(items.get(position).getImages()==null){
                return 0;
            }else {
                return items.get(position).getImages().size();
            }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class SortViewHoder extends XRecyclerView.ViewHolder{
        public int viewType;
//        @BindView(R.id.source_img1)
        public ImageView sourceImg1;
//        @BindView(R.id.source_img2)
        public ImageView sourceImg2;
//        @BindView(R.id.source_img3)
        public ImageView sourceImg3;

        public ImageView fuliImg;
        @BindView(R.id.source_title)
        public TextView sourceTitle;
        @BindView(R.id.source_time)
        public TextView sourceTime;
        @BindView(R.id.source_writer)
        public TextView sourceWriter;
        public SortViewHoder(View itemView,int viewType) {
            super(itemView);
            this.viewType =viewType;
            ButterKnife.bind(this,itemView);
            if (viewType ==1){
                sourceImg1 = itemView.findViewById(R.id.source_img1);
            }
            if(viewType==2){
                sourceImg1 = itemView.findViewById(R.id.source_img1);
                sourceImg2 = itemView.findViewById(R.id.source_img2);
            }
            if(viewType==3){
                sourceImg1 = itemView.findViewById(R.id.source_img1);
                sourceImg2 = itemView.findViewById(R.id.source_img2);
                sourceImg3 = itemView.findViewById(R.id.source_img3);
            }

        }
    }
    interface OnItemClickListener{
        void ItemClick(View view,SortData.ResultsBean data);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }
    public void addItems(List<SortData.ResultsBean> datas){
        for (int i =0;i<datas.size();i++){
            items.add(datas.get(i));
        }
    }
    public void freshData(List<SortData.ResultsBean> datas){
        this.items = datas;
    }
}
