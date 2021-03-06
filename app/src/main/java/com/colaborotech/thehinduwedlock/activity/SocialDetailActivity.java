package com.colaborotech.thehinduwedlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.custom.CustomLayoutTitleValue;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;

import java.util.List;

/**
 * Created by him on 18-Jun-17.
 */

public class SocialDetailActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView {

    private ImageView ivBack;
    private TextView tvHeader;
    private DrawerLayout drawerLayout;
    private CustomLayoutTitleValue ctvMaritalStatus;
    private CustomLayoutTitleValue ctvMotherTongue;
    private CustomLayoutTitleValue ctvReligion;
    private CustomLayoutTitleValue ctvManglik;
    private CustomLayoutTitleValue ctvHoroScope;
    private LinearLayout llOpenForAllCaste;
    private CheckBox ivOpenForAllSelection;
    private LinearLayout llopen, llmanglik, llhoro;
    private TextView tvNext;
    private Dialog secondDialog;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_social_detail;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvMaritalStatus = (CustomLayoutTitleValue) findViewById(R.id.ctv_marital_stats_social_detail);
        ctvMotherTongue = (CustomLayoutTitleValue) findViewById(R.id.ctv_mother_tongue_social_detail);
        ctvReligion = (CustomLayoutTitleValue) findViewById(R.id.ctv_Religion_social_detail);
        ctvManglik = (CustomLayoutTitleValue) findViewById(R.id.ctv_manglik_social_detail);
        ctvHoroScope = (CustomLayoutTitleValue) findViewById(R.id.ctv_horoscope_social_detail);
        llOpenForAllCaste = (LinearLayout) findViewById(R.id.ll_open_for_all_caste_social_detail);
        ivOpenForAllSelection = (CheckBox) findViewById(R.id.iv_open_for_all_caste_social_detail);
        llopen = (LinearLayout) findViewById(R.id.ll_caste_no_bar_social);
        llmanglik = (LinearLayout) findViewById(R.id.ll_manglik_social);
        llhoro = (LinearLayout) findViewById(R.id.ll_horo_social);


