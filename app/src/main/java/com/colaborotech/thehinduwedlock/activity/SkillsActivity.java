package com.colaborotech.thehinduwedlock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.MultipleModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;

import java.util.List;

import static com.colaborotech.thehinduwedlock.utility.OtherFunction.returnMultipleModelArrayList;

/**
 * Created by him on 10-Jul-17.
 */

public class SkillsActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView, SliderFragment.ReturnMultipleView, SliderFragment.ReturnDone {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvLanguageSpeak;
    CustomLayoutTitleValue ctvCookFood;
    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_skills;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvLanguageSpeak = (CustomLayoutTitleValue) findViewById(R.id.ctv_language_speak_skills);
        ctvCookFood = (CustomLayoutTitleValue) findViewById(R.id.ctv_cook_food_skills);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);

        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Habits");

        ctvLanguageSpeak.setOnClickListener(this);
        ctvCookFood.setOnClickListener(this);

    }

    @Override
    public void init(Bundle save) {
        ctvLanguageSpeak.setValue(AppPref.getInstance().getLanguageSpeak());
        ctvCookFood.setValue(AppPref.getInstance().getFoodCook());

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
        AppPref.getInstance().setLanguageSpeak(ctvLanguageSpeak.getValue());
        AppPref.getInstance().setFoodCook(ctvCookFood.getValue());
        finish();
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnMultipleView(this);
        SliderFragment.getInstance().setReturnDone(this);
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.ctv_language_speak_skills:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.languageModelList, ""), R.id.ctv_language_speak_skills, "Language I Speak");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_cook_food_skills:
                Intent intent11 = new Intent(this, EnterDataActivity.class);
                intent11.putExtra("value", AppPref.getInstance().getFoodCook());
                intent11.putExtra("header", "Food I Cook");
                startActivityForResult(intent11, 100);
                break;

        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {

    }

    @Override
    public void getMultipleAdapterView(View view, List Objects, int position, int from) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        MultipleModel multipleModel = (MultipleModel) Objects.get(position);
        switch (from) {
            case R.id.ctv_language_speak_skills:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
        }

    }

    @Override
    public void selectedMultipleModelList(List<MultipleModel> multipleModelList, int selection) {

        switch (selection) {
            case R.id.ctv_language_speak_skills:
                String selected_value = "";
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvLanguageSpeak.setValue(selected_value);
                break;
        }
        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            ctvCookFood.setValue(textdata);
            AppPref.getInstance().setFoodCook(textdata.toString().trim());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
