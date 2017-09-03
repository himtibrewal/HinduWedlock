package com.colaborotech.thehinduwedlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by him on 10-Jun-17.
 */

public class ProfileListActivity extends BaseActivity implements RecyclerAdapter.ReturnView, GetWebServiceData, View.OnClickListener {
    RecyclerView recyclerView;
    List<UserModel> list = new ArrayList<UserModel>();
    RecyclerAdapter recyclerAdapter;
    ImageView ivBack;
    TextView tvTitle;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private int currentPage = 0;
    private int activeTab = -1;
    private int limit = 10;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.list_search);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new PaginationScrollListener(llm) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                if (TOTAL_PAGES >= currentPage) {
                    getdatafromServer(currentPage);
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
    }

    @Override
    public void init(Bundle save) {
        getdatafromServer(0);
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

    @Override
    public void getAdapterView(View view, final List objects, final int position, int from) {
        ImageView listImage = (ImageView) view.findViewById(R.id.iv_profile_pic_item_search);
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
        RelativeLayout rlSendInterest = (RelativeLayout) view.findViewById(R.id.rl_interest_item_interest_received);
        RelativeLayout rlSendShortList = (RelativeLayout) view.findViewById(R.id.rl_shortlist_item_interest_received);
        RelativeLayout rlBlockThisUser = (RelativeLayout) view.findViewById(R.id.rl_decline_item_interest_received);
        RelativeLayout rlContact = (RelativeLayout) view.findViewById(R.id.rl_contact_item_interest_received);
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
                sendInterestToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlSendShortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendShortlistToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlBlockThisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBlockToServer(((UserModel) objects.get(position)).getUserId());
            }
        });
        rlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void sendInterestToServer(String reciverid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&recieve_id=").append(reciverid);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.SEND_INTEREST, 1, content, true, "please wait..", this);
        getDataUsingWService.execute();
    }

    private void sendShortlistToServer(String reciverid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&recieve_id=").append(reciverid);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.SEND_SHORTLIST, 2, content, true, "please wait..", this);
        getDataUsingWService.execute();
    }

    private void sendBlockToServer(String reciverid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&block_id=").append(reciverid);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.SEND_BLOCK, 3, content, true, "please wait..", this);
        getDataUsingWService.execute();
    }

    private void getdatafromServer(int page_no) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("page_no=").append(page_no);
        stringBuilder.append("&gender=").append(AppPref.getInstance().getGender());
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.USER_LIST, 0, content, true, "please wait..", this);
        getDataUsingWService.execute();
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        switch (serviceCounter) {
            case 0:
                parseUserList(responseData);
                break;
            case 1:
                Log.e("response" + serviceCounter, "is" + responseData);
                break;
            case 2:
                Log.e("response" + serviceCounter, "is" + responseData);
                break;
            case 3:
                Log.e("response" + serviceCounter, "is" + responseData);
                break;
        }

    }


    private void parseUserList(String responseData) {
        int count = 0;
        try {
            Log.e("user_list", "is" + responseData);
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("results");
                int usercount = jsonObject1.getInt("user_count");
                tvTitle.setText(usercount + " Matches found");
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
                    list.add(userModel);
                }


            }
            recyclerAdapter = new RecyclerAdapter(list, this, R.layout.item_search_result, this, 0);
//            recyclerView.setAdapter(recyclerAdapter);
//            recyclerAdapter.notifyDataSetChanged();
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
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            Toast.makeText(this, "notification", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
