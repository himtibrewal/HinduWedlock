package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
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
 * Created by him on 19-Jun-17.
 */

public class FamilyDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView, GetWebServiceData {
    private ImageView ivBack;
    private TextView tvHeader;
    private DrawerLayout drawerLayout;
    private CustomLayoutTitleValue ctvFamilyStatus;
    private CustomLayoutTitleValue ctvFamilyValues;
    private CustomLayoutTitleValue ctvFamilyType;
    private CustomLayoutTitleValue ctvFamilyIncome;
    private CustomLayoutTitleValue ctvFatherOccupation;
    private CustomLayoutTitleValue ctvMotherOccupation;
    private CustomLayoutTitleValue ctvBrothers;
    private CustomLayoutTitleValue ctvSisters;
    private CustomLayoutTitleValue ctvFamilyBasedOutOf;
    private CustomLayoutTitleValue ctvGotra;
    private TextView tvNext;
    private Dialog secondDialog;
    private Dialog inputDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_family_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setVisibility(View.GONE);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvFamilyStatus = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_status_family_detail);
        ctvFamilyValues = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_values_family_detail);
        ctvFamilyType = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_type_family_detail);
        ctvFamilyIncome = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_income_family_detail);
        ctvFatherOccupation = (CustomLayoutTitleValue) findViewById(R.id.ctv_father_occupations_family_detail);
        ctvMotherOccupation = (CustomLayoutTitleValue) findViewById(R.id.ctv_mother_occupations_family_detail);
        ctvBrothers = (CustomLayoutTitleValue) findViewById(R.id.ctv_Brothers_family_detail);
        ctvSisters = (CustomLayoutTitleValue) findViewById(R.id.ctv_sisters_family_detail);
        ctvFamilyBasedOutOf = (CustomLayoutTitleValue) findViewById(R.id.ctv_family_based_out_of_family_detail);
        ctvGotra = (CustomLayoutTitleValue) findViewById(R.id.ctv_gotra_family_detail);
        ctvFamilyStatus.setOnClickListener(this);
        ctvFamilyValues.setOnClickListener(this);
        ctvFamilyType.setOnClickListener(this);
        ctvFamilyIncome.setOnClickListener(this);
        ctvFatherOccupation.setOnClickListener(this);
        ctvMotherOccupation.setOnClickListener(this);
        ctvBrothers.setOnClickListener(this);
        //ctvBrothersMarried.setOnClickListener(this);
        ctvSisters.setOnClickListener(this);
        //ctvSistersMarried.setOnClickListener(this);
        ctvFamilyBasedOutOf.setOnClickListener(this);
        ctvGotra.setOnClickListener(this);
        tvNext = (TextView) findViewById(R.id.tv_next_family_detail);
        tvNext.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Family Details");
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
            case R.id.ctv_family_status_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familystatusModelList, R.id.ctv_family_status_family_detail, "Family Status");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_values_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyValuesModelList, R.id.ctv_family_values_family_detail, "Family Values");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_type_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyTypeModelList, R.id.ctv_family_type_family_detail, "Family Type");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_income_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.incomeModelList, R.id.ctv_family_income_family_detail, "Family Income");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_father_occupations_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_father_occupations_family_detail, "Father\'s Occupations");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_mother_occupations_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.occupationModelList, R.id.ctv_mother_occupations_family_detail, "Mother\'s Occupations");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_Brothers_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyBorther_sisterModelList, R.id.ctv_Brothers_family_detail, "Brother(s)");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_sisters_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.familyBorther_sisterModelList, R.id.ctv_sisters_family_detail, "Sister(s)");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_family_based_out_of_family_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.countryModelList, R.id.ctv_family_based_out_of_family_detail, "Family based out Of");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_gotra_family_detail:
                secondDialog("Enter Gotra", "Enter Gotra", "", ctvGotra);
                break;
            case R.id.tv_next_family_detail:
                validation();
                // sendToThisActivity(WriteAboutActivity.class, new String[]{"from;FamilyDetailActivity"});
                break;
        }
    }

    private void validation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&family_status=").append(AppPref.getInstance().getFamilyStatus());
        stringBuilder.append("&family_values=").append(AppPref.getInstance().getFamilyValues());
        stringBuilder.append("&family_type=").append(AppPref.getInstance().getFamilyType());
        stringBuilder.append("&family_income=").append(AppPref.getInstance().getFamilyIncome());
        stringBuilder.append("&father_occupation=").append(AppPref.getInstance().getFatherOccupation());
        stringBuilder.append("&mother_occupation=").append(AppPref.getInstance().getMotherOccupation());
        stringBuilder.append("&brother=").append(AppPref.getInstance().getBrother());
        stringBuilder.append("&married_brother=").append(AppPref.getInstance().getMarriedBrother());
        stringBuilder.append("&sister=").append(AppPref.getInstance().getSister());
        stringBuilder.append("&sister_married=").append(AppPref.getInstance().getMarriedSister());
        stringBuilder.append("&family_based_on=").append(AppPref.getInstance().getFamilyBased());
        stringBuilder.append("&gotra=").append(AppPref.getInstance().getGotra());
        String content = stringBuilder.toString();
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_FAMILY_DETAIL, 0, content, true, "Please wait.", this);
        getDataUsingWService.execute();
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        try {
            //{"response_code":"200","message":"data added successfully"}
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                AppPref.getInstance().setFamilyFilled(true);
                sendToThisActivity(WriteAboutActivity.class, new String[]{"from;FamilyDetailActivity"});
                finish();
            }
        } catch (JSONException e) {

        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_family_status_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_values_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_type_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_income_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_father_occupations_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_mother_occupations_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_Brothers_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_sisters_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_family_based_out_of_family_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;

        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_family_status_family_detail:
                        ctvFamilyStatus.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setFamilyStatus(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_family_values_family_detail:
                        ctvFamilyValues.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setFamilyValues(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_family_type_family_detail:
                        ctvFamilyType.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setFamilyType(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_family_income_family_detail:
                        ctvFamilyIncome.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setFamilyIncome(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_father_occupations_family_detail:
                        ctvFatherOccupation.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setFatherOccupation(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_mother_occupations_family_detail:
                        ctvMotherOccupation.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setMotherOccupation(((DataModel) Objects.get(position)).get_dataName());
                        break;
                    case R.id.ctv_Brothers_family_detail:
                        ctvBrothers.setValue(((DataModel) Objects.get(position)).get_dataName());
                        secondDialog(TheHinduWedLockApp.familyBorther_sisterModelList, ((DataModel) Objects.get(position)).get_dataName(), ctvBrothers, R.id.ctv_Brothers_family_detail);
                        break;
                    case R.id.ctv_sisters_family_detail:
                        ctvSisters.setValue(((DataModel) Objects.get(position)).get_dataName());
                        secondDialog(TheHinduWedLockApp.familyBorther_sisterModelList, ((DataModel) Objects.get(position)).get_dataName(), ctvSisters, R.id.ctv_sisters_family_detail);
                        break;
                    case R.id.ctv_family_based_out_of_family_detail:
                        ctvFamilyBasedOutOf.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setFamilyBased(((DataModel) Objects.get(position)).get_dataName());
                        break;
                }
            }
        });
    }

    private void secondDialog(List<DataModel> list, final String data, final CustomLayoutTitleValue ctv, final int id) {
        secondDialog = new Dialog(this, R.style.DialogSlideAnim);
        secondDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        secondDialog.setContentView(R.layout.dialog_second_drawer);
        secondDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = secondDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        secondDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) secondDialog.findViewById(R.id.dialog_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, final List objects, final int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // String peeviousStrng = ctv.getValue().toString();
                        if (id == R.id.ctv_Brothers_family_detail) {
                            AppPref.getInstance().setBrother(data);
                            AppPref.getInstance().setMarriedBrother(((DataModel) objects.get(position)).get_dataName());
                        } else if (id == R.id.ctv_sisters_family_detail) {
                            AppPref.getInstance().setSister(data);
                            AppPref.getInstance().setMarriedSister(((DataModel) objects.get(position)).get_dataName());
                        }
                        ctv.setValue(data + " - " + ((DataModel) objects.get(position)).get_dataName());
                        secondDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);

                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        secondDialog.show();
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
