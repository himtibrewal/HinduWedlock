package com.colaborotech.thehinduwedlock.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter11 extends FragmentPagerAdapter {

    String tabs[];
    ReturnView returnView;
    Context context;


    public ViewPagerAdapter11(Context context, FragmentManager fm, ReturnView returnView, String tabs[]) {
        super(fm);
        this.returnView = returnView;
        this.context = context;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return returnView.getItemTemp(position);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    public interface ReturnView {
        Fragment getItemTemp(int position);
    }


}