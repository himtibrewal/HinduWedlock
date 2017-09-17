package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
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
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by him on 08-Jul-17.
 */

public class CollegeDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {
    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvHighestEducation;
    CustomLayoutTitleValue ctvPgColleg;
    CustomLayoutTitleValue ctvPgDegree;
    CustomLayoutTitleValue ctvUgCollege;
    CustomLayoutTitleValue ctvUgDegree;
    CustomLayoutTitleValue ctvSchoolName;
    LinearLayout llpgclg, llugclg, llpgdeg, llugdeg;
    TextView tvCancel, tvHeader, tvSave;
    Dialog inputDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_college_detail;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvHighestEducation = (CustomLayoutTitleValue) findViewById(R.id.ctv_highest_education_college_detail);
        ctvPgColleg = (CustomLayoutTitleValue) findViewById(R.id.ctv_pg_college_college_detail);
        ctvPgDegree = (CustomLayoutTitleValue) findViewById(R.id.ctv_pg_degree_college_detail);
        ctvUgCollege = (CustomLayoutTitleValue) findViewById(R.id.ctv_ug_college_college_detail);
        ctvUgDegree = (CustomLayoutTitleValue) findViewById(R.id.ctv_ug_degree_college_detail);
        ctvSchoolName = (CustomLayoutTitleValue) findViewById(R.id.ctv_school_name_college_detail);
        llpgclg = (LinearLayout) findViewById(R.id.ll_pg_college);
        llugclg = (LinearLayout) findViewById(R.id.ll_ug_college);
        llpgdeg = (LinearLayout) findViewById(R.id.ll_pg_degree);
        llugdeg = (LinearLayout) findViewById(R.id.ll_ug_degree);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("College Details");
        ctvHighestEducation.setOnClickListener(this);
        ctvPgColleg.setOnClickListener(this);
        ctvPgDegree.setOnClickListener(this);
        ctvUgCollege.setOnClickListener(this);
        ctvUgDegree.setOnClickListener(this);
        ctvSchoolName.setOnClickListener(this);
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
        ctvHighestEducation.setValue(AppPref.getInstance().getHighestEducation());
        ctvUgDegree.setValue(AppPref.getInstance().getUgCollege());
        ctvUgCollege.setValue(AppPref.getInstance().getUgCollege());
        ctvPgDegree.setValue(AppPref.getInstance().getPgDegree());
        ctvPgColleg.setValue(AppPref.getInstance().getPgCollge());
        ctvSchoolName.setValue(AppPref.getInstance().getSchoolName());

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
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.ctv_highest_education_college_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_highest_education_college_detail, "Highest Education");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_pg_college_college_detail:
                secondDialog("Enter Pg college", "enter pg college", "", ctvPgColleg);
                break;
            case R.id.ctv_pg_degree_college_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_pg_degree_college_detail, "PG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_ug_college_college_detail:
                secondDialog("Enter Ug college", "enter Ug college", "", ctvUgCollege);
                break;
            case R.id.ctv_ug_degree_college_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.educationModelList, R.id.ctv_ug_degree_college_detail, "UG Degree");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_school_name_college_detail:
                secondDialog("Enter School Name", "Enter School Name", "", ctvSchoolName);
                break;

        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_highest_education_college_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_pg_college_college_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_pg_degree_college_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_ug_college_college_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_ug_degree_college_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_highest_education_college_detail:
                        ctvHighestEducation.setValue(((DataModel) Objects.get(position)).get_dataName());
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
                    case R.id.ctv_pg_college_college_detail:
                        ctvPgColleg.setValue(((DataModel) Objects.get(position)).get_dataName());

                        break;
                    case R.id.ctv_pg_degree_college_detail:
                        ctvPgDegree.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_ug_college_college_detail:
                        ctvUgCollege.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_ug_degree_college_detail:
                        ctvUgDegree.setValue(((DataModel) Objects.get(position)).get_dataName());
                        break;


                }
                drawerLayout.closeDrawer(Gravity.RIGHT);

            }
        });
    }

    private void validation() {
        AppPref.getInstance().setHighestEducation(ctvHighestEducation.getValue());
        AppPref.getInstance().setUgDegree(ctvUgDegree.getValue());
        AppPref.getInstance().setUgCollege(ctvUgCollege.getValue());
        AppPref.getInstance().setPgDegree(ctvPgDegree.getValue());
        AppPref.getInstance().setPgCollge(ctvPgColleg.getValue());
        AppPref.getInstance().setSchoolName(ctvSchoolName.getValue());
        sendDataToServer();
    }

    private void sendDataToServer() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&highest_education=").append(AppPref.getInstance().getHighestEducation());
        stringBuilder.append("&pg_college=").append(AppPref.getInstance().getPgCollge());
        stringBuilder.append("&pg_degree=").append(AppPref.getInstance().getPgDegree());
        stringBuilder.append("&ug_degree=").append(AppPref.getInstance().getUgDegree());
        stringBuilder.append("&ug_college=").append(AppPref.getInstance().getUgCollege());
        stringBuilder.append("&school_name=").append(AppPref.getInstance().getSchoolName());
        String content = stringBuilder.toString();
        Log.e("content", content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_COLLEGE, 0, content, true, "please wait.", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("response", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        finish();
                    }

                } catch (JSONException e) {

                }
            }
        });
        getDataUsingWService.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

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
