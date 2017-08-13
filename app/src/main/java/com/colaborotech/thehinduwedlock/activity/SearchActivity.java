package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.DrawerFragment;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.MultipleModel;
import com.colaborotech.thehinduwedlock.utility.CustomTextView;

import java.util.List;

import static com.colaborotech.thehinduwedlock.utility.OtherFunction.returnMultipleModelArrayList;


public class SearchActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView, SliderFragment.ReturnMultipleView, SliderFragment.ReturnDone {

    DrawerLayout drawerLayout;

    CustomTextView tvAgeLower;
    CustomTextView tvAgeUpper;
    CustomTextView tvHeightLower;
    CustomTextView tvHeightUpper;
    CustomTextView tvReligion;
    CustomTextView tvMotherTounge;
    CustomTextView tvIncomeLower;
    CustomTextView tvIncomeUpper;
    CustomTextView tvCountry;
    CustomTextView tvState;
    TextView tvProfileWithPhoto;
    TextView tvAllProfile;
    TextView tvSubmit;
    TextView tvTitle;
    ImageView ivDrawer;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initialize() {

        // noToolbar  theme  so  Add  this  tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // initialization
        ivDrawer = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvTitle.setText("Search");
        ivDrawer.setImageDrawable(getResources().getDrawable(R.drawable.lines_red));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT); // on  slide  drawer  not  open
        tvAgeLower = (CustomTextView) findViewById(R.id.lower_age_limit_searchActivity);
        tvAgeUpper = (CustomTextView) findViewById(R.id.upper_age_limit_searchActivity);
        tvHeightLower = (CustomTextView) findViewById(R.id.lower_height_limit_searchActivity);
        tvHeightUpper = (CustomTextView) findViewById(R.id.upper_height_limit_searchActivity);
        tvReligion = (CustomTextView) findViewById(R.id.religion_searchActivity);
        tvMotherTounge = (CustomTextView) findViewById(R.id.mother_tongue_searchActivity);
        tvIncomeLower = (CustomTextView) findViewById(R.id.lower_income_limit_searchActivity);
        tvIncomeUpper = (CustomTextView) findViewById(R.id.upper_income_limit_searchActivity);
        tvCountry = (CustomTextView) findViewById(R.id.country_searchActivity);
        tvState = (CustomTextView) findViewById(R.id.state_searchActivity);
        tvProfileWithPhoto = (TextView) findViewById(R.id.profile_with_photo_searchActivity);
        tvAllProfile = (TextView) findViewById(R.id.all_profile_searchActivity);
        tvSubmit = (TextView) findViewById(R.id.submit_searchActivity);
        ivDrawer.setOnClickListener(this);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        //click listener
        tvAgeLower.setOnClickListener(this);
        tvAgeUpper.setOnClickListener(this);
        tvHeightLower.setOnClickListener(this);
        tvHeightUpper.setOnClickListener(this);
        tvReligion.setOnClickListener(this);
        tvMotherTounge.setOnClickListener(this);
        tvIncomeLower.setOnClickListener(this);
        tvIncomeUpper.setOnClickListener(this);
        tvCountry.setOnClickListener(this);
        tvState.setOnClickListener(this);
        tvProfileWithPhoto.setOnClickListener(this);
        tvAllProfile.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        tvAgeLower.setValue("18 Years");
        tvAgeUpper.setValue("69 Years");
        tvHeightLower.setValue("4\'10\"");
        tvHeightUpper.setValue("7\'");
        tvReligion.setValue("Religion");
        tvMotherTounge.setValue("Mother Tongue");
        tvIncomeLower.setValue("Rs. 0");
        tvIncomeUpper.setValue("and above");
        tvCountry.setValue("Country");
        tvState.setValue("City/state");
        tvProfileWithPhoto.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
        tvProfileWithPhoto.setTextColor(getResources().getColor(R.color.white));
        tvProfileWithPhoto.setPadding(0, 20, 0, 20);
        tvAllProfile.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        tvAllProfile.setTextColor(getResources().getColor(R.color.light_black));
        tvAllProfile.setPadding(0, 20, 0, 20);
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }


    @Override
    public void onStart() {
        super.onStart();
        setFragment(new SliderFragment());
        setFragmentDrawer(new DrawerFragment());
    }

