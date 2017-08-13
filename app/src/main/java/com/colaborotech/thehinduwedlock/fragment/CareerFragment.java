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

public class CareerFragment extends Fragment implements View.OnClickListener {
    FragmentButton fragmentButton;

    CustomLayoutTitleValue ctvAboutMyCareer;
    CustomLayoutTitleValue ctvCareerDetail;
    CustomLayoutTitleValue ctvFuturePlans;
    String careerdetail = "Organization Name | Occupation | Annual Income";
    String FuturePlans = "interested in setting abroad ? | Work after marriage?";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View careerView = inflater.inflate(R.layout.fragment_career, container, false);
        initViews(careerView);
        return careerView;
    }

    private void initViews(View view) {
        ctvAboutMyCareer = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_about_my_career_career_info_fragment);
        ctvCareerDetail = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_career_deatil_career_info_fragment);
        ctvFuturePlans = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_future_plans_career_info_fragment);
        ctvAboutMyCareer.setOnClickListener(this);
        ctvCareerDetail.setOnClickListener(this);
        ctvFuturePlans.setOnClickListener(this);
        ctvCareerDetail.setValue(careerdetail);
        ctvFuturePlans.setValue(FuturePlans);
        ctvAboutMyCareer.setValue(AppPref.getInstance().getAboutCareer());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_about_my_career_career_info_fragment:
                fragmentButton.buttonClicked(ctvAboutMyCareer);
                break;
            case R.id.ctv_career_deatil_career_info_fragment:
                fragmentButton.buttonClicked(ctvCareerDetail);
                break;
            case R.id.ctv_future_plans_career_info_fragment:
                fragmentButton.buttonClicked(ctvFuturePlans);
                break;
        }

    }
}
