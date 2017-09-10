package com.colaborotech.thehinduwedlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;

/**
 * Created by him on 16-Jul-17.
 */

public class MobileVerificationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvHeader;
    private TextView tvLogout;
    private TextView tvSubmit;
    private EditText etcountryCode;
    private EditText etPhoneNo;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_verify_number;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvLogout = (TextView) findViewById(R.id.toolbar_last);
        tvSubmit = (TextView) findViewById(R.id.tv_next_verify_number);
        etcountryCode = (EditText) findViewById(R.id.et_std_verify_number);
        etPhoneNo = (EditText) findViewById(R.id.et_phone_number_verify_number);
        etPhoneNo.setText(AppPref.getInstance().getMobile());
        ivBack.setVisibility(View.GONE);
        tvHeader.setText("Verify your number");
        tvLogout.setText("Logout");
        tvLogout.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
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
            case R.id.toolbar_last:
                AppPref.getInstance().setIsLogin(false);
                AppPref.getInstance().setuserId("");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_next_verify_number:
                validation();
                break;
        }
    }


    private void validation() {
        String country_code = etcountryCode.getText().toString();
        String phoneNo = etPhoneNo.getText().toString();
        if (country_code.equalsIgnoreCase("")) {
            toastMessage("Please Enter Your Country Code");
            return;
        } else if (phoneNo.equalsIgnoreCase("")) {
            toastMessage("Please Enter Phone No");
        } else {
            Intent intent = new Intent(this, VerificationScreenActivity.class);
            intent.putExtra("country_code", country_code);
            intent.putExtra("phone_no", phoneNo);
            startActivity(intent);
        }
    }
}
