package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;

import java.util.List;

/**
 * Created by him on 08-Jul-17.
 */

public class ParentsActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvFatherIs;
    CustomLayoutTitleValue ctvMotherIs;
    CustomLayoutTitleValue ctvFamilyIncome;
    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_parents;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvFatherIs = (CustomLayoutTitleValue) findViewById(R.id.ctv_father_is_parents);
        ctvMotherIs = (CustomLayoutTitleValue) findViewById(R.id.ctv_mother_is_parents);
        ctvFamilyIncome = (CustomLayoutTitleValue) findViewById(R.id.ctvfamily_income_parents);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);

        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Parents");

        ctvFatherIs.setOnClickListener(this);
        ctvMotherIs.setOnClickListener(this);
        ctvFamilyIncome.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvFatherIs.setValue(AppPref.getInstance().getFatherOccupation());
        ctvMotherIs.setValue(AppPref.getInstance().getMotherOccupation());
        ctvFamilyIncome.setValue(AppPref.getInstance().getFamilyIncome());
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
        AppPref.getInstance().setFatherOccupation(ctvFatherIs.getValue());
        AppPref.getInstance().setMotherOccupation(ctvMotherIs.getValue());
        AppPref.getInstance().setFamilyIncome(ctvFamilyIncome.getValue());
        finish();
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
            case R.id.ctv_father_is_parents:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_father_is_parents, "Father is");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_mother_is_parents:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_mother_is_parents, "Mother is");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctvfamily_income_parents:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.casteModelList, R.id.ctvfamily_income_parents, "Family Income");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_father_is_parents:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_mother_is_parents:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctvfamily_income_parents:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_father_is_parents:
                        ctvFatherIs.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_mother_is_parents:
                        ctvMotherIs.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctvfamily_income_parents:
                        ctvFamilyIncome.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}