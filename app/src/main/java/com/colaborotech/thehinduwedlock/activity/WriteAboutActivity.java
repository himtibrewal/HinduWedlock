package com.colaborotech.thehinduwedlock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by him on 19-Jun-17.
 */

public class WriteAboutActivity extends BaseActivity implements View.OnClickListener, GetWebServiceData {
    ImageView ivBack;
    TextView tvHeader;
    TextView tvNext;
    TextView tvDetailText;
    TextView tvCondition;
    //  String value;
    String fromString = "";
    EditText etAboutdetail;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_about_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setVisibility(View.GONE);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvDetailText = (TextView) findViewById(R.id.detail_text);
        tvNext = (TextView) findViewById(R.id.tv_next_about_your_self);
        tvCondition = (TextView) findViewById(R.id.min_100_char_static);
        etAboutdetail = (EditText) findViewById(R.id.et_about_about_detail);
        tvNext.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        save = getIntent().getExtras();
        if (save != null) {
            fromString = save.getString("from");
            if (fromString.equalsIgnoreCase("FamilyDetailActivity")) {
                tvHeader.setText("Write About Family");
                tvDetailText.setText(getString(R.string.write_about_family));
                tvCondition.setVisibility(View.INVISIBLE);
                tvNext.setText("Done");
                //   value = "fromFamilyDetail";
            } else if (fromString.equalsIgnoreCase("BasicDetailFragment")) {
                tvHeader.setText("Write About Yourself");
            }

        } else {
            tvHeader.setText("Write About Yourself");
            // value = "from";
        }

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next_about_your_self:
                if (fromString.equalsIgnoreCase("FamilyDetailActivity")) {
                    sendToDrawerActivity(etAboutdetail.getText().toString());
                } else if (fromString.equalsIgnoreCase("BasicDetailFragment")) {
                    AppPref.getInstance().setAboutYourSelf(etAboutdetail.getText().toString());
                    Intent intent = getIntent();
                    intent.putExtra("value", etAboutdetail.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    sendToFamilyDetailActivity(etAboutdetail.getText().toString());
                }
                //   finish();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void sendToDrawerActivity(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("about_family=").append(data);
        stringBuilder.append("&user_id=").append(AppPref.getInstance().getuserId());
        String content = stringBuilder.toString();
        AppPref.getInstance().setAboutYourSelf(data);
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_ABOUT_FAMILY, 1, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }

    private void sendToFamilyDetailActivity(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("about_your_self=").append(data);
        stringBuilder.append("&user_id=").append(AppPref.getInstance().getuserId());
        String content = stringBuilder.toString();
        AppPref.getInstance().setAboutYourSelf(data);
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_ABOUT_YOURSELF, 0, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        switch (serviceCounter) {
            case 0:
                Log.e("response_yourself", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        sendToThisActivity(FamilyDetailActivity.class);
                        finish();
                    }
                } catch (JSONException e) {

                }
                break;
            case 1:
                Log.e("response_family", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        sendToThisActivity(MobileVerificationActivity.class);
                        finish();
                    }
                } catch (JSONException e) {

                }
                break;
        }

    }
}

