package com.colaborotech.thehinduwedlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.colaborotech.thehinduwedlock.R;
import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;
import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.utility.AppPref;
import com.colaborotech.thehinduwedlock.utility.AppUrls;
import com.colaborotech.thehinduwedlock.webservice.GetDataUsingWService;
import com.colaborotech.thehinduwedlock.webservice.GetWebServiceData;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by him on 27-May-17.
 */

public class SplashActivty extends AppCompatActivity implements GetWebServiceData {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getAlldata();
        getDeviceId();
    }

    private void getAlldata() {
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, AppUrls.GET_ALL_DATA, 0, "", false, "Please Wait", this);
        getDataUsingWService.execute();
    }


    private void getDeviceId() {
        AppPref.getInstance().setDeviceId("himanshu");
    }

    @Override
    public void getWebServiceResponse(String responseData, int serviceCounter) {
        Log.e("response", "is" + responseData);
        TheHinduWedLockApp.complexionModelList.clear();
        TheHinduWedLockApp.countryModelList.clear();
        TheHinduWedLockApp.languageModelList.clear();
        TheHinduWedLockApp.educationModelList.clear();
        TheHinduWedLockApp.maritalStausModelList.clear();
        TheHinduWedLockApp.haveClildModelList.clear();
        TheHinduWedLockApp.religionModelList.clear();
        TheHinduWedLockApp.manglikModelList.clear();
        TheHinduWedLockApp.horoscopeModelList.clear();
        TheHinduWedLockApp.familystatusModelList.clear();
        TheHinduWedLockApp.familyValuesModelList.clear();
        TheHinduWedLockApp.familyTypeModelList.clear();
        TheHinduWedLockApp.rashiModelList.clear();
        TheHinduWedLockApp.nakshatraModelList.clear();
        TheHinduWedLockApp.bodyTypeModelList.clear();
        TheHinduWedLockApp.weightModelList.clear();
        TheHinduWedLockApp.challengedModelList.clear();
        TheHinduWedLockApp.thalassemiaModelList.clear();
        TheHinduWedLockApp.hivModelList.clear();
        TheHinduWedLockApp.livingwithParents.clear();
        TheHinduWedLockApp.assetsModelList.clear();
        TheHinduWedLockApp.cityModelList.clear();
        TheHinduWedLockApp.stateModelList.clear();
        TheHinduWedLockApp.familyBorther_sisterModelList.clear();
        TheHinduWedLockApp.haveClildModelList.add(new DataModel(1, "No"));
        TheHinduWedLockApp.haveClildModelList.add(new DataModel(2, "Yes,Living Together"));
        TheHinduWedLockApp.haveClildModelList.add(new DataModel(3, "Yes,Living Separately"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(1, "Hindu"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(2, "Muslim"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(3, "Sikh"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(4, "Christian"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(5, "Buddhist"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(6, "Jain"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(7, "Parsi"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(8, "jewish"));
        TheHinduWedLockApp.religionModelList.add(new DataModel(9, "Bahai"));
        TheHinduWedLockApp.manglikModelList.add(new DataModel(1, "Manglik"));
        TheHinduWedLockApp.manglikModelList.add(new DataModel(2, "Non Manglik"));
        TheHinduWedLockApp.manglikModelList.add(new DataModel(3, "Angshik(partial manglik)"));
        TheHinduWedLockApp.horoscopeModelList.add(new DataModel(1, "Must"));
        TheHinduWedLockApp.horoscopeModelList.add(new DataModel(2, "Not Necessary"));
        TheHinduWedLockApp.familystatusModelList.add(new DataModel(1, "Rich/Affluent"));
        TheHinduWedLockApp.familystatusModelList.add(new DataModel(2, "Upper Middle Class"));
        TheHinduWedLockApp.familystatusModelList.add(new DataModel(3, "Middle Class"));
        TheHinduWedLockApp.familyValuesModelList.add(new DataModel(1, "Orthodox"));
        TheHinduWedLockApp.familyValuesModelList.add(new DataModel(2, "Conservative"));
        TheHinduWedLockApp.familyValuesModelList.add(new DataModel(3, "Moderate"));
        TheHinduWedLockApp.familyValuesModelList.add(new DataModel(4, "Liberal"));
        TheHinduWedLockApp.familyTypeModelList.add(new DataModel(1, "Joint Fammily"));
        TheHinduWedLockApp.familyTypeModelList.add(new DataModel(2, "Nuclear Family"));
        TheHinduWedLockApp.familyTypeModelList.add(new DataModel(3, "Others"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(1, "None"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(2, "1"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(3, "2"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(4, "3"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(5, "4"));
        TheHinduWedLockApp.familyBorther_sisterModelList.add(new DataModel(6, "4+"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(2, "Mesh"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(3, "Vrishabh"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(4, "Mithun"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(5, "Kark"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(6, "Simha"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(7, "Kanya"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(8, "Tula"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(9, "Vrishchick"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(10, "Dhanu"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(11, "Makar"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(12, "Kumbh"));
        TheHinduWedLockApp.rashiModelList.add(new DataModel(13, "Meen"));
        TheHinduWedLockApp.nakshatraModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.bodyTypeModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.weightModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.challengedModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.thalassemiaModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.hivModelList.add(new DataModel(1, "Don't Know"));
        TheHinduWedLockApp.livingwithParents.add(new DataModel(1, "Yes"));
        TheHinduWedLockApp.livingwithParents.add(new DataModel(2, "No"));
        TheHinduWedLockApp.livingwithParents.add(new DataModel(3, "Not Applicable"));
        TheHinduWedLockApp.assetsModelList.add(new DataModel(1, "Yes"));
        TheHinduWedLockApp.assetsModelList.add(new DataModel(2, "No"));


        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String response_code = jsonObject.getString("response_code");
            if (response_code.equalsIgnoreCase("200")) {
                JSONObject jsondata = jsonObject.getJSONObject("data");
                JSONArray categoryArray = jsondata.getJSONArray("category");
                for (int i = 0; i < categoryArray.length(); i++) {
                    JSONObject jsonObject1 = categoryArray.getJSONObject(i);
                    int category_id = jsonObject1.getInt("category_id");
                    String category_name = jsonObject1.getString("category");
                    TheHinduWedLockApp.categotyModelList.add(new DataModel(category_id, category_name));
                }
                JSONArray educationArray = jsondata.getJSONArray("education");
                for (int i = 0; i < educationArray.length(); i++) {
                    JSONObject jsonObject1 = educationArray.getJSONObject(i);
                    int category_id = jsonObject1.getInt("education_id");
                    String category_name = jsonObject1.getString("education");
                    String parents = jsonObject1.getString("education_dep");
                    TheHinduWedLockApp.educationModelList.add(new DataModel(category_id, category_name, parents));
                }
                JSONArray employeeArray = jsondata.getJSONArray("employee");
                for (int i = 0; i < employeeArray.length(); i++) {
                    JSONObject jsonObject1 = employeeArray.getJSONObject(i);
                    int category_id = jsonObject1.getInt("employee_id");
                    String category_name = jsonObject1.getString("employee");
                    TheHinduWedLockApp.occupationModelList.add(new DataModel(category_id, category_name));
                }
                JSONArray maritalArray = jsondata.getJSONArray("marital_status");
                for (int i = 0; i < maritalArray.length(); i++) {
                    JSONObject jsonObject1 = maritalArray.getJSONObject(i);
                    int category_id = jsonObject1.getInt("marital_id");
                    String category_name = jsonObject1.getString("marital");
                    TheHinduWedLockApp.maritalStausModelList.add(new DataModel(category_id, category_name));
                }
                JSONArray languageArray = jsondata.getJSONArray("language");
                for (int i = 0; i < languageArray.length(); i++) {
                    JSONObject jsonObject1 = languageArray.getJSONObject(i);
                    int language_id = jsonObject1.getInt("language_id");
                    String language_name = jsonObject1.getString("language");
                    TheHinduWedLockApp.languageModelList.add(new DataModel(language_id, language_name));
                }
                JSONArray incomeArray = jsondata.getJSONArray("income");
                for (int i = 0; i < incomeArray.length(); i++) {
                    JSONObject jsonObject1 = incomeArray.getJSONObject(i);
                    int income_id = jsonObject1.getInt("income_id");
                    String income_name = jsonObject1.getString("income");
                    TheHinduWedLockApp.incomeModelList.add(new DataModel(income_id, "Rs. " + income_name));
                }

                JSONArray complexionArray = jsondata.getJSONArray("complexion");
                for (int i = 0; i < complexionArray.length(); i++) {
                    JSONObject jsonObject1 = complexionArray.getJSONObject(i);
                    int complexion_id = jsonObject1.getInt("complexion_id");
                    String complexion_name = jsonObject1.getString("complexion");
                    TheHinduWedLockApp.complexionModelList.add(new DataModel(complexion_id, complexion_name));
                }
                JSONArray countryArray = jsondata.getJSONArray("country");
                for (int i = 0; i < countryArray.length(); i++) {
                    JSONObject jsonObject1 = countryArray.getJSONObject(i);
                    int country_id = jsonObject1.getInt("country_id");
                    String country_name = jsonObject1.getString("country");
                    TheHinduWedLockApp.countryModelList.add(new DataModel(country_id, country_name));
                }

                JSONArray stateArray = jsondata.getJSONArray("state");
                for (int i = 0; i < stateArray.length(); i++) {
                    JSONObject jsonObject1 = stateArray.getJSONObject(i);
                    int state_id = jsonObject1.getInt("state_id");
                    String country_id = jsonObject1.getString("country_id");
                    String state_name = jsonObject1.getString("state");
                    TheHinduWedLockApp.stateModelList.add(new DataModel(state_id, state_name, country_id));
                }

                JSONArray cityArray = jsondata.getJSONArray("city");
                for (int i = 0; i < cityArray.length(); i++) {
                    JSONObject jsonObject1 = cityArray.getJSONObject(i);
                    int city_id = jsonObject1.getInt("city_id");
                    String state_id = jsonObject1.getString("state_id");
                    String city_name = jsonObject1.getString("city");
                    TheHinduWedLockApp.cityModelList.add(new DataModel(city_id, city_name, state_id));
                }

                if (!AppPref.getInstance().getisLogin().equalsIgnoreCase("islogin")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                }


            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            Log.e("exception", "in All data service" + e.toString());
        }


    }
}
