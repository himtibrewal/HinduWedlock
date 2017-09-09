package com.colaborotech.thehinduwedlock.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;

/**
 * Created by him on 24-Jun-17.
 */

public class ContactFragment extends Fragment implements View.OnClickListener {


    LinearLayout llEmailId;
    TextView tvEmailId;
    TextView tvEmailVerify;
    LinearLayout llALtEmail;
    TextView tvAltEmailId;
    TextView tvAltEmailVerify;
    LinearLayout llMobileNumber;
    TextView tvMobileNo;
    TextView tvMobileNoSetting;
    LinearLayout llAltMobileNo;
    TextView tvAltMobileNumber;
    TextView tvSettingAltMobileNo;
    LinearLayout llLandLineNo;
    TextView tvLandLineNo;
    TextView tvSettingsLandLine;
    CustomLayoutTitleValue ctvSuitableTimeToCall;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contactView = inflater.inflate(R.layout.fragment_contact, container, false);
        initViews(contactView);
        return contactView;
    }

    private void initViews(View view) {
        llEmailId = (LinearLayout) view.findViewById(R.id.ll_email_id_contact);
        tvEmailId = (TextView) view.findViewById(R.id.tv_email_id_contact);
        tvEmailVerify = (TextView) view.findViewById(R.id.email_id_verify_conatct);
        llALtEmail = (LinearLayout) view.findViewById(R.id.ll_alt_email_id_contact);
        tvAltEmailId = (TextView) view.findViewById(R.id.tv_alt_email_contact);
        tvAltEmailVerify = (TextView) view.findViewById(R.id.tv_verify_alt_email_contact);
        llMobileNumber = (LinearLayout) view.findViewById(R.id.ll_mobile_no_contact);
        tvMobileNo = (TextView) view.findViewById(R.id.tv_Mobile_no_conatct);
        tvMobileNoSetting = (TextView) view.findViewById(R.id.tv_setting_mobile_no_contact);
        llAltMobileNo = (LinearLayout) view.findViewById(R.id.ll_alt_mobile_no_contact);
        tvAltMobileNumber = (TextView) view.findViewById(R.id.tv_alt_mobile_no_contact);
        tvSettingAltMobileNo = (TextView) view.findViewById(R.id.tv_settings_alt_mobile_no_contact);
        llLandLineNo = (LinearLayout) view.findViewById(R.id.ll_landline_no_contact);
        tvLandLineNo = (TextView) view.findViewById(R.id.tv_landline_no_contact);
        tvSettingsLandLine = (TextView) view.findViewById(R.id.tv_settings_landline_no_contact);
        ctvSuitableTimeToCall = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_suitable_time_to_call_contact);
        llEmailId.setOnClickListener(this);
        llALtEmail.setOnClickListener(this);
        llMobileNumber.setOnClickListener(this);
        llAltMobileNo.setOnClickListener(this);
        llLandLineNo.setOnClickListener(this);
        ctvSuitableTimeToCall.setOnClickListener(this);
        tvEmailVerify.setOnClickListener(this);
        tvAltEmailVerify.setOnClickListener(this);
        tvMobileNoSetting.setOnClickListener(this);
        tvSettingAltMobileNo.setOnClickListener(this);
        tvSettingsLandLine.setOnClickListener(this);
        tvEmailId.setText(AppPref.getInstance().getEmailId());
        tvAltEmailId.setText(AppPref.getInstance().getAltEmail());
        tvMobileNo.setText(AppPref.getInstance().getMobile());
        tvAltMobileNumber.setText(AppPref.getInstance().getAltMobile());
        tvLandLineNo.setText(AppPref.getInstance().getPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_email_id_contact:
                secondDialog("Email Id", "Email Id", "", tvEmailId, R.id.ll_email_id_contact);
                break;
            case R.id.ll_alt_email_id_contact:
                secondDialog("Alt. Email Id", "Alt. Email Id", "", tvAltEmailId, R.id.ll_alt_email_id_contact);
                break;
            case R.id.ll_mobile_no_contact:
                secondDialog("Mobile No.", "Mobile No", "", tvMobileNo, R.id.ll_mobile_no_contact);
                break;
            case R.id.ll_alt_mobile_no_contact:
                secondDialog("Alt. Mobile No", "Alt. Mobile No", "", tvAltMobileNumber, R.id.ll_alt_mobile_no_contact);
                break;
            case R.id.ll_landline_no_contact:
                secondDialog("Landline No", "Landline No", "", tvLandLineNo, R.id.ll_landline_no_contact);
                break;
            case R.id.email_id_verify_conatct:
                break;
        }

    }

    Dialog inputDialog;

    private void secondDialog(String title, String hint, String data, final TextView tv, final int id) {
        inputDialog = new Dialog(getActivity(), R.style.DialogSlideAnim2);
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
                    tv.setText("Not filled");
                } else {
                    switch (id) {
                        case R.id.ll_email_id_contact:
                            tv.setText(etValue.getText().toString());
                            AppPref.getInstance().setEmailId(etValue.getText().toString());
                            break;
                        case R.id.ll_alt_email_id_contact:
                            tv.setText(etValue.getText().toString());
                            AppPref.getInstance().setAltEmail(etValue.getText().toString());
                            break;
                        case R.id.ll_mobile_no_contact:
                            tv.setText(etValue.getText().toString());
                            AppPref.getInstance().setMobile(etValue.getText().toString());
                            break;
                        case R.id.ll_alt_mobile_no_contact:
                            tv.setText(etValue.getText().toString());
                            AppPref.getInstance().setAltMobile(etValue.getText().toString());
                            break;
                        case R.id.ll_landline_no_contact:
                            tv.setText(etValue.getText().toString());
                            AppPref.getInstance().setPhone(etValue.getText().toString());
                            break;
                    }
                }
                inputDialog.dismiss();
            }
        });


        inputDialog.show();
    }
}
