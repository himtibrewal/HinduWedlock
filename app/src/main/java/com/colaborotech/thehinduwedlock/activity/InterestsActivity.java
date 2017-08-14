package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
 * Created by him on 28-Jun-17.
 */

public class InterestsActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView, GetWebServiceData {

    //paging
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tvReceived;
    private TextView tvSent;
    private RecyclerView rlMainList;
    private RecyclerAdapter recyclerAdapterReceived;
    private RecyclerAdapter recyclerAdapterSend;
    private List<UserModel> sendList = new ArrayList<UserModel>();
    private List<UserModel> recieveList = new ArrayList<UserModel>();
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvNodata;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = 0;
    private int activeTab = -1;
    private int limit = 10;
    private int deleteposition = 0;


    @Override
    public int getActivityLayout() {
        return R.layout.layout_rl_two_tab;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvReceived = (TextView) findViewById(R.id.tv_tab1);
        tvSent = (TextView) findViewById(R.id.tv_tab2);
        rlMainList = (RecyclerView) findViewById(R.id.rv_list);
        tvNodata = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);
        tvReceived.setOnClickListener(this);
        tvSent.setOnClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rlMainList.setLayoutManager(llm);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                if (activeTab == 0) {
                    getDataFromServer(currentPage, AppUrls.GET_INTEREST_RECEIVED, 0, true);
                } else if (activeTab == 1) {
                    getDataFromServer(currentPage, AppUrls.GET_INTEREST_SENT, 1, true);
                }
            }
        });
        rlMainList.addOnScrollListener(new PaginationScrollListener(llm) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (activeTab == 0) {
                    if (TOTAL_PAGES >= currentPage) {
                        getDataFromServer(currentPage, AppUrls.GET_INTEREST_RECEIVED, 0, false);
                    }

                } else if (activeTab == 1) {
                    if (TOTAL_PAGES >= currentPage) {
                        getDataFromServer(currentPage, AppUrls.GET_INTEREST_SENT, 1, false);
                    }

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
        activeTab = 0;
        getDataFromServer(0, AppUrls.GET_INTEREST_RECEIVED, 0, true);
        recyclerAdapterReceived = new RecyclerAdapter(recieveList, this, R.layout.item_4_bottom_icon, this, 0);
    }

    @Override
    public void init(Bundle save) {
        tvTitle.setText("Interests");
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
            case R.id.tv_tab1:
                activeTab = 0;
                currentPage = 0;
                tvReceived.setBackgroundColor(getResources().getColor(R.color.red_dark));
                tvReceived.setTextColor(getResources().getColor(R.color.white));
                tvReceived.setText("Received(" + recieveList.size() + ")");
                tvSent.setText("Sent");
                getDataFromServer(currentPage, AppUrls.GET_INTEREST_RECEIVED, 0, true);
                tvSent.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvSent.setTextColor(getResources().getColor(R.color.light_black));
                recyclerAdapterReceived = new RecyclerAdapter(recieveList, this, R.layout.item_4_bottom_icon, this, 0);
                break;
            case R.id.tv_tab2:
                activeTab = 1;
                currentPage = 0;
                tvSent.setBackgroundColor(getResources().getColor(R.color.red_dark));
                tvSent.setTextColor(getResources().getColor(R.color.white));
                tvSent.setText("Sent(" + sendList.size() + ")");
                tvReceived.setText("Received");
                getDataFromServer(currentPage, AppUrls.GET_INTEREST_SENT, 1, true);
                tvReceived.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvReceived.setTextColor(getResources().getColor(R.color.light_black));
                recyclerAdapterSend = new RecyclerAdapter(sendList, this, R.layout.item_4_bottom_icon, this, 1);
                break;
        }
    }

    private void getDataFromServer(int page_no, String url, int id, boolean progress) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&page_no=").append(page_no);
        String content = stringBuilder.toString();
        Log.e("content", "is" + url + "-- " + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, url, id, content, progress, "please wait..", this);
        getDataUsingWService.execute();
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        mSwipeRefreshLayout.setRefreshing(false);
        int count = 0;
        switch (serviceCounter) {
            case 0:
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        if (currentPage == 0) {
                            recieveList.clear();
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
                            if (userMapObject.containsKey("user_id")) {
                                userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
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
                            recieveList.add(userModel);
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
                    tvNodata.setVisibility(View.VISIBLE);
                } else {
                    tvNodata.setVisibility(View.GONE);
                }
                tvReceived.setText("Received(" + count + ")");
                tvSent.setText("Sent");
                rlMainList.setAdapter(recyclerAdapterReceived);
                recyclerAdapterReceived.notifyDataSetChanged();
                break;
            case 1:
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        if (currentPage == 0) {
                            sendList.clear();
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
                            if (userMapObject.containsKey("user_id")) {
                                userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
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
                            sendList.add(userModel);
                        }
                    }
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
                TOTAL_PAGES = count / limit;
                if (count == 0) {
                    tvNodata.setVisibility(View.VISIBLE);
                } else {
                    tvNodata.setVisibility(View.GONE);
                }
                if (currentPage < TOTAL_PAGES) {
                    isLoading = false;
                    isLastPage = false;
                } else {
                    isLoading = false;
                    isLastPage = true;
                }
                tvSent.setText("Sent(" + count + ")");
                tvReceived.setText("Received");
                rlMainList.setAdapter(recyclerAdapterSend);
                recyclerAdapterSend.notifyDataSetChanged();
                break;
            case 10:
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        toastMessage(jsonObject.getString("message"));

                    }
                } catch (Exception e) {

                }
                break;
            case 20:
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        toastMessage(jsonObject.getString("message"));
                        sendList.remove(deleteposition);
                        tvSent.setText("Sent(" + sendList.size() + ")");
                        if (sendList.size() == 0) {
                            tvNodata.setVisibility(View.VISIBLE);
                        }
                        recyclerAdapterSend.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }
                break;
            case 30:
                //{"response_code":"200","message":"success","results":[{"_id":"596cce232ace2d00112817d6","user_id":18,"email":"ankit.tebrawal@gmail.com","phone":"9717806116"}]}
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    if (response_code.equalsIgnoreCase("200")) {
                        Map<String, Object> userMapObject = new Gson().fromJson(jsonArray.getJSONObject(0).toString(), Map.class);
                        UserModel userModel = new UserModel();
                        if (userMapObject.containsKey("user_id")) {
                            userModel.setUserId(jsonArray.getJSONObject(0).getString("user_id"));
                        }
                        if (userMapObject.containsKey("email")) {
                            userModel.setEmail(userMapObject.get("email").toString());
                        }
                        if (userMapObject.containsKey("phone")) {
                            userModel.setPhone(userMapObject.get("phone").toString());
                        }
                        showConactDialog(userModel);
                    }
                } catch (Exception e) {

                }
                break;
        }


    }

    @Override
    public void getAdapterView(View view, final List objects, final int position, int from) {
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
        // ImageView ivItem4 = (ImageView) view.findViewById(R.id.iv_item4);
        RelativeLayout rlItem1 = (RelativeLayout) view.findViewById(R.id.rl_item1);
        RelativeLayout rlItem2 = (RelativeLayout) view.findViewById(R.id.rl_item2);
        RelativeLayout rlItem3 = (RelativeLayout) view.findViewById(R.id.rl_item3);
        switch (from) {
            case 0:
                tvInterestTiming.setText("He sent an request on " + ((UserModel) objects.get(position)).getTime());
                llBottom.setWeightSum(2);
                tvItem1.setText("Accept");
                tvItem2.setText("Reject");
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
                        acceptRejectInterest("Y", ((UserModel) objects.get(position)).getInterest_id());
                    }
                });
                rlItem2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acceptRejectInterest("N", ((UserModel) objects.get(position)).getInterest_id());

                    }
                });
                break;
            case 1:
                tvInterestTiming.setText("He sent an request on " + ((UserModel) objects.get(position)).getTime());
                llBottom.setWeightSum(3);
                tvItem1.setText("Send Reminder");
                tvItem2.setText("Cancel Interest");
                tvItem3.setText("Contact");
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

                    }
                });
                rlItem1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastMessage("coming soon Reminder");
                    }
                });
                rlItem2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteposition = position;
                        cancelInterest(((UserModel) objects.get(position)).getInterest_id());
                    }
                });
                rlItem3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContactDetail(((UserModel) objects.get(position)).getUserId());
                    }
                });
                break;
        }
    }

    private void acceptRejectInterest(String data, int id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interest_id=").append(id);
        stringBuilder.append("&status=").append(data);
        String contant = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.INTEREST_ACCEPT_REJECT, 10, contant, true, "Please wait..", this);
        getDataUsingWService.execute();
    }

    private void cancelInterest(int id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interest_id=").append(id);
        String contant = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.DELETE_INTEREST, 20, contant, true, "Please wait..", this);
        getDataUsingWService.execute();
    }

    private void getContactDetail(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(id);
        String contant = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.CONTACT_DETAIL, 30, contant, true, "Please wait..", this);
        getDataUsingWService.execute();
    }

    private void showConactDialog(UserModel userModel) {
        toastMessage(userModel.getPhone());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
