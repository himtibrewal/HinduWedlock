package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by him on 18-Jun-17.
 */

public class LoginDetailActivity extends BaseActivity implements View.OnClickListener, GetWebServiceData {
    ImageView ivBack;
    TextView tvHeader;
    LinearLayout llFullName;
    EditText etFullName;
    TextView tvShowDialog;
    ImageView ivShowIcon;
    LinearLayout llEmail;
    EditText etEmail;
    LinearLayout llPassword;
    EditText etPassword;
    TextView tvShowPassword;
    LinearLayout llPhoneNumber;
    EditText etPhoneNumber;
    EditText etCountryCode;
    TextView tvTermsAndCondition;
    TextView tvNext;
    Dialog privacyDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        llFullName = (LinearLayout) findViewById(R.id.ll_full_name_login_detail);
        etFullName = (EditText) findViewById(R.id.et_full_name_value_login_detail);
        tvShowDialog = (TextView) findViewById(R.id.tv_Show_to_all_login_detail);
        llEmail = (LinearLayout) findViewById(R.id.ll_email_id_login_detail);
        etEmail = (EditText) findViewById(R.id.et_value_email_id_login_detail);
        llPassword = (LinearLayout) findViewById(R.id.ll_password_login_detail);
        etPassword = (EditText) findViewById(R.id.et_value_password_login_detail);
        tvShowPassword = (TextView) findViewById(R.id.show_password_login_detail);
        llPhoneNumber = (LinearLayout) findViewById(R.id.ll_phone_number_login_detail);
        etCountryCode = (EditText) findViewById(R.id.et_country_code_phone_number_login_detail);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number_login_detail);
        tvTermsAndCondition = (TextView) findViewById(R.id.tv_terms_and_condition_login_detail);
        tvNext = (TextView) findViewById(R.id.tv_next_login_detail);
        ivBack.setOnClickListener(this);
        llFullName.setOnClickListener(this);
        tvShowDialog.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Login Details");
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
            case R.id.tv_Show_to_all_login_detail:
                privacyDialog();
                break;
            case R.id.tv_next_login_detail:
                validation();
