package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.ViewPagerAdapter;
import com.colaborotech.thehinduwedlock.fragment.AboutFragment;
import com.colaborotech.thehinduwedlock.fragment.OneFragment;
import com.colaborotech.thehinduwedlock.fragment.UserFamilyFragment;
import com.colaborotech.thehinduwedlock.models.UserModel;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Map;


/**
 * Created by him on 12-Jun-17.
 */

public class ProfileDetailActivity extends BaseActivity implements GetWebServiceData {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CollapsingToolbarLayout collapsing_container;
    private UserModel userModel;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_profile_detail;
    }

    @Override
    public void initialize() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        collapsing_container = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsing_container.setTitle("");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void init(Bundle save) {
        if (getIntent().getExtras() != null) {
            String userid = getIntent().getExtras().getString("profile_id");
            getUserFullDetail(userid);
        }
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutFragment(), "ABOUT");
        adapter.addFragment(new UserFamilyFragment(), "FAMILY");
        adapter.addFragment(new OneFragment(), "LOOKING FOR");
        viewPager.setAdapter(adapter);
    }


    private void getUserFullDetail(String user_id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(user_id);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.USER_FULL_DETAIL, 0, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        try {

            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                JSONObject resultObject = jsonObject.getJSONObject("results");
                Map<String, Object> userMapObject = new Gson().fromJson(resultObject.toString(), Map.class);
                userModel = new UserModel();
                if (userMapObject.containsKey("user_id")) {
                    userModel.setUserId(resultObject.getInt("user_id") + "");
                }
                if (userMapObject.containsKey("dob")) {
                    String[] date = userMapObject.get("dob").toString().split("-");
                    int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(date[date.length - 1]);
                    userModel.setAge(age + " Years");
                }
                if (userMapObject.containsKey("height")) {
                    userModel.setHeight(userMapObject.get("height").toString());
                }
                if (userMapObject.containsKey("state")) {
                    userModel.setState(userMapObject.get("state").toString());
                }
                if (userMapObject.containsKey("city")) {
                    userModel.setCity(userMapObject.get("city").toString());
                }
                if (userMapObject.containsKey("mother_tongue")) {
                    userModel.setMotherTongue(userMapObject.get("mother_tongue").toString());
                }
                if (userMapObject.containsKey("religion")) {
                    userModel.setReligion(userMapObject.get("religion").toString());
                }
                if (userMapObject.containsKey("highest_education")) {
                    userModel.setHighestEducation(userMapObject.get("highest_education").toString());
                }
                if (userMapObject.containsKey("caste")) {
                    userModel.setCaste(userMapObject.get("caste").toString());
                }
                if (userMapObject.containsKey("occupation")) {
                    userModel.setOccupation(userMapObject.get("occupation").toString());
                }
                if (userMapObject.containsKey("income")) {
                    userModel.setIncome(userMapObject.get("income").toString());
                }
                if (userMapObject.containsKey("time")) {
                    userModel.setTime(userMapObject.get("time").toString());
                }
            }

        } catch (Exception e) {
            Log.e("error_wev", "is: " + e.toString());
        }
        toastMessage(responseData);

    }


    private void setData(UserModel userModel) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
