package io.gank.gank.xiandu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

public class Xiandu extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.xiandu_tab)
    TabLayout xianduTab;
    @BindView(R.id.xiandu_viewpaper)
    ViewPager xianduViewpaper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xiandu, container, false);
        unbinder = ButterKnife.bind(this, view);
        initContent();
        return view;
    }

    private void initContent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        XianduCategoryApi xianduCategoryApi = retrofit.create(XianduCategoryApi.class);
        Call<XianduCategoryData> call =xianduCategoryApi.getResult();
        call.enqueue(new Callback<XianduCategoryData>() {
            @Override
            public void onResponse(Call<XianduCategoryData> call, Response<XianduCategoryData> response) {
                if (!response.body().isError()){
                    List<XianduCategoryData.ResultsBean> data = response.body().getResults();
                    XianduCategoryPaperAdapter xianduCategoryPaperAdapter = new XianduCategoryPaperAdapter(getFragmentManager(),data);
                    xianduViewpaper.setAdapter(xianduCategoryPaperAdapter);
                    for (int i = 0;i<data.size();i++){
                        xianduTab.setTag(xianduTab.newTab());
                    }
                    xianduTab.setupWithViewPager(xianduViewpaper);
                    for (int i= 0;i<data.size();i++){
                        xianduTab.getTabAt(i).setText(data.get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<XianduCategoryData> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
