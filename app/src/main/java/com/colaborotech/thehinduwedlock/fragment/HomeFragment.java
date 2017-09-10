package com.colaborotech.thehinduwedlock.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.activity.AcceptedMembersActivity;
import com.colaborotech.thehinduwedlock.activity.FamilyDetailActivity;
import com.colaborotech.thehinduwedlock.activity.JustJoinedActivity;
import com.colaborotech.thehinduwedlock.activity.MobileVerificationActivity;
import com.colaborotech.thehinduwedlock.activity.ProfileEditActivity;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.models.SliderIconModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by him on 08-Jun-17.
 */

public class HomeFragment extends Fragment implements RecyclerAdapter.ReturnView, View.OnClickListener, GetWebServiceData {
    private RecyclerView recyclerViewEdit, recyclerView1, recyclerView11;
    private List<SliderIconModel> list = new ArrayList<SliderIconModel>();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerAdapter recyclerAdapter1;
    private RecyclerAdapter recyclerAdapter2;
    private RelativeLayout rlLeftItem;
    private LinearLayout llAllAccept;
    private LinearLayout llJustJoined;
    private TextView tvAllAccept;
    private TextView tvJustJoined;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initializeView(homeView);
        recyclerViewEdit = (RecyclerView) homeView.findViewById(R.id.rv_edit_detail_home);
        recyclerView1 = (RecyclerView) homeView.findViewById(R.id.firstList1);
        recyclerView11 = (RecyclerView) homeView.findViewById(R.id.firstList11);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm11 = new LinearLayoutManager(getActivity());
        llm11.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewEdit.setLayoutManager(llm);
        recyclerView1.setLayoutManager(llm1);
        recyclerView11.setLayoutManager(llm11);
        list.add(new SliderIconModel(1, R.drawable.ic_menu_camera, "Complete"));
        list.add(new SliderIconModel(2, R.drawable.camera_white, "Photo"));
        list.add(new SliderIconModel(3, R.drawable.basic_white, "Basic"));
        list.add(new SliderIconModel(4, R.drawable.kundli_white, "Kundli"));
        list.add(new SliderIconModel(5, R.drawable.education_white, "Education"));
        list.add(new SliderIconModel(6, R.drawable.career_white, "Career"));
        list.add(new SliderIconModel(7, R.drawable.family_white, "Family"));
        list.add(new SliderIconModel(8, R.drawable.lifestyle_white, "Lifestyle"));
        list.add(new SliderIconModel(9, R.drawable.contact_white, "Contact"));
        list.add(new SliderIconModel(10, R.drawable.contact_white, "Desired"));
        recyclerAdapter = new RecyclerAdapter(list, getActivity(), R.layout.list_home_userdetail, this, R.id.rv_edit_detail_home);
        recyclerAdapter1 = new RecyclerAdapter(list, getActivity(), R.layout.item_all_accept, this, 0);
        recyclerAdapter2 = new RecyclerAdapter(list, getActivity(), R.layout.item_all_accept, this, 0);
        recyclerViewEdit.setAdapter(recyclerAdapter);
        recyclerView1.setAdapter(recyclerAdapter1);
        recyclerView11.setAdapter(recyclerAdapter2);
        setMobileNoVerifyed();
        return homeView;
    }


    private void initializeView(View view) {
        rlLeftItem = (RelativeLayout) view.findViewById(R.id.rl_left_item);
        rlLeftItem.setOnClickListener(this);
        llAllAccept = (LinearLayout) view.findViewById(R.id.ll_all_accept);
        llJustJoined = (LinearLayout) view.findViewById(R.id.ll_just_joined);
        tvAllAccept = (TextView) view.findViewById(R.id.tv_all_accept);
        tvAllAccept = (TextView) view.findViewById(R.id.tv_just_joined);
        llAllAccept.setOnClickListener(this);
        llJustJoined.setOnClickListener(this);
    }


    private void setMobileNoVerifyed() {
        if (!AppPref.getInstance().getMobileVerify()) {
            Intent intent = new Intent(getActivity(), MobileVerificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_left_item:
                sendTothisActivity(FamilyDetailActivity.class);
                getActivity().finish();
                break;
            case R.id.ll_all_accept:
                sendTothisActivity(AcceptedMembersActivity.class);
                break;
            case R.id.ll_just_joined:
                sendTothisActivity(JustJoinedActivity.class);
                break;
        }
    }


    private void sendTothisActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void getAdapterView(View view, List objects, final int position, int from) {
        switch (from) {
            case R.id.rv_edit_detail_home:
                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_edit_profile);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_infocategory_icon_homeFragment);
                TextView textView = (TextView) view.findViewById(R.id.tv_infocategoty_name_homefragment);
                imageView.setImageDrawable(getResources().getDrawable(((SliderIconModel) objects.get(position)).getIcon()));
                textView.setText(((SliderIconModel) objects.get(position)).getName());
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {

                        } else if (position > 0) {
                            Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                            intent.putExtra("position", position - 1);
                            startActivity(intent);
                        }

                    }
                });
                break;
        }

    }

    private void callWebservice() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {

    }
}
