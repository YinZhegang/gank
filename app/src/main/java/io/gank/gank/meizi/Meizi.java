package io.gank.gank.meizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.gank.gank.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Meizi extends Fragment {
    @BindView(R.id.meizi_progress_bar)
    ProgressBar meiziProgressBar;
    private MeiziXrecyclerAdapter meiziXrecyclerAdapter;
    @BindView(R.id.meizi_xrecycler)
    XRecyclerView meiziXrecycler;
    Unbinder unbinder;
    private int pageNow = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meizi, container, false);
        unbinder = ButterKnife.bind(this, view);
        requestData(10, 1);
        return view;
    }

    private void requestData(int size, final int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeiziApi meiziApi = retrofit.create(MeiziApi.class);
        Call<MeiziData> call = meiziApi.getResult(size, page);
        call.enqueue(new Callback<MeiziData>() {
            @Override
            public void onResponse(Call<MeiziData> call, Response<MeiziData> response) {
                if (response.body().isError() == false) {
                    List<MeiziData.ResultsBean> data = response.body().getResults();
                    if (page == 1) {
                        if (meiziProgressBar.getVisibility()==View.VISIBLE){
                            meiziProgressBar.setVisibility(View.GONE);
                        }
                        renderData(data);
                        meiziXrecycler.refreshComplete();
                        pageNow = 2;
                    } else {
                        meiziXrecyclerAdapter.addItems(data);
                        meiziXrecycler.loadMoreComplete();
                        pageNow += 1;
                    }
                    meiziXrecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MeiziData> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void renderData(List<MeiziData.ResultsBean> data) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        meiziXrecycler.setLayoutManager(layoutManager);
        meiziXrecycler.setAdapter(meiziXrecyclerAdapter = new MeiziXrecyclerAdapter(getContext(), data));
        meiziXrecycler.setLoadingMoreEnabled(true);
        meiziXrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData(10, 1);
            }

            @Override
            public void onLoadMore() {
                requestData(10, pageNow);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
