package com.colaborotech.thehinduwedlock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.fragment.SliderFragment;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.MultipleModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.CustomLayoutTitleValue;

import java.util.List;

import static com.colaborotech.thehinduwedlock.utility.OtherFunction.returnMultipleModelArrayList;

/**
 * Created by him on 10-Jul-17.
 */

public class FavouriteActivity extends BaseActivity implements View.OnClickListener, SliderFragment.ReturnView, SliderFragment.ReturnMultipleView, SliderFragment.ReturnDone {

    DrawerLayout drawerLayout;
    CustomLayoutTitleValue ctvMusicFav;
    CustomLayoutTitleValue ctvBookFav;
    CustomLayoutTitleValue ctvDressStyleFav;
    CustomLayoutTitleValue ctvSports;
    CustomLayoutTitleValue ctvCuisine;
    CustomLayoutTitleValue ctvMovies;
    CustomLayoutTitleValue ctvReadFav;
    CustomLayoutTitleValue ctvTVShows;
    CustomLayoutTitleValue ctvVacationDestination;
    TextView tvCancel, tvHeader, tvSave;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_favourite;
    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        ctvMusicFav = (CustomLayoutTitleValue) findViewById(R.id.ctv_music_favourite);
        ctvBookFav = (CustomLayoutTitleValue) findViewById(R.id.ctv_book_favourite);
        ctvDressStyleFav = (CustomLayoutTitleValue) findViewById(R.id.ctv_dress_style_favourite);
        ctvSports = (CustomLayoutTitleValue) findViewById(R.id.ctv_sports_favourite);
        ctvCuisine = (CustomLayoutTitleValue) findViewById(R.id.ctv_cuisine_favourite);
        ctvMovies = (CustomLayoutTitleValue) findViewById(R.id.ctv_movies_favourite);
        ctvReadFav = (CustomLayoutTitleValue) findViewById(R.id.ctv_read_favourite);
        ctvTVShows = (CustomLayoutTitleValue) findViewById(R.id.ctv_tv_shows_favourite);
        ctvVacationDestination = (CustomLayoutTitleValue) findViewById(R.id.ctv_vacation_destination_favourite);


        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);

        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvHeader.setText("Favourite");

        ctvMusicFav.setOnClickListener(this);
        ctvBookFav.setOnClickListener(this);
        ctvDressStyleFav.setOnClickListener(this);
        ctvSports.setOnClickListener(this);
        ctvCuisine.setOnClickListener(this);
        ctvMovies.setOnClickListener(this);
        ctvReadFav.setOnClickListener(this);
        ctvTVShows.setOnClickListener(this);
        ctvVacationDestination.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        ctvMusicFav.setValue(AppPref.getInstance().getFavMusic());
        ctvBookFav.setValue(AppPref.getInstance().getFavBook());
        ctvDressStyleFav.setValue(AppPref.getInstance().getDressStyle());
        ctvSports.setValue(AppPref.getInstance().getSports());
        ctvCuisine.setValue(AppPref.getInstance().getCuisine());
        ctvMovies.setValue(AppPref.getInstance().getMovies());
        ctvReadFav.setValue(AppPref.getInstance().getFavRead());
        ctvTVShows.setValue(AppPref.getInstance().getTVShows());
        ctvVacationDestination.setValue(AppPref.getInstance().getVacationDestination());
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
        AppPref.getInstance().setFavMusic(ctvMusicFav.getValue());
        AppPref.getInstance().setFavBook(ctvBookFav.getValue());
        AppPref.getInstance().setDressStyle(ctvDressStyleFav.getValue());
        AppPref.getInstance().setSports(ctvSports.getValue());
        AppPref.getInstance().setCuisine(ctvCuisine.getValue());
        AppPref.getInstance().setMovies(ctvMovies.getValue());
        AppPref.getInstance().setFavRead(ctvReadFav.getValue());
        AppPref.getInstance().setTVShows(ctvTVShows.getValue());
        AppPref.getInstance().setVacationDestination(ctvVacationDestination.getValue());
        finish();
    }

    @Override
    public void onClick(View v) {
        SliderFragment.getInstance().setReturnView(this);
        SliderFragment.getInstance().setReturnMultipleView(this);
        SliderFragment.getInstance().setReturnDone(this);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                validation();
                break;
            case R.id.ctv_music_favourite:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.ctv_music_favourite, "Favourite Music");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_book_favourite:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.ctv_book_favourite, "Favourite Book");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_dress_style_favourite:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.ctv_dress_style_favourite, "Dress Style");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_sports_favourite:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.ctv_sports_favourite, "Sports");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_cuisine_favourite:
                SliderFragment.getInstance().setMultiple_lists(returnMultipleModelArrayList(TheHinduWedLockApp.categotyModelList, ""), R.id.ctv_cuisine_favourite, "Cuisine");
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ctv_movies_favourite:
                Intent intentmovies = new Intent(this, EnterDataActivity.class);
                intentmovies.putExtra("value", AppPref.getInstance().getMovies());
                intentmovies.putExtra("header", "Movies");
                startActivityForResult(intentmovies, 100);
                break;
            case R.id.ctv_read_favourite:
                Intent intentRead = new Intent(this, EnterDataActivity.class);
                intentRead.putExtra("value", AppPref.getInstance().getFavRead());
                intentRead.putExtra("header", "Books");
                startActivityForResult(intentRead, 200);
                break;
            case R.id.ctv_tv_shows_favourite:
                Intent intentTvShow = new Intent(this, EnterDataActivity.class);
                intentTvShow.putExtra("value", AppPref.getInstance().getTVShows());
                intentTvShow.putExtra("header", "TV Shows");
                startActivityForResult(intentTvShow, 300);
                break;
            case R.id.ctv_vacation_destination_favourite:
                Intent intentvac = new Intent(this, EnterDataActivity.class);
                intentvac.putExtra("value", AppPref.getInstance().getVacationDestination());
                intentvac.putExtra("header", "Vacation Destination");
                startActivityForResult(intentvac, 400);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            ctvMovies.setValue(textdata);
            AppPref.getInstance().setMovies(textdata.toString().trim());
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            ctvReadFav.setValue(textdata);
            AppPref.getInstance().setFavRead(textdata.toString().trim());
        } else if (requestCode == 300 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            ctvTVShows.setValue(textdata);
            AppPref.getInstance().setTVShows(textdata.toString().trim());
        } else if (requestCode == 400 && resultCode == Activity.RESULT_OK) {
            String textdata = data.getStringExtra("value");
            ctvVacationDestination.setValue(textdata);
            AppPref.getInstance().setVacationDestination(textdata.toString().trim());
        }
    }


    @Override
    public void getMultipleAdapterView(View view, List Objects, int position, int from) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        MultipleModel multipleModel = (MultipleModel) Objects.get(position);
        switch (from) {
            case R.id.ctv_music_favourite:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.ctv_book_favourite:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.ctv_dress_style_favourite:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.ctv_sports_favourite:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
            case R.id.ctv_cuisine_favourite:
                checkBox.setText(((DataModel) multipleModel.getObject()).get_dataName());
                break;
        }
    }

    @Override
    public void selectedMultipleModelList(List<MultipleModel> multipleModelList, int selection) {
        String selected_value = "";
        switch (selection) {
            case R.id.ctv_music_favourite:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvMusicFav.setValue(selected_value);
                break;
            case R.id.ctv_book_favourite:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvBookFav.setValue(selected_value);
                break;
            case R.id.ctv_dress_style_favourite:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvDressStyleFav.setValue(selected_value);
                break;
            case R.id.ctv_sports_favourite:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvSports.setValue(selected_value);
                break;
            case R.id.ctv_cuisine_favourite:
                for (int i = 0; i < multipleModelList.size(); i++) {
                    DataModel dataModel = (DataModel) multipleModelList.get(i).getObject();
                    if (i == (multipleModelList.size() - 1)) {
                        selected_value += dataModel.get_dataName();
                        continue;
                    }
                    selected_value += dataModel.get_dataName() + ",";
                }
                ctvCuisine.setValue(selected_value);
                break;

        }
        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    @Override
    public void getAdapterView(View view, final List Objects, final int position, final int from) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