        tvNext = (TextView) findViewById(R.id.tv_next_social_detail);
        ivBack.setOnClickListener(this);
        ctvMaritalStatus.setOnClickListener(this);
        ctvMotherTongue.setOnClickListener(this);
        ctvReligion.setOnClickListener(this);
        ctvManglik.setOnClickListener(this);
        ctvHoroScope.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        fullhide();

    }

    private void fullhide() {
        ctvManglik.setVisibility(View.GONE);
        ctvHoroScope.setVisibility(View.GONE);
        llOpenForAllCaste.setVisibility(View.GONE);
        llopen.setVisibility(View.GONE);
        llmanglik.setVisibility(View.GONE);
        llhoro.setVisibility(View.GONE);
    }

    @Override
    public void init(Bundle save) {
        tvHeader.setText("Social Details");
        if (!AppPref.getInstance().getMaritalStatus().equalsIgnoreCase("")) {
            ctvMaritalStatus.setValue(AppPref.getInstance().getMaritalStatus());
        }
        if (!AppPref.getInstance().getHaveChild().equalsIgnoreCase("")) {
            ctvMaritalStatus.setValue(AppPref.getInstance().getMaritalStatus() + " - " + AppPref.getInstance().getHaveChild());
        }
        if (!AppPref.getInstance().getMotherTongue().equalsIgnoreCase("")) {
            ctvMotherTongue.setValue(AppPref.getInstance().getMotherTongue());
        }
        if (!AppPref.getInstance().getReligion().equalsIgnoreCase("")) {
            ctvReligion.setValue(AppPref.getInstance().getReligion());
            ctvManglik.setVisibility(View.VISIBLE);
            ctvHoroScope.setVisibility(View.VISIBLE);
            llOpenForAllCaste.setVisibility(View.VISIBLE);
            llopen.setVisibility(View.VISIBLE);
            llmanglik.setVisibility(View.VISIBLE);
            llhoro.setVisibility(View.VISIBLE);
        }
        if (!AppPref.getInstance().getCaste().equalsIgnoreCase("")) {
            ctvReligion.setValue(AppPref.getInstance().getReligion() + " - " + AppPref.getInstance().getCaste());
        }
        if (!AppPref.getInstance().getManglik().equalsIgnoreCase("")) {
            ctvManglik.setValue(AppPref.getInstance().getManglik());
        }
        if (!AppPref.getInstance().getHoroscopeMatch().equalsIgnoreCase("")) {
            ctvHoroScope.setValue(AppPref.getInstance().getHoroscopeMatch());
        }


        ivOpenForAllSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppPref.getInstance().setOpenForAll(true);
                } else {
                    AppPref.getInstance().setOpenForAll(false);
                }
            }
        });
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

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ctv_marital_stats_social_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.maritalStausModelList, R.id.ctv_marital_stats_social_detail, "Marital Status");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_mother_tongue_social_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.languageModelList, R.id.ctv_mother_tongue_social_detail, "Mother Tongue");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_Religion_social_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.religionModelList, R.id.ctv_Religion_social_detail, "Religion");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_manglik_social_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.manglikModelList, R.id.ctv_manglik_social_detail, "Manglik");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_horoscope_social_detail:
                SliderFragment.getInstance().setLists(TheHinduWedLockApp.horoscopeModelList, R.id.ctv_horoscope_social_detail, "Horoscope Must for Marriage?");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_next_social_detail:
                validation();
                break;
        }
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {
        TextView textView = (TextView) view.findViewById(R.id.spinner_item);
        switch (from) {
            case R.id.ctv_marital_stats_social_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_mother_tongue_social_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_Religion_social_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());

                break;
            case R.id.ctv_manglik_social_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;
            case R.id.ctv_horoscope_social_detail:
                textView.setText(((DataModel) Objects.get(position)).get_dataName());
                break;

        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (from) {
                    case R.id.ctv_marital_stats_social_detail:
                        if (((DataModel) Objects.get(position)).get_id() == 1) {
                            ctvMaritalStatus.setValue(((DataModel) Objects.get(position)).get_dataName());
                            drawerLayout.closeDrawer(Gravity.RIGHT);
                        } else {
                            secondDialog("Having child", TheHinduWedLockApp.haveClildModelList, ((DataModel) Objects.get(position)).get_dataName(), ctvMaritalStatus, R.id.ctv_marital_stats_social_detail);
                        }
                        AppPref.getInstance().setMaritalStatus(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setMaritalStatusId(((DataModel) Objects.get(position)).get_id());
                        break;
                    case R.id.ctv_mother_tongue_social_detail:
                        ctvMotherTongue.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setMotherTongueId(((DataModel) Objects.get(position)).get_id());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.ctv_Religion_social_detail:
                        ctvReligion.setValue(((DataModel) Objects.get(position)).get_dataName());
                        //   secondDialog("Caste", TheHinduWedLockApp.casteModelList, ((DataModel) Objects.get(position)).get_dataName(), ctvReligion, R.id.ctv_Religion_social_detail);
                        secondDialog("Caste", TheHinduWedLockApp.casteModelList, ctvReligion, ((DataModel) Objects.get(position)).get_id());
                        AppPref.getInstance().setReligion(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setReligionId(((DataModel) Objects.get(position)).get_id());

                        break;
                    case R.id.ctv_manglik_social_detail:
                        ctvManglik.setValue(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        AppPref.getInstance().setManglikId(((DataModel) Objects.get(position)).get_id());
                        break;
                    case R.id.ctv_horoscope_social_detail:
                        ctvHoroScope.setValue(((DataModel) Objects.get(position)).get_dataName());
                        AppPref.getInstance().setHoroscopeMatch(((DataModel) Objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                }

            }
        });

    }

    private void validation() {
        String marital_status = ctvMaritalStatus.getValue().toString();
        String mother_tongue = ctvMotherTongue.getValue().toString();
        String religion = ctvReligion.getValue().toString();
        String manglik = ctvManglik.getValue().toString();

        if (marital_status.equalsIgnoreCase("") | marital_status.equalsIgnoreCase("not filled")) {
            toastMessage("please select marital status");
            return;
        } else if (mother_tongue.equalsIgnoreCase("") | mother_tongue.equalsIgnoreCase("not filled")) {
            toastMessage("please select mother  tongue");
            return;
        } else if (religion.equalsIgnoreCase("") | religion.equalsIgnoreCase("not filled")) {
            toastMessage("please select religion");
            return;
        } else {
            AppPref.getInstance().setMotherTongue(mother_tongue);
            AppPref.getInstance().setManglik(manglik);
            sendToThisActivity(LoginDetailActivity.class);
        }

    }

    private void secondDialog(String header, final List<DataModel> list, final String data, final CustomLayoutTitleValue ctv, int id) {
        secondDialog = new Dialog(this, R.style.DialogSlideAnim);
        secondDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        secondDialog.setContentView(R.layout.dialog_second_drawer);
        secondDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = secondDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        secondDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        TextView textView = (TextView) secondDialog.findViewById(R.id.tv_top_text_second_dilaog);
        textView.setText(header);
        RecyclerView recyclerView = (RecyclerView) secondDialog.findViewById(R.id.dialog_recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, final List objects, final int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctv.setValue(data + " - " + ((DataModel) objects.get(position)).get_dataName());
                        secondDialog.dismiss();
                        AppPref.getInstance().setMaritalStatus(data);
                        AppPref.getInstance().setHaveChild(((DataModel) objects.get(position)).get_dataName());
                        drawerLayout.closeDrawer(Gravity.RIGHT);

                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        secondDialog.show();
    }


    private void secondDialog(String header, List<DataModel> list, final CustomLayoutTitleValue ctv, int religionid) {
        secondDialog = new Dialog(this, R.style.DialogSlideAnim);
        secondDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        secondDialog.setContentView(R.layout.dialog_second_drawer);
        secondDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = secondDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        secondDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) secondDialog.findViewById(R.id.dialog_recyclerView);
        TextView tvHeader = (TextView) secondDialog.findViewById(R.id.tv_top_text_second_dilaog);
        tvHeader.setText(header);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, this, R.layout.layout_slider_item, new RecyclerAdapter.ReturnView() {
            @Override
            public void getAdapterView(View view, final List objects, final int position, int from) {
                final TextView tvCountry = (TextView) view.findViewById(R.id.spinner_item);
                tvCountry.setText(((DataModel) objects.get(position)).get_dataName());
                tvCountry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String peeviousStrng = ctv.getValue().toString();
                        ctv.setValue(peeviousStrng + " - " + ((DataModel) objects.get(position)).get_dataName());
                        secondDialog.dismiss();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        ctvManglik.setVisibility(View.VISIBLE);
                        ctvHoroScope.setVisibility(View.VISIBLE);
                        llOpenForAllCaste.setVisibility(View.VISIBLE);
                        llopen.setVisibility(View.VISIBLE);
                        llmanglik.setVisibility(View.VISIBLE);
                        llhoro.setVisibility(View.VISIBLE);
                        AppPref.getInstance().setCaste(((DataModel) objects.get(position)).get_dataName());
                        AppPref.getInstance().setCasteId(((DataModel) objects.get(position)).get_id());
                    }
                });
            }
        }, 0);
        recyclerView.setAdapter(recyclerAdapter);
        secondDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
