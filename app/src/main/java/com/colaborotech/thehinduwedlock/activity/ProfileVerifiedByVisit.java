package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by him on 20-Aug-17.
 */

public class ProfileVerifiedByVisit extends BaseActivity implements View.OnClickListener {
    RecyclerView rlVerifiedProfile;
    TextView tvNoData;
    ImageView ivBack;
    TextView tvHeader;

    @Override
    public int getActivityLayout() {
        return R.layout.layout_rl_two_tab;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        rlVerifiedProfile = (RecyclerView) findViewById(R.id.rv_list);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("No Matches");
        tvNoData.setText("People who match your desired partner profile and are verified by visit will appear here");
        tvNoData.setVisibility(View.VISIBLE);
        rlVerifiedProfile.setVisibility(View.GONE);
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

