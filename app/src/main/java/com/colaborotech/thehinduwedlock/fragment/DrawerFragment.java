package com.colaborotech.thehinduwedlock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.activity.AboutUsActivity;
import com.colaborotech.thehinduwedlock.activity.AcceptedMembersActivity;
import com.colaborotech.thehinduwedlock.activity.BlockedActivity;
import com.colaborotech.thehinduwedlock.activity.ContactUsActivity;
import com.colaborotech.thehinduwedlock.activity.DailyRecommendationActivity;
import com.colaborotech.thehinduwedlock.activity.DeclinedActivity;
import com.colaborotech.thehinduwedlock.activity.DesiredPartnerMacthActivity;
import com.colaborotech.thehinduwedlock.activity.DrawerActivity;
import com.colaborotech.thehinduwedlock.activity.HelpActivity;
import com.colaborotech.thehinduwedlock.activity.InterestsActivity;
import com.colaborotech.thehinduwedlock.activity.JustJoinedActivity;
import com.colaborotech.thehinduwedlock.activity.LookingForMeActivity;
import com.colaborotech.thehinduwedlock.activity.MessageActivity;
import com.colaborotech.thehinduwedlock.activity.PhoneBookActivity;
import com.colaborotech.thehinduwedlock.activity.ProfileEditActivity;
import com.colaborotech.thehinduwedlock.activity.ProfileVerifiedByVisit;
import com.colaborotech.thehinduwedlock.activity.ProfileVisitorsActivity;
import com.colaborotech.thehinduwedlock.activity.SavedSearchActivity;
import com.colaborotech.thehinduwedlock.activity.SearchActivity;
import com.colaborotech.thehinduwedlock.activity.SearchByIdActivity;
import com.colaborotech.thehinduwedlock.activity.SettingsActivity;
import com.colaborotech.thehinduwedlock.activity.ShortListProfileActivity;
import com.colaborotech.thehinduwedlock.activity.WhoViewMyContactActivity;


/**
 * Created by him on 19-Jun-17.
 */

