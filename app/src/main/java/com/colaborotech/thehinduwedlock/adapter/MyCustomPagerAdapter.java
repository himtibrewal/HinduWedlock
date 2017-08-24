package com.colaborotech.thehinduwedlock.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colaborotech.thehinduwedlock.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    List<String> imageList = new ArrayList<String>();
    LayoutInflater layoutInflater;
    int from;

    public MyCustomPagerAdapter(Context context, List<String> imageList, int from) {
        this.context = context;
        this.from = from;
        this.imageList = imageList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        TextView tvSetProfile = (TextView) itemView.findViewById(R.id.tv_choose_profile_pic);
        ImageView ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
        if (from == 0) {
            ivDelete.setVisibility(View.VISIBLE);
            tvSetProfile.setVisibility(View.GONE);
        } else if (from == 1) {
            ivDelete.setVisibility(View.GONE);
            tvSetProfile.setVisibility(View.VISIBLE);
        }
        Picasso.with(context).load(imageList.get(position).toString()).into(imageView);
        // imageView.setImageResource(images[position]);
        container.addView(itemView);

        //listening to image click
        tvSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Profile click " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "delete clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}