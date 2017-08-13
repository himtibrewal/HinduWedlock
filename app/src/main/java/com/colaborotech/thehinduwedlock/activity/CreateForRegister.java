package com.colaborotech.thehinduwedlock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;


/**
 * Created by him on 12-Jun-17.
 */

public class CreateForRegister extends BaseActivity implements View.OnClickListener {

    ImageView ivBack;
    TextView tvHeader;
    RelativeLayout rlSelf;
    RelativeLayout rlRelative;
    RelativeLayout rlBrother;
    RelativeLayout rlSister;
    RelativeLayout rlSon;
    RelativeLayout rlDaughter;
    RelativeLayout rlClient;
    RelativeLayout rlFriend;
    ImageView ivSelf;
    ImageView ivRelative;
    ImageView ivBrother;
    ImageView ivSister;
    ImageView ivSon;
    ImageView ivDaughter;
    ImageView ivClient;
    ImageView ivFriend;


    @Override
    public int getActivityLayout() {
        return R.layout.activity_createfor;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        rlSelf = (RelativeLayout) findViewById(R.id.rl_self);
        rlRelative = (RelativeLayout) findViewById(R.id.rl_relative);
        rlBrother = (RelativeLayout) findViewById(R.id.rl_brother);
        rlSister = (RelativeLayout) findViewById(R.id.rl_sister);
        rlSon = (RelativeLayout) findViewById(R.id.rl_son);
        rlDaughter = (RelativeLayout) findViewById(R.id.rl_daughter);
        rlClient = (RelativeLayout) findViewById(R.id.rl_client);
        rlFriend = (RelativeLayout) findViewById(R.id.rl_friend);
        ivSelf = (ImageView) findViewById(R.id.self_image);
        ivRelative = (ImageView) findViewById(R.id.relative_image);
        ivBrother = (ImageView) findViewById(R.id.brother_image);
        ivSister = (ImageView) findViewById(R.id.sister_image);
        ivSon = (ImageView) findViewById(R.id.son_image);
        ivDaughter = (ImageView) findViewById(R.id.daughter_image);
        ivClient = (ImageView) findViewById(R.id.client_image);
        ivFriend = (ImageView) findViewById(R.id.friend_image);
        ivBack.setOnClickListener(this);
        rlSelf.setOnClickListener(this);
        rlRelative.setOnClickListener(this);
        rlBrother.setOnClickListener(this);
        rlSister.setOnClickListener(this);
        rlSon.setOnClickListener(this);
        rlDaughter.setOnClickListener(this);
        rlClient.setOnClickListener(this);
        rlFriend.setOnClickListener(this);
    }

    @Override
    public void init(Bundle save) {
        // ivBack.setVisibility(View.GONE);
        tvHeader.setText("Create profile for");
        setInteredData();
    }

    private void setInteredData() {
        String profileFor = AppPref.getInstance().getCreateFor();
        if (profileFor.equalsIgnoreCase("SELF")) {
            rlSelf.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivSelf.setImageDrawable(getResources().getDrawable(R.drawable.self_white));
        } else if (profileFor.equalsIgnoreCase("RELATIVE")) {
            rlRelative.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivRelative.setImageDrawable(getResources().getDrawable(R.drawable.relative_white));
        } else if (profileFor.equalsIgnoreCase("DAUGHTER")) {
            rlDaughter.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivDaughter.setImageDrawable(getResources().getDrawable(R.drawable.daughter_white));
        } else if (profileFor.equalsIgnoreCase("BROTHER")) {
            rlBrother.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivBrother.setImageDrawable(getResources().getDrawable(R.drawable.brother_white));
        } else if (profileFor.equalsIgnoreCase("SISTER")) {
            rlSister.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivSister.setImageDrawable(getResources().getDrawable(R.drawable.sister_white));
        } else if (profileFor.equalsIgnoreCase("SON")) {
            rlSon.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivSon.setImageDrawable(getResources().getDrawable(R.drawable.son_white));
        } else if (profileFor.equalsIgnoreCase("CLIENT")) {
            rlClient.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivClient.setImageDrawable(getResources().getDrawable(R.drawable.client_white));

        } else if (profileFor.equalsIgnoreCase("FRIEND")) {
            rlFriend.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
            ivFriend.setImageDrawable(getResources().getDrawable(R.drawable.friend_white));
        } else {

        }
    }


    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }

    @Override
    public void onClick(View v) {
        rlSelf.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlRelative.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlBrother.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlSister.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlSon.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlDaughter.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlClient.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        rlFriend.setBackground(getResources().getDrawable(R.drawable.drawable_ractagle_hollow_pinkline));
        ivSelf.setImageDrawable(getResources().getDrawable(R.drawable.self_grey));
        ivRelative.setImageDrawable(getResources().getDrawable(R.drawable.relative_grey));
        ivBrother.setImageDrawable(getResources().getDrawable(R.drawable.brother_grey));
        ivSister.setImageDrawable(getResources().getDrawable(R.drawable.sister_grey));
        ivSon.setImageDrawable(getResources().getDrawable(R.drawable.son_grey));
        ivDaughter.setImageDrawable(getResources().getDrawable(R.drawable.daughter_grey));
        ivClient.setImageDrawable(getResources().getDrawable(R.drawable.client_grey));
        ivFriend.setImageDrawable(getResources().getDrawable(R.drawable.friend_grey));
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.rl_self:
                rlSelf.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("SELF");
                ivSelf.setImageDrawable(getResources().getDrawable(R.drawable.self_white));
                break;
            case R.id.rl_relative:
                rlRelative.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("RELATIVE");
                ivRelative.setImageDrawable(getResources().getDrawable(R.drawable.relative_white));
                break;
            case R.id.rl_son:
                rlSon.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("SON");
                AppPref.getInstance().setGender("MALE");
                ivSon.setImageDrawable(getResources().getDrawable(R.drawable.son_white));
                break;
            case R.id.rl_daughter:
                rlDaughter.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("DAUGHTER");
                AppPref.getInstance().setGender("FEMALE");
                ivDaughter.setImageDrawable(getResources().getDrawable(R.drawable.daughter_white));
                break;
            case R.id.rl_brother:
                rlBrother.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("BROTHER");
                AppPref.getInstance().setGender("MALE");
                ivBrother.setImageDrawable(getResources().getDrawable(R.drawable.brother_white));
                break;
            case R.id.rl_sister:
                rlSister.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("SISTER");
                AppPref.getInstance().setGender("FEMALE");
                ivSister.setImageDrawable(getResources().getDrawable(R.drawable.sister_white));
                break;
            case R.id.rl_client:
                rlClient.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("CLIENT");
                ivClient.setImageDrawable(getResources().getDrawable(R.drawable.client_white));
                break;
            case R.id.rl_friend:
                rlFriend.setBackground(getResources().getDrawable(R.drawable.drawable_racangle_solid_pinkfill));
                AppPref.getInstance().setCreateFor("FRIEND");
                ivFriend.setImageDrawable(getResources().getDrawable(R.drawable.friend_white));
                break;
        }
        //Seend to next  activity
        if (v.getId() != R.id.iv_back) {
            sendToThisActivity(PersonalDetailActivity.class);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
