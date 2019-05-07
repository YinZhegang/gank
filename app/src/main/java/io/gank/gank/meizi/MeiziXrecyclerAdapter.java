package io.gank.gank.meizi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.gank.gank.R;


/**
 * Created by yinzhegang on 2019/3/19.
 */

public class MeiziXrecyclerAdapter extends XRecyclerView.Adapter <MeiziXrecyclerAdapter.MeiziViewHoder>{
    private Context context;
    private List<MeiziData.ResultsBean> items;
    @Override
    public MeiziViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiziViewHoder(LayoutInflater.from(context).inflate(R.layout.meizi_item_fuli,parent,false));
    }

    public MeiziXrecyclerAdapter(Context context, List<MeiziData.ResultsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(MeiziViewHoder holder, int position) {
        Picasso.with(context).load(items.get(position).getUrl())
                .centerCrop()
                .fit()
                .into(holder.fuliImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MeiziViewHoder extends XRecyclerView.ViewHolder{
        public ImageView fuliImg;
        public MeiziViewHoder(View itemView) {
            super(itemView);
            fuliImg = itemView.findViewById(R.id.fuli_img);
        }
    }
    public void addItems(List<MeiziData.ResultsBean> data){
        for (int i = 0;i<data.size();i++){
            items.add(data.get(i));
        }
    }
}
