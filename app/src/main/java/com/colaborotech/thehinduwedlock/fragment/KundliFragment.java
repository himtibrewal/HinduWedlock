package com.colaborotech.thehinduwedlock.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.utility.FragmentButton;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by him on 24-Jun-17.
 */

public class KundliFragment extends Fragment implements View.OnClickListener {
    FragmentButton fragmentButton;
    CustomLayoutTitleValue ctvHoroschope;
    CustomLayoutTitleValue ctvRashi;
    CustomLayoutTitleValue ctvNakshatra;
    CustomLayoutTitleValue ctvManglik;
    TextView tvSubmit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View kundliview = inflater.inflate(R.layout.fragment_kundli, container, false);
        initViews(kundliview);
        return kundliview;
    }


    private void initViews(View view) {
        ctvHoroschope = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_horoscope_match_kundli_info_fragment);
        ctvRashi = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_rashi_kundli_info_fragment);
        ctvNakshatra = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_nakshtra_kundli_info_fragment);
        ctvManglik = (CustomLayoutTitleValue) view.findViewById(R.id.ctv_manglik_kundli_info_fragment);
        tvSubmit = (TextView) view.findViewById(R.id.tv_next_kundli);
        ctvHoroschope.setValue(AppPref.getInstance().getHoroscopeMatch());
        ctvRashi.setValue(AppPref.getInstance().getRashi());
        ctvNakshatra.setValue(AppPref.getInstance().getNakshatra());
        ctvManglik.setValue(AppPref.getInstance().getManglik());

        ctvHoroschope.setOnClickListener(this);
        ctvRashi.setOnClickListener(this);
        ctvNakshatra.setOnClickListener(this);
        ctvManglik.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentButton = (FragmentButton) activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_horoscope_match_kundli_info_fragment:
                fragmentButton.buttonClicked(ctvHoroschope);
                break;
            case R.id.ctv_rashi_kundli_info_fragment:
                fragmentButton.buttonClicked(ctvRashi);
                break;
            case R.id.ctv_nakshtra_kundli_info_fragment:
                fragmentButton.buttonClicked(ctvNakshatra);
                break;
            case R.id.ctv_manglik_kundli_info_fragment:
                fragmentButton.buttonClicked(ctvManglik);
                break;
            case R.id.tv_next_kundli:
                sendDataToServer();
                break;

        }

    }

    private void sendDataToServer() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&Horoscope_check=").append(AppPref.getInstance().getHoroscopeMatch());
        stringBuilder.append("&rashi=").append(AppPref.getInstance().getRashi());
        stringBuilder.append("&nakshatra=").append(AppPref.getInstance().getNakshatra());
        stringBuilder.append("&manglik=").append(AppPref.getInstance().getManglik());
        String content = stringBuilder.toString();
        Log.e("content", content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), AppUrls.UPDATE_KUNDLI, 0, content, true, "please wait.", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("response", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    String message = jsonObject.getString("message");
                    if (response_code.equalsIgnoreCase("200")) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                }
            }
        });
        getDataUsingWService.execute();
    }
}
