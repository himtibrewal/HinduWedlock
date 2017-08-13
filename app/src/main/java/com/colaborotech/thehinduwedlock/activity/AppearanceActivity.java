package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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

public class AppearanceActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvComplexion;
    CustomLayoutTitleValue ctvBodytype;
    CustomLayoutTitleValue ctvweight;

    TextView tvCancel, tvHeader, tvSave;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_apperance;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvComplexion = (CustomLayoutTitleValue) findViewById(R.id.ctv_complexion_appearance);
        ctvBodytype = (CustomLayoutTitleValue) findViewById(R.id.ctv_body_type_appearance);
        ctvweight = (CustomLayoutTitleValue) findViewById(R.id.ctv_weight_appearance);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Appearance");

        ctvComplexion.setOnClickListener(this);
        ctvBodytype.setOnClickListener(this);
        ctvweight.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvComplexion.setValue(AppPref.getInstance().getComplexion());
        ctvBodytype.setValue(AppPref.getInstance().getBodyType());
        ctvweight.setValue(AppPref.getInstance().getWeight());

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
        AppPref.getInstance().setComplexion(ctvComplexion.getValue());
        AppPref.getInstance().setBodyType(ctvBodytype.getValue());
        AppPref.getInstance().setWeight(ctvweight.getValue());
        sendDataToServer();
    }

    private void sendDataToServer() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&complexion=").append(AppPref.getInstance().getComplexion());
        stringBuilder.append("&body_type=").append(AppPref.getInstance().getBodyType());
        stringBuilder.append("&weight=").append(AppPref.getInstance().getWeight());
        String content = stringBuilder.toString();
        Log.e("content", content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_APPEARANCE, 0, content, true, "please wait.", new GetWebServiceData() {
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
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.ctv_complexion_appearance:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.complexionModelList, R.id.ctv_complexion_appearance, "Complexion");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_body_type_appearance:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.bodyTypeModelList, R.id.ctv_body_type_appearance, "Body Type");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_weight_appearance:
                secondDialog("Enter Weight", "Enter weight", "", ctvweight);
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_complexion_appearance:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_body_type_appearance:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_complexion_appearance:
                        ctvComplexion.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_body_type_appearance:
                        ctvBodytype.setValue(((DataModel) Objects.get(position)).get_dataName());
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

    Dialog inputDialog;

    private void secondDialog(String title, String hint, String data, final CustomLayoutTitleValue ctv) {
        inputDialog = new Dialog(this, R.style.DialogSlideAnim2);
        inputDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        inputDialog.setContentView(R.layout.dialog_custom_input);
        inputDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = inputDialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        inputDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        inputDialog.setCancelable(false);
        final TextView tvSubmit = (TextView) inputDialog.findViewById(R.id.tv_input_input_dialog);
        TextView tvTitle = (TextView) inputDialog.findViewById(R.id.tv_title_input_dialog);
        final EditText etValue = (EditText) inputDialog.findViewById(R.id.et_input_input_dialog);
        ImageView ivCancel = (ImageView) inputDialog.findViewById(R.id.iv_close_input_dialog);
        etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        etValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        tvTitle.setText(title);
        etValue.setHint(hint);
        etValue.setText(data);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog.dismiss();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etValue.getText().toString().equalsIgnoreCase("")) {
                    ctv.setValue("Not filled");
                } else {
                    ctv.setValue(etValue.getText().toString());
                }
                inputDialog.dismiss();
            }
        });


        inputDialog.show();
    }

}
