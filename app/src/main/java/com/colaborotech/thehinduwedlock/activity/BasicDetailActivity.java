package com.colaborotech.thehinduwedlock.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
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
 * Created by him on 08-Jul-17.
 */

public class BasicDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    EditText etFullName;
    CustomLayoutTitleValue ctvHeight;
    CustomLayoutTitleValue ctvCountry;
    CustomLayoutTitleValue ctvManageBy;

    //
    CustomLayoutTitleValue ctvGender;
    CustomLayoutTitleValue ctvDateofBitrh;
    CustomLayoutTitleValue ctvMaritalStatus;

    TextView tvCancel, tvSave, tvHeader;
    Dialog dateOfBirthDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_basic_detail;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        etFullName = (EditText) findViewById(R.id.et_full_name_value_basic_detail);
        ctvHeight = (CustomLayoutTitleValue) findViewById(R.id.layout_height_basic_detail);
        ctvCountry = (CustomLayoutTitleValue) findViewById(R.id.layout_country_basic_detail);
        ctvGender = (CustomLayoutTitleValue) findViewById(R.id.ctv_gender_basic_detail);
        ctvDateofBitrh = (CustomLayoutTitleValue) findViewById(R.id.ctv_date_of_birth_basic_detail);
        ctvMaritalStatus = (CustomLayoutTitleValue) findViewById(R.id.layout_marital_status_basic_detail);
        ctvManageBy = (CustomLayoutTitleValue) findViewById(R.id.layout_managed_by_basic_detail);
        tvHeader.setText("Basic Details");

        ctvHeight.setOnClickListener(this);
        ctvCountry.setOnClickListener(this);
        ctvManageBy.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        etFullName.setText(AppPref.getInstance().getName());
        ctvHeight.setValue(AppPref.getInstance().getHeight());
        ctvCountry.setValue(AppPref.getInstance().getCountry() + "-" + AppPref.getInstance().getState() + "-" + AppPref.getInstance().getCity());
        ctvGender.setValue(AppPref.getInstance().getGender());
        ctvDateofBitrh.setValue(AppPref.getInstance().getDob());
        ctvMaritalStatus.setValue(AppPref.getInstance().getMaritalStatus());
        ctvManageBy.setValue(AppPref.getInstance().getCreateFor());

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

    private void validation() {
        String name = etFullName.getText().toString().trim();
        if (name.equalsIgnoreCase("")) {
            toastMessage("Enter Full Name");
            return;
        } else {
            AppPref.getInstance().setName(name);
            AppPref.getInstance().setHeight(ctvHeight.getValue());
            AppPref.getInstance().setCountry("India");
            // AppPref.getInstance().se
            finish();

        }
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.layout_height_basic_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.heightModelList, R.id.layout_height_basic_detail, "Height");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_country_basic_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.countryModelList, R.id.layout_country_basic_detail, "Country");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_managed_by_basic_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.countryModelList, R.id.layout_managed_by_basic_detail, "Country");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.layout_height_basic_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_country_basic_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_managed_by_basic_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.layout_height_basic_detail:
                        ctvHeight.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.layout_country_basic_detail:
                        ctvCountry.setValue(((DataModel) Objects.get(position)).get_dataName());
                        secondDialog();
                        break;
                    case R.id.layout_managed_by_basic_detail:
                        ctvManageBy.setValue(((DataModel) Objects.get(position)).get_dataName());
                        secondDialog();
                        break;
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void openDatePicker() {
        String gender = "";
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        try {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ctvDateofBitrh.setValue(i2 + "-" + i1 + "-" + i);
                    // dob.setText(i2 + "-" + OtherUsefulFunctions.returnMonth(++i1) + "-" + i);
                }

            }, 1998, 10, 01);
            if (gender.trim().equalsIgnoreCase("male")) {
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
                        ctvCountry.setValue("India");
                        dateOfBirthDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        dateOfBirthDialog.show();
    }

}
