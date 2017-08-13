package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.utility.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by him on 08-Jul-17.
 */

public class SpclCaseActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvChallenged;
    CustomLayoutTitleValue ctvThalassemia;
    CustomLayoutTitleValue ctvHiv;

    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_spcl_cases;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvChallenged = (CustomLayoutTitleValue) findViewById(R.id.ctv_challenged_spcl_cses);
        ctvThalassemia = (CustomLayoutTitleValue) findViewById(R.id.ctv_thalassemia_spcl_cses);
        ctvHiv = (CustomLayoutTitleValue) findViewById(R.id.ctv_hiv_spcl_cses);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Special Cases");

        ctvChallenged.setOnClickListener(this);
        ctvThalassemia.setOnClickListener(this);
        ctvHiv.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvChallenged.setValue(AppPref.getInstance().getChallenged());
        ctvThalassemia.setValue(AppPref.getInstance().getThalassemia());
        ctvHiv.setValue(AppPref.getInstance().getHIV());

    }

    private void sendDataToServer() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&challenged=").append(AppPref.getInstance().getChallenged());
        stringBuilder.append("&thalassemia=").append(AppPref.getInstance().getThalassemia());
        stringBuilder.append("&hiv=").append(AppPref.getInstance().getHIV());
        String content = stringBuilder.toString();
        Log.e("content", content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_SPCL_CASE, 0, content, true, "please wait.", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("response", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        finish();
                    }

                } catch (JSONException e) {

                }
            }
        });
        getDataUsingWService.execute();
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onStart() {
        super.onStart();
        setFragment(new SliderFragment());
    }


    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    private void validation() {
        AppPref.getInstance().setChallenged(ctvChallenged.getValue());
        AppPref.getInstance().setThalassemia(ctvThalassemia.getValue());
        AppPref.getInstance().setHiv(ctvHiv.getValue());
        sendDataToServer();
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.ctv_challenged_spcl_cses:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.challengedModelList, R.id.ctv_challenged_spcl_cses, "Challenged");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_thalassemia_spcl_cses:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.thalassemiaModelList, R.id.ctv_thalassemia_spcl_cses, "Thalassemia");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_hiv_spcl_cses:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.hivModelList, R.id.ctv_hiv_spcl_cses, "Hiv+");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_challenged_spcl_cses:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_thalassemia_spcl_cses:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_hiv_spcl_cses:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_challenged_spcl_cses:
                        ctvChallenged.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_thalassemia_spcl_cses:
                        ctvThalassemia.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_hiv_spcl_cses:
                        ctvHiv.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
