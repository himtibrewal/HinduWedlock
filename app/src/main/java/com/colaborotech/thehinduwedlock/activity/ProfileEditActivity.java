package com.colaborotech.thehinduwedlock.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.adapter.PagerAdapter;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.fragment.BasicinfoFragment;
import com.colaborotech.thehinduwedlock.fragment.CareerFragment;
import com.colaborotech.thehinduwedlock.fragment.ContactFragment;
import com.colaborotech.thehinduwedlock.fragment.DesiredPartnerFragment;
import com.colaborotech.thehinduwedlock.fragment.EducationFragment;
import com.colaborotech.thehinduwedlock.fragment.FamilyFragment;
import com.colaborotech.thehinduwedlock.fragment.KundliFragment;
import com.colaborotech.thehinduwedlock.fragment.LifeStyleFragment;
import com.colaborotech.thehinduwedlock.fragment.PhotoUploadFragment;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.MultipleModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.utility.FragmentButton;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.colaborotech.thehinduwedlock.utility.OtherFunction.returnMultipleModelArrayList;


/**
 * Created by him on 22-Jun-17.
 */

public class ProfileEditActivity extends BaseActivity implements
        SliderFragment.ReturnView,
        FragmentButton,
        View.OnClickListener,
        SliderFragment.ReturnMultipleView,
        SliderFragment.ReturnDone,
        GetWebServiceData {

    PagerSlidingTabStrip tabLayout;
    DrawerLayout drawerLayout;
    CustomLayoutTitleValue customLayoutTitleValue;
    ImageView ivBack;
    TextView tvHeader;
    TextView tvPreview;
    Dialog dateOfBirthDialog;
    private int OpenFragmentIndex = 0;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public void initialize() {
        if (getIntent().getExtras() != null) {
            OpenFragmentIndex = getIntent().getExtras().getInt("position");
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvPreview = (TextView) findViewById(R.id.toolbar_last);
        ivBack.setOnClickListener(this);
        tvPreview.setOnClickListener(this);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        viewPager.setCurrentItem(OpenFragmentIndex);
        tabLayout = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabLayout.setDividerColor(Color.BLACK);
        tabLayout.setIndicatorColor(Color.BLACK);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.red_dark));
        tabLayout.setIndicatorHeight(3);
        tabLayout.setTextColor(Color.WHITE);
        tabLayout.setViewPager(viewPager);

    }

    @Override
    public void onStart() {
        super.onStart();
        setFragment(new SliderFragment());

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Me (THW0001)");
        tvPreview.setText("Preview");
        tvPreview.setTextColor(getResources().getColor(R.color.red_dark));
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
                toastMessage("comming soon");
                break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PhotoUploadFragment(), "Photo");
        adapter.addFragment(new BasicinfoFragment(), "Basic Info");
        adapter.addFragment(new KundliFragment(), "Kundli");
        adapter.addFragment(new EducationFragment(), "Education");
        adapter.addFragment(new CareerFragment(), "Career");
        adapter.addFragment(new FamilyFragment(), "Family");
        adapter.addFragment(new LifeStyleFragment(), "Lifestyle");
        adapter.addFragment(new ContactFragment(), "Contact");
        adapter.addFragment(new DesiredPartnerFragment(), "Desired Partner");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {


            //kundli  fragment
            case R.id.ctv_horoscope_match_kundli_info_fragment:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_rashi_kundli_info_fragment:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_nakshtra_kundli_info_fragment:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_manglik_kundli_info_fragment:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_future_plans_career_info_fragment:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;


        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    //kundli  fragment
                    case R.id.ctv_horoscope_match_kundli_info_fragment:
                        customLayoutTitleValue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setHoroscopeMatch(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_rashi_kundli_info_fragment:
                        customLayoutTitleValue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setRashi(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_nakshtra_kundli_info_fragment:
                        customLayoutTitleValue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setNakshatra(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_manglik_kundli_info_fragment:
                        customLayoutTitleValue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setManglik(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_future_plans_career_info_fragment:
                        customLayoutTitleValue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        secondDialog();
                        break;
                }
            }
        });


    }

    @Override
    public void buttonClicked(CustomLayoutTitleValue customLayoutTitleValue) {
        this.customLayoutTitleValue = customLayoutTitleValue;
        SliderFragment.getInstance().setReturnView(this);
        SliderFragment.getInstance().setReturnMultipleView(this);
        SliderFragment.getInstance().setReturnDone(this);
        switch (customLayoutTitleValue.getId()) {

            //basic  info
            case R.id.ctv_aboutme_basic_info_fragment:
                Intent intent = new Intent(this, EnterDataActivity.class);
                intent.putExtra("value", AppPref.getInstance().getAboutYourSelf());
                intent.putExtra("header", "About Me");
                startActivityForResult(intent, 100);
                break;
            case R.id.ctv_basic_detail_info_fragment:
                sendToThisActivity(BasicDetailActivity.class);
                break;
            case R.id.ctv_ethnicity_basic_info_fragment:
                sendToThisActivity(EthinicityActivity.class);
                break;
            case R.id.ctv_appearance_basic_info_fragment:
                sendToThisActivity(AppearanceActivity.class);
                break;
            case R.id.ctv_special_cases_basic_info_fragment:
                sendToThisActivity(SpclCaseActivity.class);
                break;

            //kundli fragment

            case R.id.ctv_horoscope_match_kundli_info_fragment:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.horoscopeModelList, R.id.ctv_horoscope_match_kundli_info_fragment, "Horoscope Must for Marriage?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_rashi_kundli_info_fragment:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.rashiModelList, R.id.ctv_rashi_kundli_info_fragment, "Rashi");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_nakshtra_kundli_info_fragment:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.nakshatraModelList, R.id.ctv_nakshtra_kundli_info_fragment, "Nakshatra");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_manglik_kundli_info_fragment:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.manglikModelList, R.id.ctv_manglik_kundli_info_fragment, "Manglik");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;


            //Education Fragment
            case R.id.ctv_about_edu_education_info_fragment:
                Intent intentaboutEdu = new Intent(this, EnterDataActivity.class);
                intentaboutEdu.putExtra("value", AppPref.getInstance().getAboutEdu());
                intentaboutEdu.putExtra("header", "About My Education");
                startActivityForResult(intentaboutEdu, 200);
                break;
            case R.id.ctv_college_detail_education_info_fragment:
                sendToThisActivity(CollegeDetailActivity.class);
                break;

            //career  fragment
            case R.id.ctv_about_my_career_career_info_fragment:
                Intent intent11 = new Intent(this, EnterDataActivity.class);
                intent11.putExtra("value", AppPref.getInstance().getAboutCareer());
                intent11.putExtra("header", "About My Career");
                startActivityForResult(intent11, 300);
                break;
            case R.id.ctv_career_deatil_career_info_fragment:
                sendToThisActivity(EditCareerDetailActivity.class);
                break;
            case R.id.ctv_future_plans_career_info_fragment:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.manglikModelList, R.id.ctv_future_plans_career_info_fragment, "Interested in Setting abroad?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

            //family fragment

            case R.id.ctv_about_my_family_family_info_fragment:
                Intent intentaou = new Intent(this, EnterDataActivity.class);
                intentaou.putExtra("value", AppPref.getInstance().getAboutFamily());
                intentaou.putExtra("header", "About My Family");
                startActivityForResult(intentaou, 400);
                break;
            case R.id.ctv_family_family_info_fragment:
                sendToThisActivity(EditFamilyDetailActivity.class);
                break;
            case R.id.ctv_parent_family_info_fragment:
                sendToThisActivity(ParentsActivity.class);
                break;
            case R.id.ctv_siblings_family_info_fragment:
                sendToThisActivity(SbilingsActivity.class);
                break;

            //lifestyle  fragment
            case R.id.ctv_habits_lifestyle_info_fragment:
                sendToThisActivity(HabitsActivity.class);
                break;
            case R.id.ctv_assert_lifestyle_info_fragment:
                sendToThisActivity(AssetsActivity.class);
                break;
            case R.id.ctv_skills_lifestyle_info_fragment:
                sendToThisActivity(SkillsActivity.class);
                break;
            case R.id.ctv_hobbies_lifestyle_info_fragment:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.languageModelList, ""), R.id.ctv_hobbies_lifestyle_info_fragment, "Hobbies");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_interests_lifestyle_info_fragment:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.languageModelList, ""), R.id.ctv_interests_lifestyle_info_fragment, "Interests");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_favourite_lifestyle_info_fragment:
                sendToThisActivity(FavouriteActivity.class);

                break;

        }


    }

    @Override
    public void getMultipleAdapterView(View view, List Objects, int position, int from) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        MultipleModel multipleModel = (MultipleModel) Objects.get(position);
        switch (from) {
            case R.id.ctv_hobbies_lifestyle_info_fragment:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.ctv_interests_lifestyle_info_fragment:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
        }

    }

    @Override
    public void selectedMultipleModelList(List<MultipleModel> multipleModelList, int selection) {
        String selected_value = "";
        switch (selection) {
            case R.id.ctv_hobbies_lifestyle_info_fragment:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                customLayoutTitleValue.setValue(selected_value);
                AppPref.getInstance().setHobbies(selected_value);
                break;
            case R.id.ctv_interests_lifestyle_info_fragment:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                customLayoutTitleValue.setValue(selected_value);
                AppPref.getInstance().setInterest(selected_value);
                break;
        }
        drawerLayout.closeDrawer(Gravity.RIGHT);

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            sendAboutMeToServer(textdata, "about_your_self=", AppUrls.UPDATE_ABOUT_YOURSELF);
            customLayoutTitleValue.setValue(textdata);
            AppPref.getInstance().setAboutYourSelf(textdata.toString().trim());
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            sendAboutMeToServer(textdata, "about_education=", AppUrls.UPDATE_ABOUT_EDUCATION);
            customLayoutTitleValue.setValue(textdata);
            AppPref.getInstance().setAboutEdu(textdata.toString().trim());
        } else if (requestCode == 300 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            customLayoutTitleValue.setValue(textdata);
            AppPref.getInstance().setAboutCareer(textdata.toString().trim());
        } else if (requestCode == 400 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            customLayoutTitleValue.setValue(textdata);
            AppPref.getInstance().setAboutFamily(textdata.toString().trim());
            sendAboutMeToServer(textdata, "about_family=", AppUrls.UPDATE_ABOUT_FAMILY);
        }
    }

    private void sendAboutMeToServer(String data, String Key, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Key).append(data);
        stringBuilder.append("&user_id=").append(AppPref.getInstance().getuserId());
        String content = stringBuilder.toString();
        AppPref.getInstance().setAboutYourSelf(data);
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, url, 0, content, true, "Please wait", this);
        getDataUsingWService.execute();
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response_about", "is" + responseData);
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {

            }
        } catch (JSONException e) {

        }

    }

    private void secondDialog() {
        dateOfBirthDialog = new Dialog(this, R.style.DialogSlideAnim);
        dateOfBirthDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dateOfBirthDialog.setContentView(R.layout.dialog_second_drawer);
        dateOfBirthDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dateOfBirthDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        dateOfBirthDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) dateOfBirthDialog.findViewById(R.id.dialog_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(TheHinduWedLockApp.countryModelList, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, List objects, int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customLayoutTitleValue.setValue("interested in setting abroad ? | Work after marriage?");
                        dateOfBirthDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        dateOfBirthDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
