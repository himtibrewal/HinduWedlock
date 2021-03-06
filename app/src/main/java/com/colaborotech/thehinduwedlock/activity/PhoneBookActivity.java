package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by ubuntu on 9/8/17.
 */

public class PhoneBookActivity extends BaseActivity implements View.OnClickListener {

    ImageView ivBack;
    TextView tvHeader;
    TextView tvTab1;
    TextView tvTab2;
    RecyclerView rlList;
    TextView tvNoData;
    LinearLayout llTab;


    @Override
    public int getActivityLayout() {
        return R.layout.layout_rl_two_tab;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvTab1 = (TextView) findViewById(R.id.tv_tab1);
        tvTab2 = (TextView) findViewById(R.id.tv_tab2);
        rlList = (RecyclerView) findViewById(R.id.rv_list);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        llTab = (LinearLayout) findViewById(R.id.ll_recived_send);
        llTab.setVisibility(View.GONE);
        ivBack.setOnClickListener(this);


    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Phonebook");
        tvNoData.setText("coming soon");
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