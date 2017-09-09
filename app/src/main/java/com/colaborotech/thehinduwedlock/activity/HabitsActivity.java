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

public class HabitsActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvDiet;
    CustomLayoutTitleValue ctvSmoke;
    CustomLayoutTitleValue ctvDrink;
    CustomLayoutTitleValue ctvOpenPets;
    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_habits;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvDiet = (CustomLayoutTitleValue) findViewById(R.id.ctv_diet_habits);
        ctvSmoke = (CustomLayoutTitleValue) findViewById(R.id.ctv_smoke_habits);
        ctvDrink = (CustomLayoutTitleValue) findViewById(R.id.ctv_drink_habits);
        ctvOpenPets = (CustomLayoutTitleValue) findViewById(R.id.ctv_pets_habits);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);

        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Habits");

        ctvDiet.setOnClickListener(this);
        ctvSmoke.setOnClickListener(this);
        ctvDrink.setOnClickListener(this);
        ctvOpenPets.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvDiet.setValue(AppPref.getInstance().getDiet());
        ctvSmoke.setValue(AppPref.getInstance().getSmoke());
        ctvDrink.setValue(AppPref.getInstance().getDrink());
        ctvOpenPets.setValue(AppPref.getInstance().getpets());
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
        AppPref.getInstance().setDiet(ctvDiet.getValue());
        AppPref.getInstance().setSmoke(ctvDrink.getValue());
        AppPref.getInstance().setDrink(ctvDrink.getValue());
        AppPref.getInstance().setPets(ctvOpenPets.getValue());
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
            case R.id.ctv_diet_habits:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_diet_habits, "Please specify your diet");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_smoke_habits:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_smoke_habits, "Do you Smoke?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_drink_habits:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.casteModelList, R.id.ctv_drink_habits, "Do you drink?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_pets_habits:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.casteModelList, R.id.ctv_pets_habits, "Open to pets?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_diet_habits:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_smoke_habits:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_drink_habits:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_pets_habits:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_diet_habits:
                        ctvDiet.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_smoke_habits:
                        ctvSmoke.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_drink_habits:
                        ctvDrink.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_pets_habits:
                        ctvOpenPets.setValue(((DataModel) Objects.get(position)).get_dataName());
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