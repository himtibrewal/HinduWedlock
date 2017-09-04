package com.colaborotech.thehinduwedlock;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.colaborotech.thehinduwedlock.models.DataModel;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


/**
 * Created by him on 27-May-17.
 */

public class TheHinduWedLockApp extends Application {

    public static List<DataModel> complexionModelList = new ArrayList<DataModel>();
    public static List<DataModel> countryModelList = new ArrayList<DataModel>();
    public static List<DataModel> ageModelList = new ArrayList<DataModel>();
    public static List<DataModel> heightModelList = new ArrayList<DataModel>();
    public static List<DataModel> languageModelList = new ArrayList<DataModel>();
    public static List<DataModel> incomeModelList = new ArrayList<DataModel>();
    public static List<DataModel> categotyModelList = new ArrayList<DataModel>();
    public static List<DataModel> stateModelList = new ArrayList<DataModel>();
    public static List<DataModel> cityModelList = new ArrayList<DataModel>();
    public static List<DataModel> educationModelList = new ArrayList<DataModel>();
    public static List<DataModel> maritalStausModelList = new ArrayList<DataModel>();
    public static List<DataModel> haveClildModelList = new ArrayList<DataModel>();
    public static List<DataModel> religionModelList = new ArrayList<DataModel>();
    public static List<DataModel> casteModelList = new ArrayList<DataModel>();
    public static List<DataModel> manglikModelList = new ArrayList<DataModel>();
    public static List<DataModel> horoscopeModelList = new ArrayList<DataModel>();
    public static List<DataModel> occupationModelList = new ArrayList<DataModel>();
    public static List<DataModel> familystatusModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyValuesModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyTypeModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyincomeModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyFatherOccupatationModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyMotherOccupationModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyBorther_sisterModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyBorther_sisterMarridModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyGotraModelList = new ArrayList<DataModel>();
    public static List<DataModel> rashiModelList = new ArrayList<DataModel>();
    public static List<DataModel> nakshatraModelList = new ArrayList<DataModel>();
    public static List<DataModel> bodyTypeModelList = new ArrayList<DataModel>();
    public static List<DataModel> weightModelList = new ArrayList<DataModel>();
    public static List<DataModel> challengedModelList = new ArrayList<DataModel>();
    public static List<DataModel> thalassemiaModelList = new ArrayList<DataModel>();
    public static List<DataModel> hivModelList = new ArrayList<DataModel>();
    public static List<DataModel> livingwithParents = new ArrayList<DataModel>();
    public static List<DataModel> assetsModelList = new ArrayList<DataModel>();
    public static List<DataModel> familyTempModelList = new ArrayList<DataModel>(); //delete when  complete  family  detail
    private static TheHinduWedLockApp theHinduWedLockApp;
    private StorageReference mStorageRef;

    public static synchronized TheHinduWedLockApp getInstance() {
        return theHinduWedLockApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        theHinduWedLockApp = this;
        // super.onCreate();
        MultiDex.install(this);
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://thehindu-24e87.appspot.com");
        for (int i = 18; i < 70; i++) {
            ageModelList.add(new DataModel(i - 18, i + " Years"));
        }
        int p = 0;
        for (int i = 4; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                String age = i + "\'" + j + "\"";
                heightModelList.add(new DataModel(p, age));
                p++;
            }
        }
        for (int i = 1; i < 10; i++) {
            casteModelList.add(new DataModel(i, "caste" + 1));

            familyTempModelList.add(new DataModel(i, "TempData" + i));

        }


    }

    public StorageReference getmStorageRef() {
        return mStorageRef;
    }

    public void setmStorageRef(StorageReference mStorageRef) {
        this.mStorageRef = mStorageRef;
    }

    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


}



