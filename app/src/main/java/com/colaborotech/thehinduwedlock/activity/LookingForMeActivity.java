package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
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
 * Created by ubuntu on 24/8/17.
 */

public class LookingForMeActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView, GetWebServiceData {
    private RecyclerView rlJustJoined;
    private TextView tvNoData;
    private ImageView ivBack;
    private TextView tvHeader;
    private ImageView ivFilter;
    private RecyclerAdapter recyclerAdapter;
    private List<UserModel> lookingForMeList = new ArrayList<UserModel>();
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = 0;
    private int limit = 10;


    @Override
    public int getActivityLayout() {
        return R.layout.results_layouts;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        ivFilter = (ImageView) findViewById(R.id.toolbar_last);
        rlJustJoined = (RecyclerView) findViewById(R.id.rv_list);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);
        ivFilter.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        getJustJoinedList(AppPref.getInstance().getuserId(), currentPage);
        tvHeader.setText("No Matches");
        tvNoData.setText("There are no profile");
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
        recyclerAdapter = new RecyclerAdapter(lookingForMeList, this, R.layout.item_4_bottom_icon, this, 0);
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
                toastMessage("filter will come here in dilaog");
                break;
        }
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        // mSwipeRefreshLayout.setRefreshing(false);
        int count = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                if (currentPage == 0) {
                    lookingForMeList.clear();
                }
                count = jsonObject.getInt("count");
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Map<String, Object> userMapObject = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), Map.class);
                    UserModel userModel = new UserModel();
                    if (userMapObject.containsKey("interest_id")) {
                        userModel.setInterest_id(jsonArray.getJSONObject(i).getInt("interest_id"));
                    }
                    if (userMapObject.containsKey("time")) {
                        userModel.setTime(userMapObject.get("time").toString());
                    }
                    if (userMapObject.containsKey("response_time")) {
                        userModel.setInterestResponseTime(userMapObject.get("response_time").toString());
                    }
                    if (userMapObject.containsKey("user_id")) {
                        userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
                    }
                    if (userMapObject.containsKey("time")) {
                        userModel.setTime(userMapObject.get("time").toString());
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
                    lookingForMeList.add(userModel);
                }
            }
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
        rlJustJoined.setAdapter(recyclerAdapter);
        tvHeader.setText("Member Looking..(" + lookingForMeList.size() + ")");
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        LinearLayout lltime = (LinearLayout) view.findViewById(R.id.ll_timing_interest);
        TextView tvInterestTiming = (TextView) view.findViewById(R.id.tv_timing_interest);
        ImageView ivNoOgimage = (ImageView) view.findViewById(R.id.iv_no_Of_image);
        ImageView ivProfilePic = (ImageView) view.findViewById(R.id.iv_profile_pic);
        RelativeLayout rlData = (RelativeLayout) view.findViewById(R.id.rl_data);
        RelativeLayout rlMessage = (RelativeLayout) view.findViewById(R.id.rl_message_view);
        TextView tvFirstMessage = (TextView) view.findViewById(R.id.tv_first_message);
        TextView tvFullMessage = (TextView) view.findViewById(R.id.full_message);
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
        LinearLayout llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom_layout);
        ImageView ivItem1 = (ImageView) view.findViewById(R.id.iv_item1);
        ImageView ivItem2 = (ImageView) view.findViewById(R.id.iv_item2);
        ImageView ivItem3 = (ImageView) view.findViewById(R.id.iv_item3);
        ImageView ivItem4 = (ImageView) view.findViewById(R.id.iv_item4);
        TextView tvItem1 = (TextView) view.findViewById(R.id.tv_item1);
        TextView tvItem2 = (TextView) view.findViewById(R.id.tv_item2);
        TextView tvItem3 = (TextView) view.findViewById(R.id.tv_item3);
        TextView tvItem4 = (TextView) view.findViewById(R.id.tv_item4);
        RelativeLayout rlItem1 = (RelativeLayout) view.findViewById(R.id.rl_item1);
        RelativeLayout rlItem2 = (RelativeLayout) view.findViewById(R.id.rl_item2);
        RelativeLayout rlItem3 = (RelativeLayout) view.findViewById(R.id.rl_item3);
        RelativeLayout rlItem4 = (RelativeLayout) view.findViewById(R.id.rl_item4);
        tvInterestTiming.setText("He sent an request on " + ((UserModel) objects.get(position)).getTime());
        llBottom.setWeightSum(4);
        tvItem1.setText("Send Interest");
        tvItem2.setText("Shortlist");
        tvItem3.setText("Ignore");
        tvItem4.setText("Contact");
        //rlMessage.setVisibility(View.GONE);
        tvUserId.setText("THW" + ((UserModel) objects.get(position)).getUserId());
        tvLastOnline.setText("Today");
        tvAge.setText(((UserModel) objects.get(position)).getAge() + " " + ((UserModel) objects.get(position)).getHeight());
        tvWork.setText(((UserModel) objects.get(position)).getOccupation());
        tvCaste.setText(((UserModel) objects.get(position)).getReligion() + " " + ((UserModel) objects.get(position)).getCaste());
        tvIncome.setText(((UserModel) objects.get(position)).getIncome());
        tvLanguage.setText(((UserModel) objects.get(position)).getMotherTongue());
        tvQualification.setText(((UserModel) objects.get(position)).getHighestEducation());
        tvCity.setText(((UserModel) objects.get(position)).getState() + " " + ((UserModel) objects.get(position)).getCity());
        tvMemberShip.setText("Free");
        ivNoOgimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("coming soon No image");
            }
        });
        rlItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        rlItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlItem4.setOnClickListener(new View.OnClickListener() {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(userid);
        stringBuilder.append("&page_no=").append(page);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.BLOCKED_USER_LIST, 0, content, true, "Please Wait", this);
        getDataUsingWService.execute();
    }

}