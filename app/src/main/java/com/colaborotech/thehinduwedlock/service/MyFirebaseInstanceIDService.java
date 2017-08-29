package com.colaborotech.thehinduwedlock.service;


import android.util.Log;

import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;
import static com.colaborotech.thehinduwedlock.utility.AppUrls.UPDATE_TOKEN;

/**
 * Created by him on 29-Aug-17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        AppPref.getInstance().setRegToken(refreshedToken);
        //  sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String tokenValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&reg_token=").append(tokenValue);
        String content = stringBuilder.toString();
        Log.e("reg_token", "send: " + content);
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(TheHinduWedLockApp.getInstance().getApplicationContext(), UPDATE_TOKEN, 0, content, false, "", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {

            }
        });
        getDataUsingWService.execute();

    }
}
