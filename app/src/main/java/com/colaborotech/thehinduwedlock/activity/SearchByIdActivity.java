package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;

/**
 * Created by him on 22-Jul-17.
 */

public class SearchByIdActivity extends BaseActivity implements View.OnClickListener {

    ImageView ivBack;
    TextView tvTitle;
    EditText etProfileid;
    TextView tvSearch;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_search_by_id;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        etProfileid = (EditText) findViewById(R.id.editText_profile_id_search);
        tvSearch = (TextView) findViewById(R.id.submit_searchby_id);
    }

    @Override
    public void init(Bundle save) {
        ivBack.setOnClickListener(this);
        tvTitle.setText("Search by Profile id");
        tvSearch.setOnClickListener(this);
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
            case R.id.submit_searchby_id:
                validation();
                break;
        }

    }

    private void validation() {
        String profileid = etProfileid.getText().toString();
        if (profileid.equalsIgnoreCase("")) {
            toastMessage("please enter  profile id");
            return;
        } else {
            sendToThisActivity(ProfileDetailActivity.class, new String[]{"profile_id;" + profileid});
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