public class DrawerFragment extends Fragment implements View.OnClickListener {
    RelativeLayout rlInterest;
    RelativeLayout rlAcceptance;    RelativeLayout rlJustJoined;
    TextView tvSearch;
    TextView tvHome;
    TextView tvSearchByid;
    TextView tvSavedSearch;
    TextView tvJustJoined;
    TextView tvMatchesVerifiedByVisit;
    TextView tvDesiredPartner;
    TextView tvDailyRecomendation;
    TextView tvKundliMatch;
    TextView tvMemberLookingForMe;
    TextView tvProfileVisitors;
    TextView tvInterestReceived;
    TextView tvFilteredInterest;
    TextView tvAllAcceptance;
    TextView tvPhoneBook;
    TextView tvContactViewed;
    TextView tvShortListProfile;
    TextView tvMessage;
    TextView tvInterestSent;
    TextView tvBlocked;
    TextView tvDeclined;
    TextView tvHelp;
    TextView tvSettings;
    TextView tvContactUs;
    TextView tvAboutUs;
    TextView tvRateTheApp;
    RelativeLayout rlEditProfile;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View drawerView = inflater.inflate(R.layout.fragment_drawer, container, false);
        rlInterest = (RelativeLayout) drawerView.findViewById(R.id.rl_interest_received);
        rlAcceptance = (RelativeLayout) drawerView.findViewById(R.id.rl_accceptance_drawer);
        rlJustJoined = (RelativeLayout) drawerView.findViewById(R.id.rl_just_joined_drawer);
        tvSearch = (TextView) drawerView.findViewById(R.id.drawer_search);
        tvHome = (TextView) drawerView.findViewById(R.id.drawer_home);
        tvSearchByid = (TextView) drawerView.findViewById(R.id.drawer_search_by_id);
        tvSavedSearch = (TextView) drawerView.findViewById(R.id.drawer_saved_searches);
        tvJustJoined = (TextView) drawerView.findViewById(R.id.drawer_just_joined_mathces);
        tvMatchesVerifiedByVisit = (TextView) drawerView.findViewById(R.id.drawer_matches_verified_visit);
        tvDesiredPartner = (TextView) drawerView.findViewById(R.id.drawer_desired_partner_match);
        tvDailyRecomendation = (TextView) drawerView.findViewById(R.id.drawer_daily_recommendation);
        tvKundliMatch = (TextView) drawerView.findViewById(R.id.drawer_kundli_match);
        tvMemberLookingForMe = (TextView) drawerView.findViewById(R.id.drawer_member_looking_for_me);
        tvProfileVisitors = (TextView) drawerView.findViewById(R.id.drawer_profile_visitors);
        tvInterestReceived = (TextView) drawerView.findViewById(R.id.drawer_interest_received);
        tvFilteredInterest = (TextView) drawerView.findViewById(R.id.drawer_filter_interest);
        tvAllAcceptance = (TextView) drawerView.findViewById(R.id.drawer_all_acceptance);
        tvPhoneBook = (TextView) drawerView.findViewById(R.id.drawer_phonebook);
        tvContactViewed = (TextView) drawerView.findViewById(R.id.drawer_who_viewed_my_contact);
        tvShortListProfile = (TextView) drawerView.findViewById(R.id.drawer_shortlist_profile);
        tvMessage = (TextView) drawerView.findViewById(R.id.drawer_messgae);
        tvInterestSent = (TextView) drawerView.findViewById(R.id.drawer_interest_sent);
        tvBlocked = (TextView) drawerView.findViewById(R.id.drawer_Blocked);
        tvDeclined = (TextView) drawerView.findViewById(R.id.drawer_declined_member);
        tvHelp = (TextView) drawerView.findViewById(R.id.drawer_help);
        tvSettings = (TextView) drawerView.findViewById(R.id.drawer_setting);
        tvContactUs = (TextView) drawerView.findViewById(R.id.drawer_contact_us);
        tvAboutUs = (TextView) drawerView.findViewById(R.id.drawer_about_us);
        tvRateTheApp = (TextView) drawerView.findViewById(R.id.drawer_rate_the_app);
        rlEditProfile = (RelativeLayout) drawerView.findViewById(R.id.drawer_edit_profile);
        rlInterest.setOnClickListener(this);
        rlAcceptance.setOnClickListener(this);
        rlJustJoined.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvSearchByid.setOnClickListener(this);
        tvSavedSearch.setOnClickListener(this);
        tvJustJoined.setOnClickListener(this);
        tvMatchesVerifiedByVisit.setOnClickListener(this);
        tvDesiredPartner.setOnClickListener(this);
        tvDailyRecomendation.setOnClickListener(this);
        tvKundliMatch.setOnClickListener(this);
        tvMemberLookingForMe.setOnClickListener(this);
        tvProfileVisitors.setOnClickListener(this);
        tvInterestReceived.setOnClickListener(this);
        tvFilteredInterest.setOnClickListener(this);
        tvAllAcceptance.setOnClickListener(this);
        tvPhoneBook.setOnClickListener(this);
        tvContactViewed.setOnClickListener(this);
        tvShortListProfile.setOnClickListener(this);
        tvMessage.setOnClickListener(this);
        tvInterestSent.setOnClickListener(this);
        tvBlocked.setOnClickListener(this);
        tvDeclined.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvRateTheApp.setOnClickListener(this);
        rlEditProfile.setOnClickListener(this);
        return drawerView;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_interest_received:
                Intent interestintent = new Intent(getActivity(), InterestsActivity.class);
                startActivity(interestintent);
                getActivity().onBackPressed();
                break;
            case R.id.rl_accceptance_drawer:
                Intent acceptanceintent = new Intent(getActivity(), AcceptedMembersActivity.class);
                startActivity(acceptanceintent);
                getActivity().onBackPressed();
                break;
            case R.id.rl_just_joined_drawer:
                intent = new Intent(getActivity(), JustJoinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_edit_profile:
                Intent intentEditProfile = new Intent(getActivity(), ProfileEditActivity.class);
                intentEditProfile.putExtra("position", 0);
                startActivity(intentEditProfile);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_home:
                Intent intentHome = new Intent(getActivity(), DrawerActivity.class);
                startActivity(intentHome);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_search:
                Intent intentSearch = new Intent(getActivity(), SearchActivity.class);
                startActivity(intentSearch);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_search_by_id:
                Intent intentSearchById = new Intent(getActivity(), SearchByIdActivity.class);
                startActivity(intentSearchById);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_saved_searches:
                intent = new Intent(getActivity(), SavedSearchActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_just_joined_mathces:
                intent = new Intent(getActivity(), JustJoinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_matches_verified_visit:
                intent = new Intent(getActivity(), ProfileVerifiedByVisit.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_desired_partner_match:
                intent = new Intent(getActivity(), DesiredPartnerMacthActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_daily_recommendation:
                intent = new Intent(getActivity(), DailyRecommendationActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_kundli_match:
                intent = new Intent(getActivity(), JustJoinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_member_looking_for_me:
                intent = new Intent(getActivity(), LookingForMeActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_profile_visitors:
                intent = new Intent(getActivity(), ProfileVisitorsActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_interest_received:
                intent = new Intent(getActivity(), InterestsActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_filter_interest:
                intent = new Intent(getActivity(), JustJoinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_all_acceptance:
                intent = new Intent(getActivity(), AcceptedMembersActivity.class);
                intent.putExtra("select", "tab2");
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_phonebook:
                intent = new Intent(getActivity(), PhoneBookActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_who_viewed_my_contact:
                intent = new Intent(getActivity(), WhoViewMyContactActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_shortlist_profile:
                intent = new Intent(getActivity(), ShortListProfileActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_messgae:
                intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_interest_sent:
                intent = new Intent(getActivity(), InterestsActivity.class);
                intent.putExtra("select", "tab2");
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_Blocked:
                intent = new Intent(getActivity(), BlockedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_declined_member:
                intent = new Intent(getActivity(), DeclinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_help:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_setting:
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_contact_us:
                intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_about_us:
                intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
            case R.id.drawer_rate_the_app:
                intent = new Intent(getActivity(), JustJoinedActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;

        }
    }

}
