package com.colaborotech.thehinduwedlock.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.utility.FragmentButton;

/**
 * Created by him on 24-Jun-17.
 */

public class BasicinfoFragment extends Fragment implements View.OnClickListener {
    CustomLayoutTitleValue ctvAboutMe;
    CustomLayoutTitleValue ctvBasicDetail;
    CustomLayoutTitleValue ctvEthnicity;
    CustomLayoutTitleValue ctvAppearance;
    CustomLayoutTitleValue ctvSpclCases;
    FragmentButton fragmentButton;
    String basicDetails = "Full Name | Height | Country Living in | Gender | Date of Birth | Marital Status | Profile Managed by";
    String ethnicity = "Religion | Mother Tongue | Caste | Subcaste | Gotra | Family based out of";
    String appearance = "Complexion | Body Type | Weight(kgs)";
    String spclCase = "Challenged | Thalassemia | HIV+?";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View basicinfoView = inflater.inflate(R.layout.fragment_basic_info, container, false);
        init(basicinfoView);
        return basicinfoView;

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }


    private void init(View v) {
        ctvAboutMe = (CustomLayoutTitleValue) v.findViewById(R.id.ctv_aboutme_basic_info_fragment);
        ctvBasicDetail = (CustomLayoutTitleValue) v.findViewById(R.id.ctv_basic_detail_info_fragment);
        ctvEthnicity = (CustomLayoutTitleValue) v.findViewById(R.id.ctv_ethnicity_basic_info_fragment);
        ctvAppearance = (CustomLayoutTitleValue) v.findViewById(R.id.ctv_appearance_basic_info_fragment);
        ctvSpclCases = (CustomLayoutTitleValue) v.findViewById(R.id.ctv_special_cases_basic_info_fragment);
        ctvAboutMe.setOnClickListener(this);
        ctvBasicDetail.setOnClickListener(this);
        ctvEthnicity.setOnClickListener(this);
        ctvAppearance.setOnClickListener(this);
        ctvSpclCases.setOnClickListener(this);
        ctvAboutMe.setValue(AppPref.getInstance().getAboutYourSelf(), 2, true);
        ctvBasicDetail.setValue(setBasicDetail(basicDetails));
        ctvEthnicity.setValue(ethnicity);
        ctvAppearance.setValue(appearance);
        ctvSpclCases.setValue(spclCase);

    }


    private String setBasicDetail(String basicDetails) {
        String[] data = basicDetails.split("|");
        StringBuilder stringBuilder = new StringBuilder();
        if (!AppPref.getInstance().getName().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[0] + "</font>");
        }
        if (!AppPref.getInstance().getHeight().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[1] + "</font>");
        }
        if (!AppPref.getInstance().getCountry().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[2] + "</font>");
        }
        if (!AppPref.getInstance().getGender().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[3] + "</font>");
        }
        if (!AppPref.getInstance().getDob().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[4] + "</font>");
        }
        if (!AppPref.getInstance().getMaritalStatus().equalsIgnoreCase("")) {
            stringBuilder.append("<font color=#FFAE0707>" + data[5] + "</font>");
        }
        return stringBuilder.toString();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_aboutme_basic_info_fragment:
                fragmentButton.buttonClicked(ctvAboutMe);
                break;
            case R.id.ctv_basic_detail_info_fragment:
                fragmentButton.buttonClicked(ctvBasicDetail);
                break;
            case R.id.ctv_ethnicity_basic_info_fragment:
                fragmentButton.buttonClicked(ctvEthnicity);
                break;
            case R.id.ctv_appearance_basic_info_fragment:
                fragmentButton.buttonClicked(ctvAppearance);
                break;
            case R.id.ctv_special_cases_basic_info_fragment:
                fragmentButton.buttonClicked(ctvSpclCases);
                break;
        }
    }


}
