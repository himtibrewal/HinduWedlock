package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.ViewPagerAdapter;
import com.colaborotech.thehinduwedlock.fragment.AboutFragment;
import com.colaborotech.thehinduwedlock.fragment.OneFragment;
import com.colaborotech.thehinduwedlock.fragment.UserFamilyFragment;


/**
 * Created by him on 12-Jun-17.
 */

public class ProfileDetailActivity extends BaseActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    CollapsingToolbarLayout collapsing_container;
    Toolbar toolbar;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_profile_detail;
    }

    @Override
    public void initialize() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        collapsing_container = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsing_container.setTitle("ppppppp");

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void init(Bundle save) {

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutFragment(), "ABOUT");
        adapter.addFragment(new UserFamilyFragment(), "FAMILY");
        adapter.addFragment(new OneFragment(), "LOOKING FOR");
        viewPager.setAdapter(adapter);
    }
}
