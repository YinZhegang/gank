package io.gank.gank.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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
import io.gank.gank.ResourceDao;
import io.gank.gank.sqlBean.ResourceBean;

public class Collect extends Fragment {
    private CollectRecyclerAdapter collectRecyclerAdapter;
    @BindView(R.id.collect_recycler)
    RecyclerView collectRecycler;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect, container, false);
        List<ResourceBean> data = ResourceDao.queryResource();
        Toast.makeText(getContext(), data.get(0).getUrl(), Toast.LENGTH_SHORT).show();
        unbinder = ButterKnife.bind(this, view);
        initContent(data);
        return view;
    }

    private void initContent(List<ResourceBean> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        collectRecycler.setLayoutManager(layoutManager);
        collectRecycler.setAdapter(collectRecyclerAdapter =new CollectRecyclerAdapter(getContext(),data));
        collectRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
