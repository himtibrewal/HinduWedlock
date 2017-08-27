package com.colaborotech.thehinduwedlock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.FragementData;

/**
 * Created by him on 03-Aug-17.
 */

public class AboutFragment extends Fragment implements FragementData {

    TextView tvUserId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String data = getArguments().getString("data", "");
        Log.e("data_in_frag", "is" + data);
    }


    @Override
    public void dataGet(String data) {
        Log.e("data_in_frag_full", "is" + data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        return view;
    }
}
