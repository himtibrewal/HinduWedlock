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

public class SavedSearchActivity extends BaseActivity implements View.OnClickListener {
    ImageView ivBack;
    TextView tvHeader;
    TextView tvManage;
    RecyclerView rlList;
    TextView tvNodata;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_saved_search;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvManage = (TextView) findViewById(R.id.toolbar_last);
        rlList = (RecyclerView) findViewById(R.id.rv_saved_search);
        tvNodata = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);


    }

    @Override
    public void init(Bundle save) {
        tvManage.setText("Manage");
        tvHeader.setText("Saved Searches(0)");
        tvManage.setTextColor(getResources().getColor(R.color.red_dark));
        tvNodata.setText("Not Saved Any Data");
        tvNodata.setVisibility(View.VISIBLE);
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
