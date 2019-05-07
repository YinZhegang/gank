package io.gank.gank.sort;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by yinzhegang on 2019/3/14.
 */

public class SortViewPaperAdapter extends FragmentPagerAdapter {
    String [] titles;
    public SortViewPaperAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
            Fragment fr = new SortCommon();
            Bundle args = new Bundle();
            args.putString("dataType",titles[position]);
            fr.setArguments(args);
            return  fr;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
