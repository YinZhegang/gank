package io.gank.gank.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.gank.gank.R;

public class Sort extends Fragment {
    private String[] titles =new String[]{"all","Android","iOS","App","前端","瞎推荐","拓展资源","休息视频"};
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_paper)
    ViewPager viewPaper;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort, container, false);
        unbinder = ButterKnife.bind(this, view);
        initContent();
        return view;

    }
    private void initContent(){
        viewPaper.setAdapter(new SortViewPaperAdapter(getFragmentManager(),titles));
        for (int i=0;i<titles.length;i++){
            tabLayout.setTag(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPaper);
        for (int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
