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
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import java.util.List;

/**
 * Created by him on 08-Jul-17.
 */

public class EthinicityActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvReligion;
    CustomLayoutTitleValue ctvMotherTongue;
    CustomLayoutTitleValue ctvCaste;
    CustomLayoutTitleValue ctvSubCaste;
    CustomLayoutTitleValue ctvGotra;
    CustomLayoutTitleValue ctvFamilyBased;
    TextView tvCancel, tvHeader, tvSave;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_ethnicity;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvReligion = (CustomLayoutTitleValue) findViewById(R.id.layout_religion_ethnicity);
        ctvMotherTongue = (CustomLayoutTitleValue) findViewById(R.id.layout_mother_tongue_ethnicity);
        ctvCaste = (CustomLayoutTitleValue) findViewById(R.id.layout_caste_ethnicity);
        ctvSubCaste = (CustomLayoutTitleValue) findViewById(R.id.layout_subcaste_ethnicity);
        ctvGotra = (CustomLayoutTitleValue) findViewById(R.id.layout_gotra_ethnicity);
        ctvFamilyBased = (CustomLayoutTitleValue) findViewById(R.id.layout_family_based_ethnicity);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Ethnicity");

        ctvReligion.setOnClickListener(this);
        ctvMotherTongue.setOnClickListener(this);
        ctvCaste.setOnClickListener(this);
        ctvSubCaste.setOnClickListener(this);
        ctvGotra.setOnClickListener(this);
        ctvFamilyBased.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvReligion.setValue(AppPref.getInstance().getReligion());
        ctvMotherTongue.setValue(AppPref.getInstance().getMotherTongue());
        ctvCaste.setValue(AppPref.getInstance().getCaste());
        ctvSubCaste.setValue(AppPref.getInstance().getSubCaste());
        ctvGotra.setValue(AppPref.getInstance().getGotra());
        ctvFamilyBased.setValue(AppPref.getInstance().getFamilyBased());

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
        AppPref.getInstance().setReligion(ctvReligion.getValue());
        AppPref.getInstance().setMotherTongue(ctvMotherTongue.getValue());
        AppPref.getInstance().setCaste(ctvCaste.getValue());
        AppPref.getInstance().setSubCaste(ctvSubCaste.getValue());
        AppPref.getInstance().setGotra(ctvGotra.getValue());
        AppPref.getInstance().setFamilyBased(ctvFamilyBased.getValue());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&religion=").append(AppPref.getInstance().getReligion());
        stringBuilder.append("&mother_tongue=").append(AppPref.getInstance().getMotherTongue());
        stringBuilder.append("&caste=").append(AppPref.getInstance().getCaste());
        stringBuilder.append("&sub_caste=").append(AppPref.getInstance().getSubCaste());
        stringBuilder.append("&gotra=").append(AppPref.getInstance().getGotra());
        stringBuilder.append("&family_based_on=").append(AppPref.getInstance().getFamilyBased());
        String content = stringBuilder.toString();
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_ETHNICITY, 0, content, true, "please wait..", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("response", responseData);
                finish();
            }
        });
        getDataUsingWService.execute();
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
            case R.id.layout_religion_ethnicity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.religionModelList, R.id.layout_religion_ethnicity, "Religion");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_mother_tongue_ethnicity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.languageModelList, R.id.layout_mother_tongue_ethnicity, "Mother Tongue");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_caste_ethnicity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.casteModelList, R.id.layout_caste_ethnicity, "Caste");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.layout_subcaste_ethnicity:
                secondDialog("Enter SubCaste", "Enter SubCaste", "", ctvSubCaste);
                break;
            case R.id.layout_gotra_ethnicity:
                secondDialog("Enter Gotra", "Enter Gotra", "", ctvGotra);
                break;
            case R.id.layout_family_based_ethnicity:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.countryModelList, R.id.layout_family_based_ethnicity, "Family Based out of");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.layout_religion_ethnicity:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_mother_tongue_ethnicity:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_caste_ethnicity:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.layout_family_based_ethnicity:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.layout_religion_ethnicity:
                        ctvReligion.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.layout_mother_tongue_ethnicity:
                        ctvMotherTongue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.layout_caste_ethnicity:
                        ctvCaste.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.layout_family_based_ethnicity:
                        ctvFamilyBased.setValue(((DataModel) Objects.get(position)).get_dataName());
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
