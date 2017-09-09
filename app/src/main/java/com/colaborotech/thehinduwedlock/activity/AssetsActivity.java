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
 * Created by him on 09-Jul-17.
 */

public class AssetsActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvOwnCar;
    CustomLayoutTitleValue ctvOwnHouse;
    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_assets;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvOwnHouse = (CustomLayoutTitleValue) findViewById(R.id.ctv_own_house_assets);
        ctvOwnCar = (CustomLayoutTitleValue) findViewById(R.id.ctv_own_car_assets);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);

        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Habits");

        ctvOwnHouse.setOnClickListener(this);
        ctvOwnCar.setOnClickListener(this);

    }

    @Override
    public void init(Bundle save) {
        ctvOwnHouse.setValue(AppPref.getInstance().getOwnHouse());
        ctvOwnCar.setValue(AppPref.getInstance().getOwnCar());

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
        AppPref.getInstance().setOwnHouse(ctvOwnHouse.getValue());
        AppPref.getInstance().setOwnCar(ctvOwnCar.getValue());
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
            case R.id.ctv_own_house_assets:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.assetsModelList, R.id.ctv_own_house_assets, "Own a house?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_own_car_assets:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.assetsModelList, R.id.ctv_own_car_assets, "Own a car?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_own_house_assets:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_own_car_assets:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;

        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_own_house_assets:
                        ctvOwnHouse.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_own_car_assets:
                        ctvOwnCar.setValue(((DataModel) Objects.get(position)).get_dataName());
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