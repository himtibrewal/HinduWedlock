package com.colaborotech.thehinduwedlock.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.activity.AcceptedMembersActivity;
import com.colaborotech.thehinduwedlock.activity.FamilyDetailActivity;
import com.colaborotech.thehinduwedlock.activity.JustJoinedActivity;
import com.colaborotech.thehinduwedlock.activity.MobileVerificationActivity;
import com.colaborotech.thehinduwedlock.activity.ProfileEditActivity;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.models.SliderIconModel;
import com.colaborotech.thehinduwedlock.models.UserModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
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
 * Created by him on 08-Jun-17.
 */

public class HomeFragment extends Fragment implements RecyclerAdapter.ReturnView, View.OnClickListener, GetWebServiceData {
    private RecyclerView recyclerViewEdit, recyclerView1, recyclerView11;
    private List<SliderIconModel> list = new ArrayList<SliderIconModel>();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerAdapter recyclerAdapter1;
    private RecyclerAdapter recyclerAdapter2;
    private RelativeLayout rlLeftItem;
    private LinearLayout llAllAccept;
    private LinearLayout llJustJoined;
    private TextView tvAllAccept;
    private TextView tvJustJoined;
    private List<UserModel> interestList = new ArrayList<UserModel>();
    private List<UserModel> matchOfTheDayList = new ArrayList<UserModel>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initializeView(homeView);
        recyclerViewEdit = (RecyclerView) homeView.findViewById(R.id.rv_edit_detail_home);
        recyclerView1 = (RecyclerView) homeView.findViewById(R.id.firstList1);
        recyclerView11 = (RecyclerView) homeView.findViewById(R.id.firstList11);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm11 = new LinearLayoutManager(getActivity());
        llm11.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewEdit.setLayoutManager(llm);
        recyclerView1.setLayoutManager(llm1);
        recyclerView11.setLayoutManager(llm11);
        list.add(new SliderIconModel(1, R.drawable.ic_menu_camera, "Complete"));
        list.add(new SliderIconModel(2, R.drawable.camera_white, "Photo"));
        list.add(new SliderIconModel(3, R.drawable.basic_white, "Basic"));
        list.add(new SliderIconModel(4, R.drawable.kundli_white, "Kundli"));
        list.add(new SliderIconModel(5, R.drawable.education_white, "Education"));
        list.add(new SliderIconModel(6, R.drawable.career_white, "Career"));
        list.add(new SliderIconModel(7, R.drawable.family_white, "Family"));
        list.add(new SliderIconModel(8, R.drawable.lifestyle_white, "Lifestyle"));
        list.add(new SliderIconModel(9, R.drawable.contact_white, "Contact"));
        list.add(new SliderIconModel(10, R.drawable.contact_white, "Desired"));
        recyclerAdapter = new RecyclerAdapter(list, getActivity(), R.layout.list_home_userdetail, this, R.id.rv_edit_detail_home);
        recyclerAdapter1 = new RecyclerAdapter(interestList, getActivity(), R.layout.item_interest_recived, this, 102);
        recyclerAdapter2 = new RecyclerAdapter(matchOfTheDayList, getActivity(), R.layout.item_all_accept, this, 103);
        recyclerViewEdit.setAdapter(recyclerAdapter);
        recyclerView1.setAdapter(recyclerAdapter1);
        recyclerView11.setAdapter(recyclerAdapter2);
        setMobileNoVerifyed();
        callWebservice();
        getinterestReceived();
        getRecentelyjoined();
        return homeView;
    }


    private void initializeView(View view) {
        rlLeftItem = (RelativeLayout) view.findViewById(R.id.rl_left_item);
        rlLeftItem.setOnClickListener(this);
        llAllAccept = (LinearLayout) view.findViewById(R.id.ll_all_accept);
        llJustJoined = (LinearLayout) view.findViewById(R.id.ll_just_joined);
        tvAllAccept = (TextView) view.findViewById(R.id.tv_all_accept);
        tvJustJoined = (TextView) view.findViewById(R.id.tv_just_joined);
        llAllAccept.setOnClickListener(this);
        llJustJoined.setOnClickListener(this);
    }


    private void setMobileNoVerifyed() {
        if (!AppPref.getInstance().getMobileVerify()) {
            Intent intent = new Intent(getActivity(), MobileVerificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_left_item:
                sendTothisActivity(FamilyDetailActivity.class);
                getActivity().finish();
                break;
            case R.id.ll_all_accept:
                sendTothisActivity(AcceptedMembersActivity.class);
                break;
            case R.id.ll_just_joined:
                sendTothisActivity(JustJoinedActivity.class);
                break;
        }
    }


    private void sendTothisActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void getAdapterView(View view, List objects, final int position, int from) {
        TextView tvProfession = (TextView) view.findViewById(R.id.tv_profession);
        TextView tvAge_height = (TextView) view.findViewById(R.id.tv_year_height);
        TextView tvcaste = (TextView) view.findViewById(R.id.tv_caste);
        TextView tvLocation = (TextView) view.findViewById(R.id.tv_location);
        TextView tvIncome = (TextView) view.findViewById(R.id.tv_income);
        switch (from) {
            case R.id.rv_edit_detail_home:
                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_edit_profile);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_infocategory_icon_homeFragment);
                TextView textView = (TextView) view.findViewById(R.id.tv_infocategoty_name_homefragment);
                imageView.setImageDrawable(getResources().getDrawable(((SliderIconModel) objects.get(position)).getIcon()));
                textView.setText(((SliderIconModel) objects.get(position)).getName());
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {

                        } else if (position > 0) {
                            Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                            intent.putExtra("position", position - 1);
                            startActivity(intent);
                        }

                    }
                });
                break;
            case 102:

                tvProfession.setText(interestList.get(position).getOccupation());
                tvAge_height.setText(interestList.get(position).getAge() + " " + interestList.get(position).getHeight());
                tvcaste.setText(interestList.get(position).getCaste() + " " + interestList.get(position).getSubCaste());
                tvLocation.setText(interestList.get(position).getState() + " " + interestList.get(position).getCity());
                tvIncome.setText(interestList.get(position).getIncome());
                break;
            case 103:
                tvProfession.setText(matchOfTheDayList.get(position).getOccupation());
                tvAge_height.setText(matchOfTheDayList.get(position).getAge() + " " + matchOfTheDayList.get(position).getHeight());
                tvcaste.setText(matchOfTheDayList.get(position).getCaste() + " " + matchOfTheDayList.get(position).getSubCaste());
                tvLocation.setText(matchOfTheDayList.get(position).getState() + " " + matchOfTheDayList.get(position).getCity());
                tvIncome.setText(matchOfTheDayList.get(position).getIncome());
                break;
        }

    }

    private void callWebservice() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), AppUrls.COUNT_DATA, 101, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }


    private void getinterestReceived() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&page_no=").append("0");
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), AppUrls.GET_INTEREST_RECEIVED, 102, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }


    private void getRecentelyjoined() {
        String gender = "";
        if (AppPref.getInstance().getGender().equalsIgnoreCase("1")) {
            gender = "0";
        } else {
            gender = "1";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gender=").append(gender);
        stringBuilder.append("&page_no=").append("0");
        stringBuilder.append("&just_join=").append("1");
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), AppUrls.USER_LIST, 103, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        switch (serviceCounter) {
            case 101:
                Log.e("response", "is: " + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        JSONObject result = jsonObject.getJSONObject("results");
                        int allaccept = result.getInt("all_accept");
                        int recent_join = result.getInt("recent_join");
                        tvAllAccept.setText(allaccept + "");
                        tvJustJoined.setText(recent_join + "");
                    }
                } catch (Exception e) {

                }
                break;
            case 102:
                Log.e("response", "is: " + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        int count = jsonObject.getInt("count");
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
                            interestList.add(userModel);
                        }
                    }
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
                recyclerAdapter1 = new RecyclerAdapter(interestList, getActivity(), R.layout.item_interest_recived, this, 102);
                recyclerView1.setAdapter(recyclerAdapter1);
                recyclerAdapter1.notifyDataSetChanged();
                break;
            case 103:
                Log.e("response", "is: " + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("results");
                        int count = jsonObject1.getInt("user_count");
                        // tvTitle.setText(usercount + " Matches found");

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
                            if (loginMapObject.containsKey("occupation")) {
                                userModel.setOccupation(loginMapObject.get("occupation").toString());
                            }
                            matchOfTheDayList.add(userModel);
                        }
                    }
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
                recyclerAdapter2 = new RecyclerAdapter(matchOfTheDayList, getActivity(), R.layout.item_all_accept, this, 103);
                recyclerView11.setAdapter(recyclerAdapter2);
                recyclerAdapter2.notifyDataSetChanged();
                break;
        }
    }
}
