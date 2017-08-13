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

public class EducationFragment extends Fragment implements View.OnClickListener {
    CustomLayoutTitleValue ctvAboutEducation;
    CustomLayoutTitleValue ctvCollegeDetail;
    FragmentButton fragmentButton;
    String collegeDetail = "Highest Education | UG Degree | UG College | School Name";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View educationView = inflater.inflate(R.layout.fragment_education, container, false);
        inintview(educationView);
        return educationView;
    }

    private void inintview(View view) {
        ctvAboutEducation = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_about_edu_education_info_fragment);
        ctvCollegeDetail = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_college_detail_education_info_fragment);
        ctvAboutEducation.setOnClickListener(this);
        ctvCollegeDetail.setOnClickListener(this);
        ctvCollegeDetail.setValue(collegeDetail);
        ctvAboutEducation.setValue(AppPref.getInstance().getAboutEdu());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_about_edu_education_info_fragment:
                fragmentButton.buttonClicked(ctvAboutEducation);
                break;
            case R.id.ctv_college_detail_education_info_fragment:
                fragmentButton.buttonClicked(ctvCollegeDetail);
                break;
        }


    }
}
