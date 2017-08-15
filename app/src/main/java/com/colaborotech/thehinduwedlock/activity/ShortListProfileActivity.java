package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import java.util.List;

/**
 * Created by ubuntu on 9/8/17.
 */

public class ShortListProfileActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView, GetWebServiceData {

    private ImageView ivBack;
    private TextView tvHeader;
    private TextView tvTab1;
    private TextView tvTab2;
    private RecyclerView rlList;
    private TextView tvNoData;
    private LinearLayout llTab;


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
        tvHeader.setText("Shortlisted Profile");


    }

    @Override
    public void init(Bundle save) {

    }


    @Override
    public void getAdapterView(View view, List objects, int position, int from) {

    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }


    @Override
    public void onClick(View v) {

    }
}