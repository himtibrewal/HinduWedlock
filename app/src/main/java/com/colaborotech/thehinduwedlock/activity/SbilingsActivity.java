package com.colaborotech.thehinduwedlock.activity;

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
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;

import java.util.List;

import static com.colaborotech.thehinduwedlock.TheHinduWedLockApp.familyBorther_sisterMarridModelList;

/**
 * Created by him on 08-Jul-17.
 */

public class SbilingsActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvBrothers;
    CustomLayoutTitleValue ctvSister;
    TextView tvCancel, tvHeader, tvSave;
    String brother, sister, brotherMarried, sisterMarried;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_siblings;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvBrothers = (CustomLayoutTitleValue) findViewById(R.id.ctv_brothers_siblings);
        ctvSister = (CustomLayoutTitleValue) findViewById(R.id.ctv_sisters_siblings);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Siblings");
        ctvBrothers.setOnClickListener(this);
        ctvSister.setOnClickListener(this);
    }


    @Override
    public void init(Bundle save) {
        ctvBrothers.setValue(AppPref.getInstance().getBrother() + " Brother of which married " + AppPref.getInstance().getMarriedBrother());
        ctvSister.setValue(AppPref.getInstance().getSister() + " sister of which married " + AppPref.getInstance().getMarriedSister());
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
        AppPref.getInstance().setBrother(brother);
        AppPref.getInstance().setSister(sister);
        AppPref.getInstance().setMarriedBrother(brotherMarried);
        AppPref.getInstance().setMarriedSister(sisterMarried);
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
            case R.id.ctv_brothers_siblings:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyBorther_sisterModelList, R.id.ctv_brothers_siblings, "Brother(s)");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_sisters_siblings:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyBorther_sisterModelList, R.id.ctv_sisters_siblings, "Sister(s)");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_brothers_siblings:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_sisters_siblings:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familyBorther_sisterMarridModelList.clear();
                switch (from) {
                    case R.id.ctv_brothers_siblings:
                        // ctvBrothers.setValue(((DataModel) Objects.get(position)).get_dataName());
                        brother = ((DataModel) Objects.get(position)).get_dataName();
                        if (((DataModel) Objects.get(position)).get_id() != 1) {
                            secondDialog("of which married", familyBorther_sisterMarridModelList, ((DataModel) Objects.get(position)).get_id(), ctvBrothers, 1);
                            drawerLayout.closeDrawer(Gravity.RIGHT);
                        } else {
                            drawerLayout.closeDrawer(Gravity.RIGHT);
                            ctvBrothers.setValue("None");
                        }

                        break;
                    case R.id.ctv_sisters_siblings:
                        sister = ((DataModel) Objects.get(position)).get_dataName();
                        if (((DataModel) Objects.get(position)).get_id() != 1) {
                            secondDialog("of which married", familyBorther_sisterMarridModelList, ((DataModel) Objects.get(position)).get_id(), ctvSister, 2);
                            drawerLayout.closeDrawer(Gravity.RIGHT);
                        } else {
                            drawerLayout.closeDrawer(Gravity.RIGHT);
                            ctvBrothers.setValue("None");
                        }
                        break;

                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void secondDialog(String header, List<DataModel> list, final int id, final CustomLayoutTitleValue ctv, final int check) {
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
        TextView textViewHeader = (TextView) dateOfBirthDialog.findViewById(R.id.tv_top_text_second_dilaog);
        textViewHeader.setText(header);
        RecyclerView recyclerView = (RecyclerView) dateOfBirthDialog.findViewById(R.id.dialog_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        for (int i = 0; i < id; i++) {
            list.add(new DataModel(TheHinduWedLockApp.familyBorther_sisterModelList.get(i).get_id(), TheHinduWedLockApp.familyBorther_sisterModelList.get(i).get_dataName()));
        }
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, final List objects, final int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check == 1) {
                            brotherMarried = ((DataModel) objects.get(position)).get_dataName();
                            ctv.setValue(brother + " brother of which married " + ((DataModel) objects.get(position)).get_dataName());
                        } else if (check == 2) {
                            sisterMarried = ((DataModel) objects.get(position)).get_dataName();
                            ctv.setValue(brother + " sister of which married " + ((DataModel) objects.get(position)).get_dataName());
                        }
                        familyBorther_sisterMarridModelList.clear();
                        dateOfBirthDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        dateOfBirthDialog.show();
    }

    Dialog dateOfBirthDialog;

}