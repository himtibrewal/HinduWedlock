package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by ubuntu on 5/8/17.
 */

public class JustJoinedActivity extends BaseActivity implements View.OnClickListener {
    RecyclerView rlJustJoined;
    TextView tvNoData;
    ImageView ivBack;
    TextView tvHeader;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_just_joined;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        rlJustJoined = (RecyclerView) findViewById(R.id.rv_just_joined);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("No Matches");
        tvNoData.setText("There are no recently joined profile");
        tvNoData.setVisibility(View.VISIBLE);
        rlJustJoined.setVisibility(View.GONE);

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
