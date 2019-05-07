package io.gank.gank.xiandu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class XianduCategoryCommon extends Fragment {
    private XianduRecyclerAdapter xianduRecyclerAdapter;
    @BindView(R.id.category_recycler)
    RecyclerView categoryRecycler;
    Unbinder unbinder;
    private String dataType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataType = getArguments().getString("dataType");
        View view = inflater.inflate(R.layout.xiandu_category_common, container, false);
        initList();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategriesApi categriesApi = retrofit.create(CategriesApi.class);
        Call<CategoriesData> call = categriesApi.getResults(dataType);
        call.enqueue(new Callback<CategoriesData>() {
            @Override
            public void onResponse(Call<CategoriesData> call, Response<CategoriesData> response) {
                if(!response.body().isError()){
                    List<CategoriesData.ResultsBean> data = response.body().getResults();
                    renderRecycler(data);
                }
            }

            @Override
            public void onFailure(Call<CategoriesData> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private void renderRecycler(List<CategoriesData.ResultsBean> data){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryRecycler.setAdapter(xianduRecyclerAdapter=new XianduRecyclerAdapter(getContext(),data));
        xianduRecyclerAdapter.setOnItemClickListener(new XianduRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, String id) {
                Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
