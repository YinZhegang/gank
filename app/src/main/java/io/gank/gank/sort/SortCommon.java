package io.gank.gank.sort;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.gank.gank.R;
import io.gank.gank.Webview;
import io.gank.gank.sort.data.SortData;
import io.gank.gank.sort.data.SortWebViewData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SortCommon extends Fragment {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String dataType;
    private int pageNow = 1;
    @BindView(R.id.sort_xrecycler)
    XRecyclerView sortXrecycler;
    private String baseUrl = "http://gank.io/";
    private SortXrecyclerAdapter sortXrecyclerAdapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataType = getArguments().getString("dataType");
        View view = inflater.inflate(R.layout.sort_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (progressBar.getVisibility()!=View.VISIBLE){
            progressBar.setVisibility(View.VISIBLE);
        }
        requestData(dataType, 10, 1);
        return view;
    }

    private void requestData(String dataType, int size, final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SortApi api = retrofit.create(SortApi.class);
        Call<SortData> call = api.getResult(dataType, size, page);
        call.enqueue(new Callback<SortData>() {
            @Override
            public void onResponse(Call<SortData> call, Response<SortData> response) {
                List<SortData.ResultsBean> results = response.body().getResults();
                if (!response.body().isError()) {
                    if (page == 1) {
                        if (progressBar.getVisibility()==View.VISIBLE){
                            progressBar.setVisibility(View.GONE);
                        }
                        pageNow = 2;
                        renderData(results);
                        sortXrecycler.refreshComplete();
                    } else {
                        sortXrecyclerAdapter.addItems(results);
                        sortXrecycler.loadMoreComplete();
                        pageNow += 1;
                    }
                    sortXrecyclerAdapter.notifyDataSetChanged();
                }
//                Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SortData> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void renderData(List<SortData.ResultsBean> data) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            sortXrecycler.setLayoutManager(layoutManager);
        sortXrecycler.setAdapter(sortXrecyclerAdapter = new SortXrecyclerAdapter(getContext(),data));
        sortXrecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        sortXrecyclerAdapter.setOnItemClickListener(new SortXrecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(View view, SortData.ResultsBean data) {
                SortWebViewData datas = new SortWebViewData();
                datas.set_id(data.get_id());
                datas.setCreatedAt(data.getCreatedAt());
                datas.setDesc(data.getDesc());
                datas.setPublishedAt(data.getPublishedAt());
                datas.setSource(data.getSource());
                datas.setType(data.getType());
                datas.setUrl(data.getUrl());
                datas.setWho(data.getWho());
                Bundle bundle =new Bundle();
                bundle.putSerializable("data",datas);
                Intent intent = new Intent(getContext(), Webview.class);
                intent.putExtras(bundle);
                startActivity(intent);
//                Toast.makeText(getContext(),data.getUrl(),Toast.LENGTH_SHORT).show();
            }
        });
        sortXrecycler.setLoadingMoreEnabled(true);
        sortXrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData(dataType, 10, 1);
            }

            @Override
            public void onLoadMore() {
                requestData(dataType, 10, pageNow);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
