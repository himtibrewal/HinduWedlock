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

public class LifeStyleFragment extends Fragment implements View.OnClickListener {

    FragmentButton fragmentButton;
    CustomLayoutTitleValue ctvHabits;
    CustomLayoutTitleValue ctvAssets;
    CustomLayoutTitleValue ctvSkills;
    CustomLayoutTitleValue ctvHobbies;
    CustomLayoutTitleValue ctvInterest;
    CustomLayoutTitleValue ctvFavourite;
    String habits = "Dietary Habits | Smoking Habits | Drinking Habits | Open to Pets?";
    String assets = "Own a House | Own a Car ?";
    String skill = "Languages | Speak | Food | Cook";
    String favourite = "Favourite Music | Favourite Book | Dress Style | Sports | Cuisine | Movies | Favourite Read | TV Shows | Vacation Destination";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lifestyleView = inflater.inflate(R.layout.fragment_lifestyle, container, false);
        initViews(lifestyleView);
        return lifestyleView;
    }

    private void initViews(View view) {
        ctvHabits = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_habits_lifestyle_info_fragment);
        ctvAssets = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_assert_lifestyle_info_fragment);
        ctvSkills = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_skills_lifestyle_info_fragment);
        ctvHobbies = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_hobbies_lifestyle_info_fragment);
        ctvInterest = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_interests_lifestyle_info_fragment);
        ctvFavourite = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_favourite_lifestyle_info_fragment);
        ctvHabits.setOnClickListener(this);
        ctvAssets.setOnClickListener(this);
        ctvSkills.setOnClickListener(this);
        ctvHobbies.setOnClickListener(this);
        ctvInterest.setOnClickListener(this);
        ctvFavourite.setOnClickListener(this);
        ctvHabits.setValue(habits);
        ctvAssets.setValue(assets);
        ctvSkills.setValue(skill);
        ctvFavourite.setValue(favourite);
        ctvHobbies.setValue(AppPref.getInstance().getHobbies());
        ctvInterest.setValue(AppPref.getInstance().getInterest());

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_habits_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvHabits);
                break;
            case R.id.ctv_assert_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvAssets);
                break;
            case R.id.ctv_skills_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvSkills);
                break;
            case R.id.ctv_hobbies_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvHobbies);
                break;
            case R.id.ctv_interests_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvInterest);
                break;
            case R.id.ctv_favourite_lifestyle_info_fragment:
                fragmentButton.buttonClicked(ctvFavourite);
                break;

        }


    }
}
