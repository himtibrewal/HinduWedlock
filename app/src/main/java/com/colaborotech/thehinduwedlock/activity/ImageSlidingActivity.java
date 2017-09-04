package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.MyCustomPagerAdapter;
import com.colaborotech.thehinduwedlock.models.ImageModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 24/8/17.
 */

public class ImageSlidingActivity extends BaseActivity implements View.OnClickListener, GetWebServiceData {

    int images[] = {R.drawable.profilepic, R.drawable.profilepic, R.drawable.profilepic, R.drawable.profilepic};
    MyCustomPagerAdapter myCustomPagerAdapter;
    List<ImageModel> imageList = new ArrayList<ImageModel>();
    private TextView tvBack;
    private TextView tvHeader;
    private TextView tvSave;
    private ViewPager viewPager;
    private int callingFrom = -1;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_image_sliding;
    }

    @Override
    public void initialize() {
        tvBack = (TextView) findViewById(R.id.tv_cancel);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvSave = (TextView) findViewById(R.id.tv_save);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void init(Bundle save) {
        if (getIntent().getExtras() != null) {
            callingFrom = getIntent().getExtras().getInt("from");
            if (callingFrom == 0) {
                tvSave.setVisibility(View.GONE);
            } else if (callingFrom == 1) {
                tvSave.setVisibility(View.VISIBLE);
            } else {
                toastMessage("Something Went Wrong");
            }
        }
        Gson gson = new Gson();
        imageList = gson.fromJson(AppPref.getInstance().getImageUrls(), new TypeToken<List<ImageModel>>() {
        }.getType());
        tvSave.setText("Save");
        tvHeader.setText("My Photos \n 1 of 2");
        tvBack.setText("Back");
        tvBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        if (imageList != null && imageList.size() > 0) {
            myCustomPagerAdapter = new MyCustomPagerAdapter(ImageSlidingActivity.this, imageList, callingFrom);
            viewPager.setAdapter(myCustomPagerAdapter);
        }

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
        }

    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
