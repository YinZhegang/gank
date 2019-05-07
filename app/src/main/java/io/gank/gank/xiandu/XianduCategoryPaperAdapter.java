package io.gank.gank.xiandu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yinzhegang on 2019/3/19.
 */

public class XianduCategoryPaperAdapter extends FragmentPagerAdapter {
    List<XianduCategoryData.ResultsBean> list;
    public XianduCategoryPaperAdapter(FragmentManager fm, List<XianduCategoryData.ResultsBean> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position ) {
        Fragment fr = new XianduCategoryCommon();
        Bundle args = new Bundle();
        args.putString("dataType",list.get(position).getEn_name());
        fr.setArguments(args);
        return fr;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
