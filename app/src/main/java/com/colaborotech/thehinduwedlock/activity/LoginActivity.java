package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.models.ImageModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.colaborotech.thehinduwedlock.webservice.Other;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by him on 27-May-17.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, GetWebServiceData {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    Button btnSearch;
    Button btnRegister;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initialize() {
        etEmail = (EditText) findViewById(R.id.login_ed_username);
        etPassword = (EditText) findViewById(R.id.login_ed_password);
        btnLogin = (Button) findViewById(R.id.btn_login_login);
        btnSearch = (Button) findViewById(R.id.btn_search_login);
        btnRegister = (Button) findViewById(R.id.btn_register_login);
    }

    @Override
    public void init(Bundle save) {
        etEmail.setOnClickListener(this);
        etPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_ed_username:
                etEmail.setFocusable(true);
                etEmail.requestFocus();
                InputMethodManager immeail = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                immeail.showSoftInput(etEmail, InputMethodManager.SHOW_FORCED);
                break;
            case R.id.login_ed_password:
                etPassword.requestFocus();
                etPassword.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etPassword, InputMethodManager.SHOW_FORCED);
                break;
            case R.id.btn_login_login:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (!Other.isValidEmail(email)) {
                    toastMessage("Enter  valid Email id");
                    return;
                } else if (password.length() < 5) {
                    toastMessage("Enter  valid  Password");
                    return;
                } else {
                    loginWebservice(email, password);
                }
                break;
            case R.id.btn_search_login:
                sendToThisActivity(SearchActivity.class);
                break;
            case R.id.btn_register_login:
                sendToThisActivity(CreateForRegister.class);
                break;
        }
    }


    private void loginWebservice(String email, String password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("email=").append(email);
        stringBuilder.append("&password=").append(password);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.LOGIN, 0, content, true, "login in..", this);
        getDataUsingWService.execute();
    }


    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("login", "webservice response is" + responseData);
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            String message = jsonObject.getString("message");
            if (response_code.equalsIgnoreCase("200")) {
                Map<String, Object> loginMapObject = new Gson().fromJson(jsonObject.getJSONObject("data").toString(), Map.class);
                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("image");
                AppPref.getInstance().setNoOfImage(jsonArray.length());
                List<ImageModel> imageList = new ArrayList<ImageModel>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    int image_id = jsonObject1.getInt("image_id");
                    String imageurl = "https://firebasestorage.googleapis.com/v0/b/thehindu-24e87.appspot.com/o/" + jsonObject1.getString("image") + "?alt=media";
                    String profile = jsonObject1.getString("profile");
                    imageList.add(new ImageModel(image_id, imageurl, profile));
                }
                Gson gson = new Gson();
                String imageUrls = gson.toJson(imageList);
                AppPref.getInstance().setImageUrls(imageUrls);
                if (loginMapObject.containsKey("user_id")) {
                    AppPref.getInstance().setuserId(jsonObject.getJSONObject("data").get("user_id").toString());
                }
                if (loginMapObject.containsKey("createfor")) {
                    AppPref.getInstance().setCreateFor(loginMapObject.get("createfor").toString());
                }
                if (loginMapObject.containsKey("name")) {
                    AppPref.getInstance().setName(loginMapObject.get("name").toString());
                }
                if (loginMapObject.containsKey("dob")) {
                    AppPref.getInstance().setDob(loginMapObject.get("dob").toString());
                }
                if (loginMapObject.containsKey("height")) {
                    AppPref.getInstance().setHeight(loginMapObject.get("height").toString());
                }
                if (loginMapObject.containsKey("state")) {
                    AppPref.getInstance().setState(loginMapObject.get("state").toString());
                }
                if (loginMapObject.containsKey("city")) {
                    AppPref.getInstance().setCity(loginMapObject.get("city").toString());
                }
                if (loginMapObject.containsKey("mother_tongue")) {
                    AppPref.getInstance().setMotherTongue(loginMapObject.get("mother_tongue").toString());
                }
                if (loginMapObject.containsKey("religion")) {
                    AppPref.getInstance().setReligion(loginMapObject.get("religion").toString());
                }
                if (loginMapObject.containsKey("caste")) {
                    AppPref.getInstance().setCaste(loginMapObject.get("caste").toString());
                }

                if (loginMapObject.containsKey("marital_status")) {
                    AppPref.getInstance().setMaritalStatus(loginMapObject.get("marital_status").toString());
                }
                if (loginMapObject.containsKey("have_child")) {
                    AppPref.getInstance().setHaveChild(loginMapObject.get("have_child").toString());
                }
                if (loginMapObject.containsKey("Horoscope_check")) {
                    AppPref.getInstance().setHoroscopeMatch(loginMapObject.get("Horoscope_check").toString());
                }
                if (loginMapObject.containsKey("manglik")) {
                    AppPref.getInstance().setManglik(loginMapObject.get("manglik").toString());
                }
                if (loginMapObject.containsKey("caste")) {
                    AppPref.getInstance().setCaste(loginMapObject.get("caste").toString());
                }
                if (loginMapObject.containsKey("highest_education")) {
                    AppPref.getInstance().setHighestEducation(loginMapObject.get("highest_education").toString());
                }
                if (loginMapObject.containsKey("pg_degree")) {
                    AppPref.getInstance().setPgDegree(loginMapObject.get("pg_degree").toString());
                }
                if (loginMapObject.containsKey("pg_college")) {
                    AppPref.getInstance().setPgCollge(loginMapObject.get("pg_college").toString());
                }
                if (loginMapObject.containsKey("ug_degree")) {
                    AppPref.getInstance().setUgDegree(loginMapObject.get("ug_degree").toString());
                }
                if (loginMapObject.containsKey("ug_college")) {
                    AppPref.getInstance().setUgCollege(loginMapObject.get("ug_college").toString());
                }
                if (loginMapObject.containsKey("occupation")) {
                    AppPref.getInstance().setOccupation(loginMapObject.get("occupation").toString());
                }
                if (loginMapObject.containsKey("income")) {
                    AppPref.getInstance().setIncome(loginMapObject.get("income").toString());
                }
                if (loginMapObject.containsKey("email")) {
                    AppPref.getInstance().setEmailId(loginMapObject.get("email").toString());
                }
                if (loginMapObject.containsKey("phone")) {
                    AppPref.getInstance().setMobile(loginMapObject.get("phone").toString());
                }
                if (loginMapObject.containsKey("about_your_self")) {
                    AppPref.getInstance().setAboutYourSelf(loginMapObject.get("about_your_self").toString());
                }
                if (loginMapObject.containsKey("gotra")) {
                    AppPref.getInstance().setGotra(loginMapObject.get("gotra").toString());
                }
                if (loginMapObject.containsKey("family_based_on")) {
                    AppPref.getInstance().setFamilyBased(loginMapObject.get("family_based_on").toString());
                }
                if (loginMapObject.containsKey("gender")) {
                    AppPref.getInstance().setGender(loginMapObject.get("gender").toString());
                }
                if (loginMapObject.containsKey("country")) {
                    AppPref.getInstance().setCountry(loginMapObject.get("country").toString());
                }
                if (loginMapObject.containsKey("sub_caste")) {
                    AppPref.getInstance().setSubCaste(loginMapObject.get("sub_caste").toString());
                }
                if (loginMapObject.containsKey("about_family")) {
                    AppPref.getInstance().setAboutFamily(loginMapObject.get("about_family").toString());
                }
                if (loginMapObject.containsKey("family_status")) {
                    AppPref.getInstance().setFamilyStatus(loginMapObject.get("family_status").toString());
                }
                AppPref.getInstance().setIsLogin(true);
                sendToThisActivity(DrawerActivity.class);
                finish();
            } else if (response_code.equalsIgnoreCase("202")) {
                toastMessage(message);
            }
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

    }


    private void newDialog() {
        Dialog riderRequestDialog = new Dialog(this, R.style.DialogSlideAnim);
        riderRequestDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        riderRequestDialog.setContentView(R.layout.dialog_second_drawer);
        riderRequestDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = riderRequestDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        riderRequestDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) riderRequestDialog.findViewById(R.id.dialog_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        List<String> list = new ArrayList<String>();
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");
        list.add("jchsjsfj");

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, List objects, int position, int from) {

            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        riderRequestDialog.show();
    }

}
