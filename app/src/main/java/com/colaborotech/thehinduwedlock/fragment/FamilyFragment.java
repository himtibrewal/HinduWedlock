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
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.utility.FragmentButton;

/**
 * Created by him on 24-Jun-17.
 */

public class FamilyFragment extends Fragment implements View.OnClickListener {
    FragmentButton fragmentButton;
    CustomLayoutTitleValue ctvAboutFamily;
    CustomLayoutTitleValue ctvFamily;
    CustomLayoutTitleValue ctvParent;
    CustomLayoutTitleValue ctvSiblings;
    String family = "Family Status | Family Values | Family Type | Living with parents?";
    String parents = "Father is | Mother is | Family Income";
    String siblings = "Brother(s) | Sister(s)";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View familyView = inflater.inflate(R.layout.fragment_family, container, false);
        initviews(familyView);
        return familyView;
    }

    private void initviews(View view) {
        ctvAboutFamily = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_about_my_family_family_info_fragment);
        ctvFamily = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_family_family_info_fragment);
        ctvParent = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_parent_family_info_fragment);
        ctvSiblings = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_siblings_family_info_fragment);
        ctvAboutFamily.setOnClickListener(this);
        ctvFamily.setOnClickListener(this);
        ctvParent.setOnClickListener(this);
        ctvSiblings.setOnClickListener(this);
        ctvAboutFamily.setValue(AppPref.getInstance().getAboutFamily());
        ctvFamily.setValue(family);
        ctvParent.setValue(parents);
        ctvSiblings.setValue(siblings);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_about_my_family_family_info_fragment:
                fragmentButton.buttonClicked(ctvAboutFamily);
                break;
            case R.id.ctv_family_family_info_fragment:
                fragmentButton.buttonClicked(ctvFamily);
                break;
            case R.id.ctv_parent_family_info_fragment:
                fragmentButton.buttonClicked(ctvParent);
                break;
            case R.id.ctv_siblings_family_info_fragment:
                fragmentButton.buttonClicked(ctvSiblings);
                break;
        }

    }
}
