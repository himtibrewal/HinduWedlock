package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ubuntu on 9/8/17.
 */

public class DeclinedActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView, GetWebServiceData {

    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView tvIDeclined;
    TextView tvTheyDeclined;
    private RecyclerView rlMainList;
    private RecyclerAdapter recyclerAdapterIDeclined;
    private RecyclerAdapter recyclerAdapterTheyDeclined;
    private List<UserModel> iDeclined = new ArrayList<UserModel>();
    private List<UserModel> theyDelicned = new ArrayList<UserModel>();
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvNodata;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = 0;
    private int activeTab = -1;
    private int limit = 10;


    @Override
    public int getActivityLayout() {
        return R.layout.layout_rl_two_tab;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvIDeclined = (TextView) findViewById(R.id.tv_tab1);
        tvTheyDeclined = (TextView) findViewById(R.id.tv_tab2);
        rlMainList = (RecyclerView) findViewById(R.id.rv_list);
        tvNodata = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);
        tvIDeclined.setOnClickListener(this);
        tvTheyDeclined.setOnClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rlMainList.setLayoutManager(llm);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                if (activeTab == 0) {
                    getDataFromServer(currentPage, AppUrls.ACCEPTED_ME, 0, true);
                } else if (activeTab == 1) {
                    getDataFromServer(currentPage, AppUrls.ACCEPTED_BY_ME, 1, true);
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
        getDataFromServer(0, AppUrls.I_DECLINED, 0, true);
        recyclerAdapterIDeclined = new RecyclerAdapter(iDeclined, this, R.layout.item_declined, this, 0);
    }

    @Override
    public void getAdapterView(View view, final List objects, final int position, int from) {
        RelativeLayout rlClick = (RelativeLayout) view.findViewById(R.id.rl_top_layout);
        CircleImageView civProfilePic = (CircleImageView) view.findViewById(R.id.iv_image);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        switch (from) {
            case 0:
                tvName.setText(((UserModel) objects.get(position)).getName());
                tvDate.setText("You Declined on " + ((UserModel) objects.get(position)).getInterestResponseTime());
                rlClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendToThisActivity(ProfileDetailActivity.class, new String[]{"profile_id;" + ((UserModel) objects.get(position)).getUserId()});
                    }
                });
                break;
            case 1:
                tvName.setText(((UserModel) objects.get(position)).getName());
                tvDate.setText("He Cancelled on " + ((UserModel) objects.get(position)).getInterestResponseTime());
                rlClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendToThisActivity(ProfileDetailActivity.class, new String[]{"profile_id;" + ((UserModel) objects.get(position)).getUserId()});
                    }
                });
                break;
        }
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
                            iDeclined.clear();
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
                            if (userMapObject.containsKey("name")) {
                                userModel.setName(userMapObject.get("name").toString());
                            }
                            if (userMapObject.containsKey("user_id")) {
                                userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
                            }
                            iDeclined.add(userModel);
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
                tvIDeclined.setText("I Declined(" + count + ")");
                tvTheyDeclined.setText("They Declined");
                rlMainList.setAdapter(recyclerAdapterIDeclined);
                recyclerAdapterIDeclined.notifyDataSetChanged();
                break;
            case 1:
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        if (currentPage == 0) {
                            theyDelicned.clear();
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
                            if (userMapObject.containsKey("name")) {
                                userModel.setName(userMapObject.get("name").toString());
                            }
                            if (userMapObject.containsKey("user_id")) {
                                userModel.setUserId(jsonArray.getJSONObject(i).getString("user_id"));
                            }
                            theyDelicned.add(userModel);
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
                tvIDeclined.setText("I Declined");
                tvTheyDeclined.setText("They Declined(" + count + ")");
                rlMainList.setAdapter(recyclerAdapterTheyDeclined);
                recyclerAdapterTheyDeclined.notifyDataSetChanged();
                break;
        }

    }

    @Override
    public void init(Bundle save) {
        tvTitle.setText("Declined Members");
        tvIDeclined.setText("I Declined(0)");
        tvTheyDeclined.setText("They Declined(0)");
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
                activeTab = 1;
                currentPage = 0;
                tvIDeclined.setBackgroundColor(getResources().getColor(R.color.red_dark));
                tvIDeclined.setTextColor(getResources().getColor(R.color.white));
                tvIDeclined.setText("I Declined(" + iDeclined.size() + ")");
                tvTheyDeclined.setText("They Declined");
                getDataFromServer(currentPage, AppUrls.I_DECLINED, 0, true);
                tvTheyDeclined.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvTheyDeclined.setTextColor(getResources().getColor(R.color.light_black));
                recyclerAdapterIDeclined = new RecyclerAdapter(iDeclined, this, R.layout.item_declined, this, 0);
                break;
            case R.id.tv_tab2:
                activeTab = 0;
                currentPage = 0;
                tvTheyDeclined.setBackgroundColor(getResources().getColor(R.color.red_dark));
                tvTheyDeclined.setTextColor(getResources().getColor(R.color.white));
                tvTheyDeclined.setText("They Declined(" + theyDelicned.size() + ")");
                tvIDeclined.setText("I Declined");
                getDataFromServer(currentPage, AppUrls.THEY_DECLINED, 1, true);
                tvIDeclined.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvIDeclined.setTextColor(getResources().getColor(R.color.light_black));
                recyclerAdapterTheyDeclined = new RecyclerAdapter(theyDelicned, this, R.layout.item_declined, this, 1);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}
