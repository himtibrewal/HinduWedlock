package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.SaveSearchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 5/8/17.
 */

public class SavedSearchActivity extends BaseActivity implements View.OnClickListener, RecyclerAdapter.ReturnView {
    private ImageView ivBack;
    private TextView tvHeader;
    private TextView tvManage;
    private RecyclerView rlList;
    private TextView tvNodata;
    private RecyclerAdapter recyclerAdapter;
    private List<SaveSearchModel> saveSearchList = new ArrayList<SaveSearchModel>();


    @Override
    public int getActivityLayout() {
        return R.layout.activity_saved_search;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvManage = (TextView) findViewById(R.id.toolbar_last);
        rlList = (RecyclerView) findViewById(R.id.rv_saved_search);
        tvNodata = (TextView) findViewById(R.id.tv_no_data);
        ivBack.setOnClickListener(this);
        tvManage.setOnClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rlList.setLayoutManager(llm);
        addTempData();


    }

    @Override
    public void init(Bundle save) {
        tvManage.setText("Manage");
        tvHeader.setText("Saved Searches(0)");
        tvManage.setTextColor(getResources().getColor(R.color.red_dark));
        tvNodata.setText("Not Saved Any Data");
        //tvNodata.setVisibility();
        recyclerAdapter = new RecyclerAdapter(saveSearchList, this, R.layout.item_saved_search, this, 0);
        rlList.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }


    private void addTempData() {
        List<DataModel> list = new ArrayList<DataModel>();
        list.add(new DataModel(1, "data1"));
        list.add(new DataModel(2, "data2"));
        SaveSearchModel saveSearchModel = new SaveSearchModel();
        saveSearchModel.setSearchType(0);
        saveSearchModel.setViewed(list);
        saveSearchModel.setPhoto(list);
        saveSearchModel.setHeightFrom(list);
        saveSearchModel.setHeightTo(list);
        saveSearchModel.setDataName("himanhu");
        for (int i = 0; i < 5; i++) {
            saveSearchList.add(saveSearchModel);
        }
        hideDeleteIcon();
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvData = (TextView) view.findViewById(R.id.tv_data);
        ImageView ivDelete = (ImageView) view.findViewById(R.id.iv_clickicon);
        tvName.setText(((SaveSearchModel) objects).getDataName());
        tvData.setText(((SaveSearchModel) objects).getDataName());

    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.toolbar_last:
                break;
        }
    }


    private void showDeleteIcon() {
        for (int i = 0; i < saveSearchList.size(); i++) {
            saveSearchList.get(i).setMakeEdit(true);
        }
    }

    private void hideDeleteIcon() {
        for (int i = 0; i < saveSearchList.size(); i++) {
            saveSearchList.get(i).setMakeEdit(false);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
