package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.fragment.FilterFragment;
import com.colaborotech.thehinduwedlock.models.UserModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.utility.PaginationScrollListener;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by ubuntu on 5/8/17.
 */

public class JustJoinedActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView, GetWebServiceData {
    private RecyclerView rlJustJoined;
    private TextView tvNoData;
    private ImageView ivBack;
    private TextView tvHeader;
    private ImageView ivFilter;
    private RecyclerAdapter recyclerAdapter;
    private List<UserModel> justJoinedList = new ArrayList<UserModel>();
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = 0;
    private int limit = 5;
    private DrawerLayout drawerLayout;


    @Override
    public int getActivityLayout() {
        return R.layout.results_layouts;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        ivFilter = (ImageView) findViewById(R.id.toolbar_last);
        ivFilter.setVisibility(View.GONE);
        rlJustJoined = (RecyclerView) findViewById(R.id.rv_list);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);
        ivFilter.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setFragmentFilter(new FilterFragment());
    }


    public void setFragmentFilter(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void init(Bundle save) {
        getJustJoinedList(AppPref.getInstance().getuserId(), currentPage);
        tvHeader.setText("No Matches");
        tvNoData.setText("There are no recently joined profile");
        tvNoData.setVisibility(View.VISIBLE);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rlJustJoined.setLayoutManager(llm);
        rlJustJoined.addOnScrollListener(new PaginationScrollListener(llm) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (TOTAL_PAGES >= currentPage) {
                    getJustJoinedList(AppPref.getInstance().getuserId(), currentPage);
                }
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        recyclerAdapter = new RecyclerAdapter(justJoinedList, this, R.layout.item_4_bottom_icon, this, 0);
        rlJustJoined.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
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
            case R.id.toolbar_last:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        // mSwipeRefreshLayout.setRefreshing(false);
        int count = 0;
        try {
            Log.e("user_list", "is" + responseData);
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("results");
                count = jsonObject1.getInt("user_count");
                // tvTitle.setText(usercount + " Matches found");
                tvHeader.setText("Just Joined Matches(" + count + ")");
                JSONArray jsonArray = jsonObject1.getJSONArray("user_data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Map<String, Object> loginMapObject = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), Map.class);
                    UserModel userModel = new UserModel();
                    if (loginMapObject.containsKey("user_id")) {
                        //userModel.setUserId(((int) Double.parseDouble(loginMapObject.get("user_id").toString())) + "");
                        userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
                    }
                    if (loginMapObject.containsKey("name")) {
                        userModel.setName(loginMapObject.get("name").toString());
                    }
                    if (loginMapObject.containsKey("dob")) {
                        String[] date = loginMapObject.get("dob").toString().split("-");
                        int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(date[date.length - 1]);
                        userModel.setAge(age + " Years");
                    }
                    if (loginMapObject.containsKey("height")) {
                        userModel.setHeight(loginMapObject.get("height").toString());
                    }
                    if (loginMapObject.containsKey("state")) {
                        userModel.setState(loginMapObject.get("state").toString());
                    }
                    if (loginMapObject.containsKey("city")) {
                        userModel.setCity(loginMapObject.get("city").toString());
                    }
                    if (loginMapObject.containsKey("mother_tongue")) {
                        userModel.setMotherTongue(loginMapObject.get("mother_tongue").toString());
                    }
                    if (loginMapObject.containsKey("caste")) {
                        userModel.setCaste(loginMapObject.get("caste").toString());
                    }
                    if (loginMapObject.containsKey("highest_education")) {
                        userModel.setHighestEducation(loginMapObject.get("highest_education").toString());
                    }
                    if (loginMapObject.containsKey("income")) {
                        userModel.setIncome(loginMapObject.get("income").toString());
                    }
                    if (loginMapObject.containsKey("gender")) {
                        userModel.setGender(loginMapObject.get("gender").toString());
                    }
                    if (loginMapObject.containsKey("country")) {
                        userModel.setCountry(loginMapObject.get("country").toString());
                    }
                    justJoinedList.add(userModel);
                }
            }

            rlJustJoined.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        TOTAL_PAGES = count / limit;
        if (currentPage < TOTAL_PAGES) {
            isLoading = false;
            isLastPage = false;
        } else {
            isLoading = false;
            isLastPage = true;
        }
        if (count == 0) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }

    }

    @Override
    public void getAdapterView(View view, final List objects, final int position, int from) {
        ImageView listImage = (ImageView) view.findViewById(R.id.iv_profile_pic);
        TextView tvUserId = (TextView) view.findViewById(R.id.userid_item_searchResult);
        TextView tvLastOnline = (TextView) view.findViewById(R.id.lastOnline_item_searchResult);
        TextView tvAge = (TextView) view.findViewById(R.id.age_item_searchResult);
        TextView tvWork = (TextView) view.findViewById(R.id.work_item_searchResult);
        TextView tvCaste = (TextView) view.findViewById(R.id.cast_item_searchResult);
        TextView tvIncome = (TextView) view.findViewById(R.id.income_item_searchResult);
        TextView tvLanguage = (TextView) view.findViewById(R.id.language_item_searchResult);
        TextView tvQualification = (TextView) view.findViewById(R.id.study_item_searchResult);
        TextView tvCity = (TextView) view.findViewById(R.id.city_item_searchResult);
        TextView tvMemberShip = (TextView) view.findViewById(R.id.membership_item_searchResult);
        RelativeLayout rlSendInterest = (RelativeLayout) view.findViewById(R.id.rl_item1);
        RelativeLayout rlSendShortList = (RelativeLayout) view.findViewById(R.id.rl_item2);
        RelativeLayout rlBlockThisUser = (RelativeLayout) view.findViewById(R.id.rl_item3);
        RelativeLayout rlContact = (RelativeLayout) view.findViewById(R.id.rl_item4);
        tvUserId.setText(((UserModel) objects.get(position)).getUserId());
        tvLastOnline.setText("Today");
        tvAge.setText(((UserModel) objects.get(position)).getAge());
        tvWork.setText(((UserModel) objects.get(position)).getOccupation());
        tvCaste.setText(((UserModel) objects.get(position)).getCaste());
        tvIncome.setText(((UserModel) objects.get(position)).getIncome());
        tvLanguage.setText(((UserModel) objects.get(position)).getMotherTongue());
        tvQualification.setText(((UserModel) objects.get(position)).getHighestEducation());
        tvCity.setText(((UserModel) objects.get(position)).getCity());
        tvMemberShip.setText("Free");
        listImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToThisActivity(ProfileDetailActivity.class, new String[]{"profile_id;" + ((UserModel) objects.get(position)).getUserId()});
            }
        });
        rlSendInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendInterestToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlSendShortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendShortlistToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlBlockThisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendBlockToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getJustJoinedList(String userid, int page) {
        String gender = "";
        if (AppPref.getInstance().getGender().equalsIgnoreCase("1")) {
            gender = "0";
        } else {
            gender = "1";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gender=").append(gender);
        stringBuilder.append("&page_no=").append(page);
        stringBuilder.append("&just_join=").append("1");
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.USER_LIST, 0, content, true, "Please Wait", this);
        getDataUsingWService.execute();
    }

}
