package com.colaborotech.thehinduwedlock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by him on 08-Jul-17.
 */

public class EnterDataActivity extends BaseActivity implements View.OnClickListener {

    private String data = "";
    private String header = "";
    private EditText etEditText;
    private TextView tvCancel;
    private TextView tvSave;
    private TextView tvHeader;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_edit_large_data;
    }

    @Override
    public void initialize() {
        etEditText = (EditText) findViewById(R.id.et_input_some_data);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        if (getIntent().getExtras() != null) {
            data = getIntent().getExtras().getString("value");
            header = getIntent().getExtras().getString("header");
        }
        etEditText.setText(data);
        tvHeader.setText(header);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);


    }

    @Override
    public void init(Bundle save) {

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
            case R.id.tv_save:
                data = etEditText.getText().toString();
                Intent intent = getIntent();
                intent.putExtra("value", data);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
