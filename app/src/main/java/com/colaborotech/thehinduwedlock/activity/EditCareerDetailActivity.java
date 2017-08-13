package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.CustomLayoutTitleValue;

import java.util.List;

/**
 * Created by him on 08-Jul-17.
 */

public class EditCareerDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {
    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvOrganizationName;
    CustomLayoutTitleValue ctvOccupation;
    CustomLayoutTitleValue ctvAnnualIncome;
    TextView tvCancel, tvHeader, tvSave;

    @Override
    public int getActivityLayout() {
        return R.layout.edit_career_detail_activity;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvOrganizationName = (CustomLayoutTitleValue) findViewById(R.id.ctv_organization_name_edit_career_detail);
        ctvOccupation = (CustomLayoutTitleValue) findViewById(R.id.ctv_occupation_edit_career_detail);
        ctvAnnualIncome = (CustomLayoutTitleValue) findViewById(R.id.ctv_annual_income_edit_career_detail);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("College Details");

        ctvOrganizationName.setOnClickListener(this);
        ctvOccupation.setOnClickListener(this);
        ctvAnnualIncome.setOnClickListener(this);

    }


    @Override
    public void init(Bundle save) {
        ctvOrganizationName.setValue(AppPref.getInstance().getOrganizationName());
        ctvOccupation.setValue(AppPref.getInstance().getOccupation());
        ctvAnnualIncome.setValue(AppPref.getInstance().getAnnualIncome());

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

    private void validate() {
        AppPref.getInstance().setOrganizationName(ctvOrganizationName.getValue());
        AppPref.getInstance().setOccupation(ctvOccupation.getValue());
        AppPref.getInstance().setAnnualIncome(ctvAnnualIncome.getValue());
        finish();
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.tv_save:
                validate();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.ctv_organization_name_edit_career_detail:
                secondDialog("Enter Organization Name", "Enter Organization Name", "", ctvOrganizationName);
                break;
            case R.id.ctv_occupation_edit_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_occupation_edit_career_detail, "PG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_annual_income_edit_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.incomeModelList, R.id.ctv_annual_income_edit_career_detail, "PG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_occupation_edit_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_annual_income_edit_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_occupation_edit_career_detail:
                        ctvOccupation.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_annual_income_edit_career_detail:
                        ctvAnnualIncome.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                }
                drawerLayout.closeDrawer(Gravity.RIGHT);

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    Dialog inputDialog;

    private void secondDialog(String title, String hint, String data, final CustomLayoutTitleValue ctv) {
        inputDialog = new Dialog(this, R.style.DialogSlideAnim2);
        inputDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        inputDialog.setContentView(R.layout.dialog_custom_input);
        inputDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = inputDialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        inputDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        inputDialog.setCancelable(false);
        final TextView tvSubmit = (TextView) inputDialog.findViewById(R.id.tv_input_input_dialog);
        TextView tvTitle = (TextView) inputDialog.findViewById(R.id.tv_title_input_dialog);
        final EditText etValue = (EditText) inputDialog.findViewById(R.id.et_input_input_dialog);
        ImageView ivCancel = (ImageView) inputDialog.findViewById(R.id.iv_close_input_dialog);
        tvTitle.setText(title);
        etValue.setHint(hint);
        etValue.setText(data);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog.dismiss();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etValue.getText().toString().equalsIgnoreCase("")) {
                    ctv.setValue("Not filled");
                } else {
                    ctv.setValue(etValue.getText().toString());
                }
                inputDialog.dismiss();
            }
        });


        inputDialog.show();
    }
}
