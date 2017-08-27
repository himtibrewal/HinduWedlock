package com.colaborotech.thehinduwedlock.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.models.ImageModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    List<ImageModel> imageList = new ArrayList<ImageModel>();
    LayoutInflater layoutInflater;
    int from;

    public MyCustomPagerAdapter(Context context, List<ImageModel> imageList, int from) {
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

        Picasso.with(context).load(imageList.get(position).getImageURL()).into(imageView);
        // imageView.setImageResource(images[position]);
        container.addView(itemView);

        //listening to image click
        tvSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeProfilePic(imageList.get(position).getImageId() + "", position);
                // Toast.makeText(context, "Profile click " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImage(imageList.get(position).getImageId() + "", position);
                //Toast.makeText(context, "delete clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private void makeProfilePic(String image_id, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("image_id=").append(image_id);
        stringBuilder.append("&user_id=").append(AppPref.getInstance().getuserId());
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(context, AppUrls.MAKE_PROFILE_PIC, 0, content, true, "Please Wait..", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("response", "is " + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String message = jsonObject.getString("message");
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }
        });
        getDataUsingWService.execute();
    }

    private void deleteImage(String image_id, final int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("image_id=").append(image_id);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(context, AppUrls.DELETE_IMAGE, 0, content, true, "Please Wait", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("delete_image", "is " + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String message = jsonObject.getString("message");
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    imageList.remove(position);
                    notifyDataSetChanged();
                } catch (Exception e) {

                }
            }
        });
        getDataUsingWService.execute();
    }
}