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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.CustomLayoutTitleValue;

import java.util.List;

/**
 * Created by him on 15-Jun-17.
 */

public class CareerDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {
    DrawerLayout drawerLayout;
    ImageView ivBack;
    TextView tvHeader;
    CustomLayoutTitleValue ctvHighestEducation;
    CustomLayoutTitleValue ctvPgColleg;
    CustomLayoutTitleValue ctvPgDegree;
    CustomLayoutTitleValue ctvUgCollege;
    CustomLayoutTitleValue ctvUgDegree;
    CustomLayoutTitleValue ctvWorkArea;
    CustomLayoutTitleValue ctvIncome;
    LinearLayout llpgclg, llugclg, llpgdeg, llugdeg;
    TextView tvNext;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_career_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvNext = (TextView) findViewById(R.id.tv_next_career_detail);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvHighestEducation = (CustomLayoutTitleValue) findViewById(R.id.ctv_highest_education_career_detail);
        ctvPgColleg = (CustomLayoutTitleValue) findViewById(R.id.ctv_pg_college_career_detail);
        ctvPgDegree = (CustomLayoutTitleValue) findViewById(R.id.ctv_pg_degree_career_detail);
        ctvUgCollege = (CustomLayoutTitleValue) findViewById(R.id.ctv_ug_college_career_detail);
        ctvUgDegree = (CustomLayoutTitleValue) findViewById(R.id.ctv_ug_degree_career_detail);
        ctvWorkArea = (CustomLayoutTitleValue) findViewById(R.id.ctv_work_area_career_detail);
        ctvIncome = (CustomLayoutTitleValue) findViewById(R.id.ctv_income_career_detail);
        llpgclg = (LinearLayout) findViewById(R.id.ll_pg_college);
        llugclg = (LinearLayout) findViewById(R.id.ll_ug_college);
        llpgdeg = (LinearLayout) findViewById(R.id.ll_pg_degree);
        llugdeg = (LinearLayout) findViewById(R.id.ll_ug_degree);
        ctvHighestEducation.setOnClickListener(this);
        ctvPgColleg.setOnClickListener(this);
        ctvPgDegree.setOnClickListener(this);
        ctvUgCollege.setOnClickListener(this);
        ctvUgDegree.setOnClickListener(this);
        ctvWorkArea.setOnClickListener(this);
        ctvIncome.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        hideall();
    }

    private void hideall() {
        ctvUgCollege.setVisibility(View.GONE);
        ctvUgDegree.setVisibility(View.GONE);
        ctvPgColleg.setVisibility(View.GONE);
        ctvPgDegree.setVisibility(View.GONE);
        llpgclg.setVisibility(View.GONE);
        llugclg.setVisibility(View.GONE);
        llpgdeg.setVisibility(View.GONE);
        llugdeg.setVisibility(View.GONE);
    }


    @Override
    public void init(Bundle save) {
        tvHeader.setText("Career Detail");
        if (!AppPref.getInstance().getHighestEducation().equalsIgnoreCase("")) {
            ctvHighestEducation.setValue(AppPref.getInstance().getHighestEducation());
        }
        if (!AppPref.getInstance().getUgCollege().equalsIgnoreCase("")) {
            ctvUgCollege.setValue(AppPref.getInstance().getUgCollege());
        }
        if (!AppPref.getInstance().getUgDegree().equalsIgnoreCase("")) {
            ctvUgDegree.setValue(AppPref.getInstance().getUgDegree());
        }
        if (!AppPref.getInstance().getPgCollge().equalsIgnoreCase("")) {
            ctvPgColleg.setValue(AppPref.getInstance().getPgCollge());
            ctvPgColleg.setVisibility(View.VISIBLE);
        }
        if (!AppPref.getInstance().getPgDegree().equalsIgnoreCase("")) {
            ctvPgDegree.setValue(AppPref.getInstance().getPgDegree());
            ctvPgDegree.setVisibility(View.VISIBLE);
        }
        if (!AppPref.getInstance().getWorkArea().equalsIgnoreCase("")) {
            ctvWorkArea.setValue(AppPref.getInstance().getWorkArea());
        }
        if (!AppPref.getInstance().getIncome().equalsIgnoreCase("")) {
            ctvIncome.setValue(AppPref.getInstance().getIncome());
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
            case R.id.ctv_highest_education_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_highest_education_career_detail, "Highest Education");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_pg_college_career_detail:
                secondDialog("Enter Pg college", "enter pg college", "", ctvPgColleg);
//                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_pg_college_career_detail, "Highest Education");
//                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_pg_degree_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_pg_degree_career_detail, "PG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_ug_college_career_detail:
                secondDialog("Enter Ug college", "enter Ug college", "", ctvUgCollege);
//                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_ug_college_career_detail, "Highest Education");
//                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_ug_degree_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_ug_degree_career_detail, "UG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_work_area_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_work_area_career_detail, "Work Area");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_income_career_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.incomeModelList, R.id.ctv_income_career_detail, "Income");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_next_career_detail:
                validation();

                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_highest_education_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_pg_college_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_pg_degree_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_ug_college_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_ug_degree_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_work_area_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_income_career_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_highest_education_career_detail:
                        ctvHighestEducation.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setHighestEduId(((DataModel) Objects.get(position)).get_id());
                        if (position < 5) {
                            ctvUgCollege.setVisibility(View.VISIBLE);
                            ctvUgDegree.setVisibility(View.VISIBLE);
                            ctvPgColleg.setVisibility(View.GONE);
                            ctvPgDegree.setVisibility(View.GONE);
                            llugclg.setVisibility(View.VISIBLE);
                            llugdeg.setVisibility(View.VISIBLE);
                            llpgclg.setVisibility(View.GONE);
                            llpgdeg.setVisibility(View.GONE);
                        } else {
                            ctvPgColleg.setVisibility(View.VISIBLE);
                            ctvPgDegree.setVisibility(View.VISIBLE);
                            ctvUgCollege.setVisibility(View.VISIBLE);
                            ctvUgDegree.setVisibility(View.VISIBLE);
                            llpgclg.setVisibility(View.VISIBLE);
                            llugclg.setVisibility(View.VISIBLE);
                            llpgdeg.setVisibility(View.VISIBLE);
                            llugdeg.setVisibility(View.VISIBLE);
                        }
                        break;
                    case R.id.ctv_pg_college_career_detail:
                        ctvPgColleg.setValue(((DataModel) Objects.get(position)).get_dataName());

                        break;
                    case R.id.ctv_pg_degree_career_detail:
                        ctvPgDegree.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_ug_college_career_detail:
                        ctvUgCollege.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_ug_degree_career_detail:
                        ctvUgDegree.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_work_area_career_detail:
                        ctvWorkArea.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setOccupdationId(((DataModel) Objects.get(position)).get_id());
                        break;
                    case R.id.ctv_income_career_detail:
                        ctvIncome.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setIncomeId(((DataModel) Objects.get(position)).get_id());
                        break;

                }
                drawerLayout.closeDrawer(Gravity.RIGHT);

            }
        });
    }


    private void validation() {
        String highestEducation = ctvHighestEducation.getValue().toString();
        String pgCollege = ctvPgColleg.getValue().toString();
        String pgDegree = ctvPgDegree.getValue().toString();
        String ugCollge = ctvUgCollege.getValue().toString();
        String ugDegree = ctvUgDegree.getValue().toString();
        String workarea = ctvWorkArea.getValue().toString();
        String income = ctvIncome.getValue().toString();
        if (highestEducation.equalsIgnoreCase("") | highestEducation.equalsIgnoreCase("not filled")) {
            toastMessage("please select highest education");
            return;
        } else if (workarea.equalsIgnoreCase("") | workarea.equalsIgnoreCase("not filled")) {
            toastMessage("please select work area");
            return;
        } else if (income.equalsIgnoreCase("") | income.equalsIgnoreCase("not filled")) {
            toastMessage("please select income");
            return;
        } else {
            AppPref.getInstance().setHighestEducation(highestEducation);
            AppPref.getInstance().setWorkArea(workarea);
            AppPref.getInstance().setIncome(income);
            AppPref.getInstance().setUgCollege(ugCollge);
            AppPref.getInstance().setPgCollge(pgCollege);
            AppPref.getInstance().setUgDegree(ugDegree);
            AppPref.getInstance().setPgDegree(pgDegree);
            sendToThisActivity(SocialDetailActivity.class);
        }


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