//                Intent intent = new Intent(getApplicationContext(), WriteAboutActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                break;
        }

    }

    private void validation() {
        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        String countryCode = etCountryCode.getText().toString();
        if (fullName.equalsIgnoreCase("")) {
            toastMessage("please enter Full name");
            return;
        } else if (email.equalsIgnoreCase("")) {
            toastMessage("please enter email id");
            return;
        } else if (password.equalsIgnoreCase("")) {
            toastMessage("please enter password");
            return;
        } else if (phone.equalsIgnoreCase("")) {
            toastMessage("please enter mobile no");
            return;
        } else if (countryCode.equalsIgnoreCase("")) {
            toastMessage("please enter Country no");
            return;
        } else {
            AppPref.getInstance().setName(fullName);
            AppPref.getInstance().setEmailId(email);
            AppPref.getInstance().setPassword(password);
            AppPref.getInstance().setMobile(phone);
            sendDataToServer();

        }
    }

    private void sendDataToServer() {

//        m_id=11111&createfor=friend&photo=[jdfdjfnjgbkjgfnkjb,jsddfbhjhbvkjdfbkvd,jhdbvjhfdbvjhfd,vjhfbvjhfdb,]
//        &photo_count=4&from_image=gallery&manage_by=friend&name=Himanshu kumar&name_show=true&gender=Male
//                &dob=12-10-2019&height=4'11"&country=india&state=Bihar&city=narkatiaganj
//                &mother_tongue=Hindi&religion=Hindu&religion_show=false&caste=marwari
//                &sub_caste=bania&family_based_on=jhunjhunu&gotra=aryan&complexion=Fair
//                &body_type=slim&weight=75&challenged=No&thalassemia=No&hiv=No
//                &about_your_self=I am  a good  boy.&marital_status=naver Married&have_child=0&openforAllcaste=yes
//                &Horoscope_check=no&rashi=mesh&nakshatra=no&manglik=Yes&about_education=very  good  education
//        &highest_education=P.hd&pg_degree=ms in  physics&pg_college=IIT delhi&ug_degree=B.tech
//                &ug_college=Gurukula kangri&other_pg_degree=no&other_ug_degree=no
//                &school_name=High  school&occupation=Job(IT)&income=3&about_career=good  career
//                &organization_name=esecfote&setting_abord=yes&work_after_marriage=yes&family_status=good
//                &family_type=High&family_values=high&family_income=1 corer&father_occupation=business
//                &mother_occupation=homee&brother=1&married_brother=0&sister=0&sister_married=0
//                &about_family=good  family&living_with_parents=yes&diet=yes&smoke=no&drink=no
//                &pets=no&own_house=yes&own_car=yes&language_speak=[Hindi,English]&food_cook_detail=Very good  cook
//        &hobbies=[hobbe ,kjbvkjdf]&interest=[criiii,jbsdjvbfd]&favourite_musics=[jvjdfbkjgd,vnjkfvkjfdbkjgdd]
//        &favourite_books=vnfkjbgjdnblgk&favourite_dress_style=kjrbgkjbgbkjndkjgvnkj&favourite_sports=favourite_sports
//                &favourite_cuisines=favourite_cuisines&favourite_movies=favourite_movies&favourite_read=favourite_read&favourite_tv_shows=favourite_tv_shows&favourite_vaction_distination=favourite_vaction_distination&email=himanshu@gmail.com&password=12345678&phone=8882254010&alternate_email_id=alternate_email_id&alternate_mobile_no=alternate_mobile_no&email_veriy=no&email_visible=no&mobile_visible=no&alt_mobile_visible=no&landline_visible=no&alt_email_verify=no&mobile_verify=no&landline_no=no&suitable_time_to_call_start_time=10&suitable_time_to_call_end_time=23&partner_from_age=1&partner_to_age=70&partner_from_height=5"10"&partner_to_height=7'10"&partner_counrtys=[vjkfvndkjb,vkjbfvkjd]&partner_marital_status=partner_marital_status&partner_religion=partner_religion&partner_caste=partner_caste&partner_tongue=partner_tongue&partner_manglik=partner_manglik&partner_diet=partner_diet&partner_smoke=partner_smoke&partner_drink=partner_drink&partner_complexion=partner_complexion&partner_bodytype=partner_bodytype&partner_challenge=partner_challenge&manage_strict_partner=manage_strict_partner&partner_give_outside=partner_give_outside
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createfor=").append(AppPref.getInstance().getCreateFor());
        stringBuilder.append("&dob=").append(AppPref.getInstance().getDob());
        stringBuilder.append("&height=").append(AppPref.getInstance().getHeight());
        stringBuilder.append("&country=").append(AppPref.getInstance().getCountry());
        stringBuilder.append("&state=").append(AppPref.getInstance().getState());
        stringBuilder.append("&city=").append(AppPref.getInstance().getCity());
        stringBuilder.append("&highest_education=").append(AppPref.getInstance().getHighestEducation());
        stringBuilder.append("&pg_college=").append(AppPref.getInstance().getPgCollge());
        stringBuilder.append("&pg_degree=").append(AppPref.getInstance().getPgDegree());
        stringBuilder.append("&ug_college=").append(AppPref.getInstance().getUgCollege());
        stringBuilder.append("&ug_degree=").append(AppPref.getInstance().getUgDegree());
        stringBuilder.append("&occupation=").append(AppPref.getInstance().getWorkArea());
        stringBuilder.append("&income=").append(AppPref.getInstance().getIncome());
        stringBuilder.append("&marital_status=").append(AppPref.getInstance().getMaritalStatus());
        stringBuilder.append("&have_child=").append(AppPref.getInstance().getHaveChild());
        stringBuilder.append("&mother_tongue=").append(AppPref.getInstance().getMotherTongue());
        stringBuilder.append("&religion=").append(AppPref.getInstance().getReligion());
        stringBuilder.append("&caste=").append(AppPref.getInstance().getCaste());
        stringBuilder.append("&manglik=").append(AppPref.getInstance().getManglik());
        stringBuilder.append("&Horoscope_check=").append(AppPref.getInstance().getHoroscopeMatch());
        stringBuilder.append("&name=").append(AppPref.getInstance().getName());
        stringBuilder.append("&email=").append(AppPref.getInstance().getEmailId());
        stringBuilder.append("&password=").append(AppPref.getInstance().getPassword());
        stringBuilder.append("&phone=").append(AppPref.getInstance().getMobile());
        String content = stringBuilder.toString();
        Log.e("register_c", "is" + content);
        Log.e("url", "is" + AppUrls.REGISTER_URL);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.REGISTER_URL, 0, content, true, "please wait..", this);
        getDataUsingWService.execute();

    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("Register", responseData);
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("userDetail");
                int userid = jsonObject1.getInt("user_id");
                AppPref.getInstance().setuserId(userid + "");
                AppPref.getInstance().setsetIsLogin("isLogin");
                AppPref.getInstance().setNoOfImage(0);
                Intent intent = new Intent(getApplicationContext(), WriteAboutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                toastMessage(responseData);
            }

        } catch (JSONException e) {
            toastMessage(responseData);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void privacyDialog() {
        privacyDialog = new Dialog(this);
        privacyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        privacyDialog.setContentView(R.layout.dialog_show_name);
        privacyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = privacyDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        privacyDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        TextView tvDone = (TextView) privacyDialog.findViewById(R.id.tvDone_show_all);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyDialog.dismiss();
            }
        });


        privacyDialog.show();
    }
}