//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public void setFragmentDrawer(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        SliderFragment.getInstance().setReturnMultipleView(this);
        SliderFragment.getInstance().setReturnDone(this);
        switch (v.getId()) {
            case R.id.iv_back:
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
                break;
            case R.id.lower_age_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.ageModelList, R.id.lower_age_limit_searchActivity, "Select Lower Age Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.upper_age_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.ageModelList, R.id.upper_age_limit_searchActivity, "Select Upper Age Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.lower_height_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.heightModelList, R.id.lower_height_limit_searchActivity, "Select Lower Height Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.upper_height_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.heightModelList, R.id.upper_height_limit_searchActivity, "Select Upper Height Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.religion_searchActivity:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.religion_searchActivity, "Select Religion");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.mother_tongue_searchActivity:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.languageModelList, ""), R.id.mother_tongue_searchActivity, "Select Mother Tongue");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.lower_income_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.incomeModelList, R.id.lower_income_limit_searchActivity, "Select Lower Income Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.upper_income_limit_searchActivity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.incomeModelList, R.id.upper_income_limit_searchActivity, "Select Upper Income Limit");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.country_searchActivity:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.countryModelList, ""), R.id.country_searchActivity, "Select Country");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.state_searchActivity:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.stateModelList, ""), R.id.state_searchActivity, "Select State");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.profile_with_photo_searchActivity:
                tvProfileWithPhoto.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                tvProfileWithPhoto.setTextColor(getResources().getColor(R.color.white));
                tvAllProfile.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
                tvAllProfile.setTextColor(getResources().getColor(R.color.light_black));
                tvAllProfile.setPadding(0, 20, 0, 20);
                tvProfileWithPhoto.setPadding(0, 20, 0, 20);
                break;
            case R.id.all_profile_searchActivity:
                tvAllProfile.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                tvAllProfile.setTextColor(getResources().getColor(R.color.white));
                tvProfileWithPhoto.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
                tvProfileWithPhoto.setTextColor(getResources().getColor(R.color.light_black));
                tvAllProfile.setPadding(0, 20, 0, 20);
                tvProfileWithPhoto.setPadding(0, 20, 0, 20);
                break;
            case R.id.submit_searchActivity:
                sendToThisActivity(ProfileListActivity.class);
                break;


        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView listTextView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.lower_age_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.upper_age_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.lower_height_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.upper_height_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.lower_income_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.upper_income_limit_searchActivity:
                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
//            case R.id.country_searchActivity:
//                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
//                break;
//            case R.id.state_searchActivity:
//                listTextView.setText(((DataModel) Objects.get(position)).get_dataName());
//                break;
        }
        listTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.lower_age_limit_searchActivity:
                        tvAgeLower.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.upper_age_limit_searchActivity:
                        tvAgeUpper.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.lower_height_limit_searchActivity:
                        tvHeightLower.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.upper_height_limit_searchActivity:
                        tvHeightUpper.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.lower_income_limit_searchActivity:
                        tvIncomeLower.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.upper_income_limit_searchActivity:
                        tvIncomeUpper.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
//                    case R.id.country_searchActivity:
//                        tvCountry.setValue(((DataModel) Objects.get(position)).get_dataName());
//                        break;
//                    case R.id.state_searchActivity:
//                        tvState.setValue(((DataModel) Objects.get(position)).get_dataName());
//                        break;

                }
                drawerLayout.closeDrawer(Gravity.RIGHT);


            }
        });

    }

    @Override
    public void getMultipleAdapterView(View view, List Objects, int position, int from) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        MultipleModel multipleModel = (MultipleModel) Objects.get(position);
        switch (from) {
            case R.id.religion_searchActivity:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.mother_tongue_searchActivity:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.country_searchActivity:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.state_searchActivity:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
        }

    }

    @Override
    public void selectedMultipleModelList(List<MultipleModel> multipleModelList, int selection) {

        switch (selection) {
            case R.id.mother_tongue_searchActivity:
                String selected_value = "";
                String selected_stateid1 = "";
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                tvMotherTounge.setValue(selected_value);
                break;
        }
        drawerLayout.closeDrawer(Gravity.RIGHT);


    }
}

