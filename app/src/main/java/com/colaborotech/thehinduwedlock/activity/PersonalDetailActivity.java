package com.colaborotech.thehinduwedlock.activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;

import java.util.Calendar;
import java.util.List;

/**
 * Created by him on 15-Jun-17.
 */

public class PersonalDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {
    private DrawerLayout drawerLayout;
    private ImageView ivBack;
    private TextView tvHeader;
    private LinearLayout llGender;
    private RelativeLayout rlFemale;
    private RelativeLayout rlMale;
    private CustomLayoutTitleValue ctvDateOfBirth;
    private CustomLayoutTitleValue ctvHeight;
    private CustomLayoutTitleValue ctvCountry;
    private TextView tvNext;
    private String gender = "";
    private Dialog seconDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_personal_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        llGender = (LinearLayout) findViewById(R.id.ll_gender_personal_detail);
        rlFemale = (RelativeLayout) findViewById(R.id.rl_female_personal_detail);
        rlMale = (RelativeLayout) findViewById(R.id.rl_male_personal_detail);
        ctvDateOfBirth = (CustomLayoutTitleValue) findViewById(R.id.layout_date_of_birth_personal_detail);
        ctvHeight = (CustomLayoutTitleValue) findViewById(R.id.layout_height_personal_detail);
        ctvCountry = (CustomLayoutTitleValue) findViewById(R.id.layout_country_personal_detail);
        tvNext = (TextView) findViewById(R.id.tv_next_personal_detail);
        ivBack.setOnClickListener(this);
        rlFemale.setOnClickListener(this);
        rlMale.setOnClickListener(this);
        ctvDateOfBirth.setOnClickListener(this);
        ctvHeight.setOnClickListener(this);
        ctvCountry.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {

        tvHeader.setText("Personal Detail");
        if (AppPref.getInstance().getCreateFor().equalsIgnoreCase("SISTER") || AppPref.getInstance().getCreateFor().equalsIgnoreCase("Daughter") || AppPref.getInstance().getCreateFor().equalsIgnoreCase("BROTHER") || AppPref.getInstance().getCreateFor().equalsIgnoreCase("SON")) {
            llGender.setVisibility(View.GONE);
            gender = AppPref.getInstance().getGender();
        }
        if (!AppPref.getInstance().getDob().equalsIgnoreCase("")) {
            ctvDateOfBirth.setValue(AppPref.getInstance().getDob());
        }
        if (!AppPref.getInstance().getHeight().equalsIgnoreCase("")) {
            ctvHeight.setValue(AppPref.getInstance().getHeight());
        }
        if (!AppPref.getInstance().getCountry().equalsIgnoreCase("")) {
            ctvCountry.setValue(AppPref.getInstance().getCountry() + " - " + AppPref.getInstance().getState() + " - " + AppPref.getInstance().getCity());
        }

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
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
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.rl_female_personal_detail:
                rlFemale.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                rlMale.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
                gender = "0";
                break;
            case R.id.rl_male_personal_detail:
                rlMale.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                rlFemale.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
                gender = "1";
                break;
            case R.id.layout_date_of_birth_personal_detail:
                openDatePicker();
                break;
            case R.id.layout_height_personal_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.heightModelList, R.id.layout_height_personal_detail, "Height");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_country_personal_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.stateModelList, R.id.layout_country_personal_detail, "Country");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_next_personal_detail:
                validation();
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.layout_height_personal_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_country_personal_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.layout_height_personal_detail:
                        ctvHeight.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setHeight(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setHeightId(((DataModel) Objects.get(position)).get_id());
                        break;
                    case R.id.layout_country_personal_detail:
                        ctvCountry.setValue(((DataModel) Objects.get(position)).get_dataName());
                        if (((DataModel) Objects.get(position)).get_id() == 1) {
                            secondDialog("Country", TheHinduWedLockApp.countryModelList, "", R.id.layout_country_personal_detail);
                        } else {
                            secondDialog("City", TheHinduWedLockApp.cityModelList, "India - " + ((DataModel) Objects.get(position)).get_dataName(), R.id.layout_country_personal_detail);
                            AppPref.getInstance().setCountry("India");
                            AppPref.getInstance().setCounrtyId(1);
                            AppPref.getInstance().setStateId(((DataModel) Objects.get(position)).get_id());
                            AppPref.getInstance().setState(((DataModel) Objects.get(position)).get_dataName());
                        }
                        break;
                }


            }
        });

    }

    private void validation() {
        String dateofBirth = ctvDateOfBirth.getValue().trim();
        String height = ctvHeight.getValue().trim();
        String country = ctvCountry.getValue().trim();
        if (gender.equalsIgnoreCase("")) {
            toastMessage("please select gender");
            return;
        } else if (dateofBirth.equalsIgnoreCase("") || dateofBirth.equalsIgnoreCase("not filled")) {
            toastMessage("please enter DOB");
            return;
        } else if (height.equalsIgnoreCase("") || height.equalsIgnoreCase("not filled")) {
            toastMessage("please select Height");
            return;
        } else if (country.equalsIgnoreCase("") || country.equalsIgnoreCase("not filled")) {
            toastMessage("please select country");
            return;
        } else {
            AppPref.getInstance().setGender(gender);
            AppPref.getInstance().setDob(dateofBirth);
            AppPref.getInstance().setHeight(height);
            sendToThisActivity(CareerDetailActivity.class);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void openDatePicker() {
        Calendar cal1 = Calendar.getInstance();
        try {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ctvDateOfBirth.setValue(i2 + "-" + i1 + "-" + i);
                    AppPref.getInstance().setDob(i2 + "-" + i1 + "-" + i);
                }

            }, 1998, 10, 01);
            if (gender.trim().equalsIgnoreCase("1")) {
                cal1.add(Calendar.YEAR, -21);
            } else {
                cal1.add(Calendar.YEAR, -18);
            }
            datePickerDialog.getDatePicker().setMaxDate(cal1.getTimeInMillis());
            cal1.add(Calendar.YEAR, -42);
            datePickerDialog.getDatePicker().setMinDate(cal1.getTimeInMillis());
            datePickerDialog.show();
        } catch (Exception e) {

        }
    }


    private void secondDialog(String header, List<DataModel> list, final String data, int id) {
        seconDialog = new Dialog(this, R.style.DialogSlideAnim);
        seconDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        seconDialog.setContentView(R.layout.dialog_second_drawer);
        seconDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = seconDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        seconDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) seconDialog.findViewById(R.id.dialog_recyclerView);
        TextView textView = (TextView) seconDialog.findViewById(R.id.tv_top_text_second_dilaog);
        textView.setText(header);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, final List objects, final int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.equalsIgnoreCase("")) {
                            ctvCountry.setValue(((DataModel) objects.get(position)).get_dataName());
                            AppPref.getInstance().setCountry(((DataModel) objects.get(position)).get_dataName());
                            AppPref.getInstance().setCounrtyId(((DataModel) objects.get(position)).get_id());
                        } else {
                            ctvCountry.setValue(data + "-" + ((DataModel) objects.get(position)).get_dataName());
                            AppPref.getInstance().setCity(((DataModel) objects.get(position)).get_dataName());
                            AppPref.getInstance().setCityId(((DataModel) objects.get(position)).get_id());
                        }
                        seconDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        seconDialog.show();
    }
}

