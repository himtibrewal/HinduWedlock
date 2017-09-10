package com.colaborotech.thehinduwedlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by him on 16-Jul-17.
 */

public class VerificationScreenActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "PhoneAuthActivity";
    String phoneNo;
    String countryCode;
    ImageView ivBack;
    // [END declare_auth]
    TextView tvHeader;
    TextView tvMobileNo;
    TextView tvResend;
    EditText etCode1, etCode2, etCode3, etCode4, etCode5, etCode6;
    TextView tvSubmit;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_mobile_verification;
    }

    @Override
    public void initialize() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvHeader = (TextView) findViewById(R.id.toolbar_title);
        tvMobileNo = (TextView) findViewById(R.id.infotext);

        tvResend = (TextView) findViewById(R.id.code_resend);
        etCode1 = (EditText) findViewById(R.id.et_digit1_verify_number);
        etCode2 = (EditText) findViewById(R.id.et_digit2_verify_number);
        etCode3 = (EditText) findViewById(R.id.et_digit3_verify_number);
        etCode4 = (EditText) findViewById(R.id.et_digit4_verify_number);
        etCode5 = (EditText) findViewById(R.id.et_digit5_verify_number);
        etCode6 = (EditText) findViewById(R.id.et_digit6_verify_number);
        tvSubmit = (TextView) findViewById(R.id.tv_submit_verify_number);
        ivBack.setOnClickListener(this);
        tvHeader.setText("Verify your number");
        tvMobileNo.setText("Verification code sent to " + countryCode + " " + phoneNo);
        tvResend.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    private void validation() {
        String digit1 = etCode1.getText().toString();
        String digit2 = etCode2.getText().toString();
        String digit3 = etCode3.getText().toString();
        String digit4 = etCode4.getText().toString();
        String digit5 = etCode5.getText().toString();
        String digit6 = etCode6.getText().toString();
        if (digit1.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else if (digit2.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else if (digit3.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else if (digit4.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else if (digit5.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else if (digit6.equalsIgnoreCase("")) {
            toastMessage("enter digits");
            return;
        } else {
            verifyPhoneNumberWithCode(mVerificationId, digit1 + "" + digit2 + "" + digit3 + "" + digit4 + "" + digit5 + "" + digit6);
        }
    }

    @Override
    public void init(Bundle save) {
        if (getIntent().getExtras() != null) {
            phoneNo = getIntent().getExtras().getString("phone_no");
            countryCode = getIntent().getExtras().getString("country_code");
        }
        tvMobileNo.setText("Verification code sent to " + countryCode + " " + phoneNo);
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Invalid Phone Number",
                            Snackbar.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    @Override
    public int getActivityTitle() {
        return R.string.app_name;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                signOut();
                onBackPressed();
                break;
            case R.id.tv_submit_verify_number:
                validation();
                break;
            case R.id.code_resend:
                resendVerificationCode(phoneNo, mResendToken);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        startPhoneNumberVerification(countryCode + "" + phoneNo);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            Log.e("hhhh", user.getUid());
                            senddataToserver(user.getUid());
                            //send to different activity
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Snackbar.make(findViewById(android.R.id.content), "Invalid Code",
                                        Snackbar.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    private void senddataToserver(String uid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id").append(AppPref.getInstance().getuserId());
        stringBuilder.append("&mobile_verify=").append("YES");
        stringBuilder.append("&user_uid=").append(uid);
        String content = stringBuilder.toString();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.UPDATE_MOBILE_VERIFY, 0, content, true, "Please wait..", new GetWebServiceData() {
            @Override
            public void getWebServiceResponse(String responseData, int serviceCounter) {
                AppPref.getInstance().setMobileVerify(true);
                Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        getDataUsingWService.execute();
    }


    private void signOut() {
        mAuth.signOut();
    }
}
