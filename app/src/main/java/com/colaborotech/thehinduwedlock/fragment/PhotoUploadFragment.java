package com.colaborotech.thehinduwedlock.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.activity.ImageSlidingActivity;
import com.colaborotech.thehinduwedlock.adapter.RecyclerAdapter;
import com.colaborotech.thehinduwedlock.models.ImageModel;
import com.colaborotech.thehinduwedlock.models.MenuModel;
import com.colaborotech.thehinduwedlock.service.MyUploadService;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by him on 24-Jun-17.
 */

public class PhotoUploadFragment extends Fragment implements View.OnClickListener, RecyclerAdapter.ReturnView {

    private RelativeLayout rlGallery;
    private RelativeLayout rlCamera;
    private RelativeLayout rlFacebook;
    private ImageView ivMainImage;
    private TextView tvProfileChange;
    private TextView tvPhotoPrivacy;
    private TextView tvPhotoCount;
    private static final int RC_TAKE_PICTURE = 101;
    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";

    private BroadcastReceiver mBroadcastReceiver;
    private ProgressDialog mProgressDialog;

    private Uri mDownloadUrl = null;
    private Uri mFileUri = null;
    private List<ImageModel> imageList = new ArrayList<ImageModel>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View photoView = inflater.inflate(R.layout.fragment_photoupload, container, false);
        initViews(photoView);
        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(KEY_FILE_URI);
            mDownloadUrl = savedInstanceState.getParcelable(KEY_DOWNLOAD_URL);
        }

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive:" + intent);
                hideProgressDialog();
                switch (intent.getAction()) {
                    case MyUploadService.UPLOAD_COMPLETED:
                    case MyUploadService.UPLOAD_ERROR:
                        onUploadResultIntent(intent);
                        break;
                }

            }
        };

        // onNewIntent(getIntent());
        return photoView;
    }


    @Override
    public void onStart() {
        super.onStart();
        // Register receiver for uploads and downloads
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getActivity());
        // manager.registerReceiver(mBroadcastReceiver, MyDownloadService.getIntentFilter());
        manager.registerReceiver(mBroadcastReceiver, MyUploadService.getIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();

        // Unregister download receiver
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
    }

    private void onUploadResultIntent(Intent intent) {
        // Got a new intent from MyUploadService with a success or failure
        mDownloadUrl = intent.getParcelableExtra(MyUploadService.EXTRA_DOWNLOAD_URL);
        mFileUri = intent.getParcelableExtra(MyUploadService.EXTRA_FILE_URI);
        uploadimageurl();
        Log.e("Download url", mDownloadUrl.toString());
        Picasso.with(getActivity()).load(mDownloadUrl.toString()).into(ivMainImage);

    }


    private void uploadimageurl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&image=").append("Image_" + AppPref.getInstance().getuserId() + "%2FImage" + AppPref.getInstance().getNoOfImage());
        String content = stringBuilder.toString();
        Log.e("content", "is" + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), AppUrls.UPDATE_IMAGE, 0, content, true, "Please wait", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                Log.e("responsse", "is" + responseData);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String response_code = jsonObject.getString("response_code");
                    if (response_code.equalsIgnoreCase("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        int imageCount = jsonArray.length();
                        AppPref.getInstance().setNoOfImage(imageCount);
                        List<ImageModel> imageList = new ArrayList<ImageModel>();
                        for (int i = 0; i < imageCount; i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            int image_id = jsonObject1.getInt("image_id");
                            String imageurl = "https://firebasestorage.googleapis.com/v0/b/thehindu-24e87.appspot.com/o/" + jsonObject1.getString("image") + "?alt=media";
                            String profile = jsonObject1.getString("profile");
                            imageList.add(new ImageModel(image_id, imageurl, profile));
                        }
                        Gson gson = new Gson();
                        String imageUrls = gson.toJson(imageList);
                        AppPref.getInstance().setImageUrls(imageUrls);
                        tvPhotoCount.setText(imageCount + " Photo");
                    }

                } catch (Exception e) {

                }


            }
        });
        getDataUsingWService.execute();
    }

    private void initViews(View view) {
        rlGallery = (RelativeLayout) view.findViewById(R.id.rl_item1);
        rlCamera = (RelativeLayout) view.findViewById(R.id.rl_item2);
        rlFacebook = (RelativeLayout) view.findViewById(R.id.rl_item3);
        ivMainImage = (ImageView) view.findViewById(R.id.iv_main_Image_photo_upload_fragment);
        tvProfileChange = (TextView) view.findViewById(R.id.tv_profile_change);
        tvPhotoPrivacy = (TextView) view.findViewById(R.id.tv_ptoto_privacy);
        tvPhotoCount = (TextView) view.findViewById(R.id.tv_photo_count);
        rlGallery.setOnClickListener(this);
        rlCamera.setOnClickListener(this);
        rlFacebook.setOnClickListener(this);
        ivMainImage.setOnClickListener(this);
        tvProfileChange.setOnClickListener(this);
        tvPhotoPrivacy.setOnClickListener(this);
        tvPhotoCount.setText(AppPref.getInstance().getNoOfImage() + " Photo");
        Gson gson = new Gson();
        imageList = gson.fromJson(AppPref.getInstance().getImageUrls(), new TypeToken<List<ImageModel>>() {
        }.getType());
        if (imageList.size() > 0) {
            Picasso.with(getActivity()).load(imageList.get(0).getImageURL()).into(ivMainImage);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle out) {
        out.putParcelable(KEY_FILE_URI, mFileUri);
        out.putParcelable(KEY_DOWNLOAD_URL, mDownloadUrl);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ImageSlidingActivity.class);
        switch (v.getId()) {
            case R.id.rl_item1:
                launchCamera();
                break;
            case R.id.rl_item2:
                launchCamera();
                break;
            case R.id.rl_item3:
                launchCamera();
                break;
            case R.id.tv_ptoto_privacy:
                dialogPrivacy();
                break;
            case R.id.iv_main_Image_photo_upload_fragment:
                intent.putExtra("from", 0);
                startActivity(intent);
                break;
            case R.id.tv_profile_change:
                intent.putExtra("from", 1);
                startActivity(intent);
                break;

        }
    }


    private void showProgressDialog(String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.setMessage(caption);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    private void launchCamera() {
        Log.d(TAG, "launchCamera");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, RC_TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        if (requestCode == RC_TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                mFileUri = data.getData();

                if (mFileUri != null) {
                    uploadFromUri(mFileUri);
                } else {
                    Log.w(TAG, "File URI is null");
                }
            } else {
                Toast.makeText(getActivity(), "Taking picture failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFromUri(Uri fileUri) {
        Log.d(TAG, "uploadFromUri:src:" + fileUri.toString());

        // Save the File URI
        mFileUri = fileUri;

        // Start MyUploadService to upload the file, so that the file is uploaded
        // even if this Activity is killed or put in the background
        getActivity().startService(new Intent(getActivity(), MyUploadService.class)
                .putExtra(MyUploadService.EXTRA_FILE_URI, fileUri)
                .setAction(MyUploadService.ACTION_UPLOAD));

        // Show loading spinner
        showProgressDialog("uploading..");
    }

    RecyclerAdapter recyclerAdapter;
    List<MenuModel> list;

    private void dialogPrivacy() {
        final Dialog privacyDialog = new Dialog(getActivity());
        privacyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        privacyDialog.setContentView(R.layout.dialog_privacy_photo);
        privacyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = privacyDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        privacyDialog.setCanceledOnTouchOutside(true);
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        RecyclerView recyclerView = (RecyclerView) privacyDialog.findViewById(R.id.recyclcerView);
        TextView tvSubmit = (TextView) privacyDialog.findViewById(R.id.tv_submit);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        list = new ArrayList<MenuModel>();
        list.add(new MenuModel(0, "Visible to all(Recommended)", false));
        list.add(new MenuModel(1, "Visible to those you have accepted or expressed interest in", false));
        recyclerAdapter = new RecyclerAdapter(list, getActivity(), R.layout.item_drawer_privacy, this, 0);
        recyclerView.setAdapter(recyclerAdapter);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyDialog.dismiss();
            }
        });
        privacyDialog.show();
    }


    @Override
    public void getAdapterView(View view, final List objects, final int position, int from) {
        TextView tvData = (TextView) view.findViewById(R.id.tv_data);
        tvData.setText(((MenuModel) objects.get(position)).getData());
        final ImageView ivSelected = (ImageView) view.findViewById(R.id.iv_selected);
        RelativeLayout rlMain = (RelativeLayout) view.findViewById(R.id.rl_main);
        if (((MenuModel) objects.get(position)).isSelected()) {
            ivSelected.setVisibility(View.VISIBLE);
        } else {
            ivSelected.setVisibility(View.GONE);
        }
        rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelected(false);
                }
                list.get(position).setSelected(true);
                recyclerAdapter.notifyDataSetChanged();
            }
        });

    }
}
