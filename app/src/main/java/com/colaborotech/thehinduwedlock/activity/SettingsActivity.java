package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by ubuntu on 5/8/17.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    ImageView ivBack;
    TextView tvHeader;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Settings");
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