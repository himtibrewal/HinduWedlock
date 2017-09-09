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

public class EditFamilyDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvFamilyStatus;
    CustomLayoutTitleValue ctvFamilyValues;
    CustomLayoutTitleValue ctvFamilyType;
    CustomLayoutTitleValue ctvLivingWithParents;
    TextView tvCancel, tvheader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_edit_family;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvFamilyStatus = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_status_edit_family);
        ctvFamilyValues = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_values_edit_family);
        ctvFamilyType = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_type_edit_family);
        ctvLivingWithParents = (CustomLayoutTitleValue) findViewById(R.id.ctv_living_with_parents_edit_family);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvheader = (TextView) findViewById(R.id.toolbar_title);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvheader.setText("Family");
        ctvFamilyStatus.setOnClickListener(this);
        ctvFamilyValues.setOnClickListener(this);
        ctvFamilyType.setOnClickListener(this);
        ctvLivingWithParents.setOnClickListener(this);
    }


    @Override
    public void init(Bundle save) {
        ctvFamilyStatus.setValue(AppPref.getInstance().getFamilyStatus());
        ctvFamilyValues.setValue(AppPref.getInstance().getFamilyValues());
        ctvFamilyType.setValue(AppPref.getInstance().getFamilyType());
        ctvLivingWithParents.setValue(AppPref.getInstance().getlivingWithParents());
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
        AppPref.getInstance().setFamilyStatus(ctvFamilyStatus.getValue());
        AppPref.getInstance().setFamilyValues(ctvFamilyValues.getValue());
        AppPref.getInstance().setFamilyType(ctvFamilyType.getValue());
        AppPref.getInstance().setlivingWithParents(ctvLivingWithParents.getValue());
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

            case R.id.ctv_family_status_edit_family:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familystatusModelList, R.id.ctv_family_status_edit_family, "Family Status");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_values_edit_family:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyValuesModelList, R.id.ctv_family_values_edit_family, "Family Values");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_type_edit_family:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyTypeModelList, R.id.ctv_family_type_edit_family, "Family Type");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_living_with_parents_edit_family:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.livingwithParents, R.id.ctv_living_with_parents_edit_family, "Living with Parents?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_family_status_edit_family:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_values_edit_family:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_type_edit_family:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_living_with_parents_edit_family:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_family_status_edit_family:
                        ctvFamilyStatus.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_family_values_edit_family:
                        ctvFamilyValues.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_family_type_edit_family:
                        ctvFamilyType.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_living_with_parents_edit_family:
                        ctvLivingWithParents.setValue(((DataModel) Objects.get(position)).get_dataName());
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

}
