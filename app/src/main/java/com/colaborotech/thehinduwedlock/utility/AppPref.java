package com.colaborotech.thehinduwedlock.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.colaborotech.thehinduwedlock.TheHinduWedLockApp;


/**
 * Created by him on 27-May-17.
 */

public class AppPref {
    private static AppPref instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sEditor;
    private String SG_SHARED_PREFERENCE = "shared_preference1";

    //variable
    private final String PREF_USER_ID = "user_id";
    private final String PREF_IS_LOGIN = "islogin";
    private final String PREF_CREATE_FOR = "create_for";
    private final String PREF_GENDER = "gender";
    private final String PREF_DOB = "dob";
    private final String PREF_HEIGHT = "height";
    private final String PREF_COUNTRY = "country";
    private final String PREF_STATE = "state";
    private final String PREF_CITY = "city";
    private final String PREF_HIGHEST_EDU = "highest_edu";
    private final String PREF_UG_COLLEGE = "ug_college";
    private final String PREF_UG_DEGREE = "ug_degree";
    private final String PREF_PG_COLLEGE = "pg_college";
    private final String PREF_PG_DEGREE = "pg_degree";
    private final String PREF_WORK_AREA = "work_area";
    private final String PREF_INCOME = "income";
    private final String PREF_MARITAL_STATUS = "marital_status";
    private final String PREF_HAVE_CLILD = "have_clild";
    private final String PREF_MOTHER_TONGUE = "mother_tongue";
    private final String PREF_RELIGION = "religion";
    private final String PREF_CASTE = "caste";
    private final String PREF_MANGLIK = "manglik";
    private final String PREF_FULL_NAME = "name";
    private final String PREF_EMAIL_ID = "email_id";
    private final String PREF_PASSWORD = "password";
    private final String PREF_MOBILE = "mobile";
    private final String PREF_PHONE = "phone";
    private final String PREF_ABOUT_YOUR_SELF = "about_yourself";
    private final String PREF_ABOUT_EDUCATION = "about_education";
    private final String PREF_ABOUT_CAREER = "about_career";
    private final String PREF_ABOUT_FAMILY = "about_family";
    private final String PREF_SUBCASTE = "sub_caste";
    private final String PREF_GOTRA = "gotra";
    private final String PREF_FAMILY_BASED = "family_based";
    private final String PREF_COMPLEXION = "complexion";
    private final String PREF_BODY_TYPE = "body_type";
    private final String PREF_WEIGHT = "weight";
    private final String PREF_CHALLENGED = "challenged";
    private final String PREF_THALASSEMIA = "thalassemia";
    private final String PREF_HIV = "hiv";
    private final String PREF_SCHOOL_NAME = "school_name";
    private final String PREF_ORGANIZATION_NAME = "organization_name";
    private final String PREF_OCCUPATION = "occupation";
    private final String PREF_ANNUAL_INCOME = "income";
    private final String PREF_FAMILY_STATUS = "family_status";
    private final String PREF_FAMILY_VALUES = "family_values";
    private final String PREF_FAMILY_TYPE = "family_type";
    private final String PREF_LIVING_WITH_PARENTS = "living_with_parents";
    private final String PREF_FATHER_OCCUPATION = "father_occupation";
    private final String PREF_MOTHER_OCCUPATION = "mother_occupation";
    private final String PREF_FAMILY_INCOME = "family_income";
    private final String PREF_BROTHER = "brother";
    private final String PREF_MARRIED_BROTHER = "married_brother";
    private final String PREF_SISTER = "sister";
    private final String PREF_MARRIED_SISTER = "married_sister";
    private final String PREF_HOROSCOPE_MATCH = "horoscope_match";
    private final String PREF_RASHI = "rashi";
    private final String PREF_NAKSHATRA = "nakshatra";
    private final String PREF_DIET = "diet";
    private final String PREF_SMOKE = "smoke";
    private final String PREF_DRINK = "drink";
    private final String PREF_PETS = "pets";
    private final String PREF_OWN_CAR = "own_car";
    private final String PREF_OWN_HOUSE = "own_house";
    private final String PREF_LANGUAGE_SPEAK = "language_speak";
    private final String PREF_COOK_FOOD = "cook_food";
    private final String PREF_HOBBIES = "hobbies";
    private final String PREF_INTERESTS = "interests";
    private final String PREF_FAV_MUSIC = "fav_music";
    private final String PREF_FEV_BOOK = "fav_books";
    private final String PREF_DRESS_STYLE = "dress_style";
    private final String PREF_SPORTS = "sports";
    private final String PREF_CUISINE = "cuisine";
    private final String PREF_MOVIES = "movies";
    private final String PREF_FAV_READ = "fav_read";
    private final String PREF_TV_SHOWS = "tv_shows";
    private final String PREF_VAC_DESTINATION = "vac_destination";
    private final String PREF_ALT_EMAIL = "alt_email";
    private final String PREF_ALT_MOBILE = "alt_mobile";
    private final String PREF_MOBILE_VERIFY = "mobile_verify";
    private final String PREF_No_Of_IMAGE = "image_count";
    private final String PREF_IMAGE_URL = "image_urls";
    private final String PREF_SAVED_SEARCH = "saved_search";
    private final String PREF_REG_TOKEN = "reg_token";
    private final String PREF_HEIGHT_ID = "height_id";
    private final String PREF_COUNTRY_ID = "county_id";
    private final String PREF_STATE_ID = "state_id";
    private final String PREF_CITY_ID = "city_id";
    private final String PREF_HIGHEST_EDUCATION_ID = "highest_edu_id";
    private final String PREF_OCCUPATION_ID = "occupation_id";
    private final String PREF_INCOME_ID = "income_id";
    private final String PREF_MARITAL_STATUS_ID = "marital_status_id";
    private final String PREF_MOTHER_T0NGUE_ID = "mother_tongue_id";
    private final String PREF_RELIGIIN_ID = "religion_id";
    private final String PREF_CASTE_ID = "caste_id";
    private final String PREF_SUB_CASTE_ID = "sub_caste_id";
    private final String PREF_MANGLIK_ID = "manglik_id";
    private final String PREF_DEVICE_ID = "device_id";


    private AppPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SG_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        sEditor = sharedPreferences.edit();
    }

    public static AppPref getInstance() {
        if (instance == null) {
            synchronized (AppPref.class) {
                if (instance == null) {
                    instance = new AppPref(TheHinduWedLockApp.getInstance().getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void registerPre(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }


    public void unRegister(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }


    public boolean getisLogin() {
        return sharedPreferences.getBoolean(PREF_IS_LOGIN, false);
    }

    public void setIsLogin(boolean cindition) {
        sEditor.putBoolean(PREF_IS_LOGIN, cindition);
        sEditor.commit();
    }

    public String getuserId() {
        return sharedPreferences.getString(PREF_USER_ID, "");
    }

    public void setuserId(String userid) {
        sEditor.putString(PREF_USER_ID, userid);
        sEditor.commit();
    }

    public String getCreateFor() {
        return sharedPreferences.getString(PREF_CREATE_FOR, "");
    }

    public void setCreateFor(String createFor) {
        sEditor.putString(PREF_CREATE_FOR, createFor);
        sEditor.commit();
    }

    public String getGender() {
        return sharedPreferences.getString(PREF_GENDER, "");
    }

    public void setGender(String gender) {
        sEditor.putString(PREF_GENDER, gender);
        sEditor.commit();
    }

    public String getDob() {
        return sharedPreferences.getString(PREF_DOB, "");
    }

    public void setDob(String dob) {
        sEditor.putString(PREF_DOB, dob);
        sEditor.commit();
    }


    public String getHeight() {
        return sharedPreferences.getString(PREF_HEIGHT, "");
    }

    public void setHeight(String height) {
        sEditor.putString(PREF_HEIGHT, height);
        sEditor.commit();
    }

    public String getCountry() {
        return sharedPreferences.getString(PREF_COUNTRY, "");
    }

    public void setCountry(String contry) {
        sEditor.putString(PREF_COUNTRY, contry);
        sEditor.commit();
    }

    public String getState() {
        return sharedPreferences.getString(PREF_STATE, "");
    }

    public void setState(String state) {
        sEditor.putString(PREF_STATE, state);
        sEditor.commit();
    }

    public String getCity() {
        return sharedPreferences.getString(PREF_CITY, "");
    }

    public void setCity(String city) {
        sEditor.putString(PREF_CITY, city);
        sEditor.commit();
    }

    public String getHighestEducation() {
        return sharedPreferences.getString(PREF_HIGHEST_EDU, "");
    }

    public void setHighestEducation(String highestEducation) {
        sEditor.putString(PREF_HIGHEST_EDU, highestEducation);
        sEditor.commit();
    }

    public String getUgCollege() {
        return sharedPreferences.getString(PREF_UG_COLLEGE, "");
    }

    public void setUgCollege(String ugCollege) {
        sEditor.putString(PREF_UG_COLLEGE, ugCollege);
        sEditor.commit();
    }

    public String getUgDegree() {
        return sharedPreferences.getString(PREF_UG_DEGREE, "");
    }

    public void setUgDegree(String ugDegree) {
        sEditor.putString(PREF_UG_DEGREE, ugDegree);
        sEditor.commit();
    }


    public String getPgCollge() {
        return sharedPreferences.getString(PREF_PG_COLLEGE, "");
    }

    public void setPgCollge(String pgCollge) {
        sEditor.putString(PREF_PG_COLLEGE, pgCollge);
        sEditor.commit();
    }

    public String getPgDegree() {
        return sharedPreferences.getString(PREF_PG_DEGREE, "");
    }

    public void setPgDegree(String pgDegree) {
        sEditor.putString(PREF_PG_DEGREE, pgDegree);
        sEditor.commit();
    }


    public String getWorkArea() {
        return sharedPreferences.getString(PREF_WORK_AREA, "");
    }

    public void setWorkArea(String workArea) {
        sEditor.putString(PREF_WORK_AREA, workArea);
        sEditor.commit();
    }

    public String getIncome() {
        return sharedPreferences.getString(PREF_INCOME, "");
    }

    public void setIncome(String income) {
        sEditor.putString(PREF_INCOME, income);
        sEditor.commit();
    }

    public String getMaritalStatus() {
        return sharedPreferences.getString(PREF_MARITAL_STATUS, "");
    }

    public void setMaritalStatus(String maritaStatus) {
        sEditor.putString(PREF_MARITAL_STATUS, maritaStatus);
        sEditor.commit();
    }

    public String getHaveChild() {
        return sharedPreferences.getString(PREF_HAVE_CLILD, "");
    }

    public void setHaveChild(String child) {
        sEditor.putString(PREF_HAVE_CLILD, child);
        sEditor.commit();
    }

    public String getMotherTongue() {
        return sharedPreferences.getString(PREF_MOTHER_TONGUE, "");
    }

    public void setMotherTongue(String motherTongue) {
        sEditor.putString(PREF_MOTHER_TONGUE, motherTongue);
        sEditor.commit();
    }

    public String getReligion() {
        return sharedPreferences.getString(PREF_RELIGION, "");
    }

    public void setReligion(String religion) {
        sEditor.putString(PREF_RELIGION, religion);
        sEditor.commit();
    }


    public String getCaste() {
        return sharedPreferences.getString(PREF_CASTE, "");
    }

    public void setCaste(String caste) {
        sEditor.putString(PREF_CASTE, caste);
        sEditor.commit();
    }


    public String getManglik() {
        return sharedPreferences.getString(PREF_MANGLIK, "");
    }

    public void setManglik(String manglik) {
        sEditor.putString(PREF_MANGLIK, manglik);
        sEditor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(PREF_FULL_NAME, "");
    }

    public void setName(String name) {
        sEditor.putString(PREF_FULL_NAME, name);
        sEditor.commit();
    }

    public String getPassword() {
        return sharedPreferences.getString(PREF_PASSWORD, "");
    }

    public void setPassword(String password) {
        sEditor.putString(PREF_PASSWORD, password);
        sEditor.commit();
    }

    public String getMobile() {
        return sharedPreferences.getString(PREF_MOBILE, "");
    }

    public void setMobile(String mobile) {
        sEditor.putString(PREF_MOBILE, mobile);
        sEditor.commit();
    }

    public String getPhone() {
        return sharedPreferences.getString(PREF_PHONE, "");
    }

    public void setPhone(String phone) {
        sEditor.putString(PREF_PHONE, phone);
        sEditor.commit();
    }

    public String getEmailId() {
        return sharedPreferences.getString(PREF_EMAIL_ID, "");
    }

    public void setEmailId(String emailId) {
        sEditor.putString(PREF_EMAIL_ID, emailId);
        sEditor.commit();
    }

    public String getAboutYourSelf() {
        return sharedPreferences.getString(PREF_ABOUT_YOUR_SELF, "");
    }

    public void setAboutYourSelf(String aboutYourself) {
        sEditor.putString(PREF_ABOUT_YOUR_SELF, aboutYourself);
        sEditor.commit();
    }

    public String getAboutEdu() {
        return sharedPreferences.getString(PREF_ABOUT_EDUCATION, "");
    }

    public void setAboutEdu(String aboutEdu) {
        sEditor.putString(PREF_ABOUT_EDUCATION, aboutEdu);
        sEditor.commit();
    }

    public String getAboutCareer() {
        return sharedPreferences.getString(PREF_ABOUT_CAREER, "");
    }

    public void setAboutCareer(String aboutCareer) {
        sEditor.putString(PREF_ABOUT_CAREER, aboutCareer);
        sEditor.commit();
    }

    public String getAboutFamily() {
        return sharedPreferences.getString(PREF_ABOUT_FAMILY, "");
    }

    public void setAboutFamily(String aboutFamily) {
        sEditor.putString(PREF_ABOUT_FAMILY, aboutFamily);
        sEditor.commit();
    }

    public String getSubCaste() {
        return sharedPreferences.getString(PREF_SUBCASTE, "");
    }

    public void setSubCaste(String subcaste) {
        sEditor.putString(PREF_SUBCASTE, subcaste);
        sEditor.commit();
    }

    public String getGotra() {
        return sharedPreferences.getString(PREF_GOTRA, "");
    }

    public void setGotra(String gotra) {
        sEditor.putString(PREF_GOTRA, gotra);
        sEditor.commit();
    }

    public String getFamilyBased() {
        return sharedPreferences.getString(PREF_FAMILY_BASED, "");
    }

    public void setFamilyBased(String familyBased) {
        sEditor.putString(PREF_FAMILY_BASED, familyBased);
        sEditor.commit();
    }

    public String getComplexion() {
        return sharedPreferences.getString(PREF_COMPLEXION, "");
    }

    public void setComplexion(String complexion) {
        sEditor.putString(PREF_COMPLEXION, complexion);
        sEditor.commit();
    }

    public String getBodyType() {
        return sharedPreferences.getString(PREF_BODY_TYPE, "");
    }

    public void setBodyType(String bodyType) {
        sEditor.putString(PREF_BODY_TYPE, bodyType);
        sEditor.commit();
    }

    public String getWeight() {
        return sharedPreferences.getString(PREF_WEIGHT, "");
    }

    public void setWeight(String weight) {
        sEditor.putString(PREF_WEIGHT, weight);
        sEditor.commit();
    }

    public String getChallenged() {
        return sharedPreferences.getString(PREF_CHALLENGED, "");
    }

    public void setChallenged(String challenged) {
        sEditor.putString(PREF_CHALLENGED, challenged);
        sEditor.commit();
    }

    public String getThalassemia() {
        return sharedPreferences.getString(PREF_THALASSEMIA, "");
    }

    public void setThalassemia(String thalassemia) {
        sEditor.putString(PREF_THALASSEMIA, thalassemia);
        sEditor.commit();
    }

    public String getHIV() {
        return sharedPreferences.getString(PREF_HIV, "");
    }

    public void setHiv(String hiv) {
        sEditor.putString(PREF_HIV, hiv);
        sEditor.commit();
    }

    public String getOrganizationName() {
        return sharedPreferences.getString(PREF_ORGANIZATION_NAME, "");
    }

    public void setOrganizationName(String organizationName) {
        sEditor.putString(PREF_ORGANIZATION_NAME, organizationName);
        sEditor.commit();
    }

    public String getOccupation() {
        return sharedPreferences.getString(PREF_OCCUPATION, "");
    }

    public void setOccupation(String occupation) {
        sEditor.putString(PREF_OCCUPATION, occupation);
        sEditor.commit();
    }

    public String getSchoolName() {
        return sharedPreferences.getString(PREF_SCHOOL_NAME, "");
    }

    public void setSchoolName(String schoolName) {
        sEditor.putString(PREF_SCHOOL_NAME, schoolName);
        sEditor.commit();
    }

    public String getAnnualIncome() {
        return sharedPreferences.getString(PREF_ANNUAL_INCOME, "");
    }

    public void setAnnualIncome(String annualIncome) {
        sEditor.putString(PREF_ANNUAL_INCOME, annualIncome);
        sEditor.commit();
    }

    public String getFamilyStatus() {
        return sharedPreferences.getString(PREF_FAMILY_STATUS, "");
    }

    public void setFamilyStatus(String familyStatus) {
        sEditor.putString(PREF_FAMILY_STATUS, familyStatus);
        sEditor.commit();
    }

    public String getFamilyValues() {
        return sharedPreferences.getString(PREF_FAMILY_VALUES, "");
    }

    public void setFamilyValues(String familyValues) {
        sEditor.putString(PREF_FAMILY_VALUES, familyValues);
        sEditor.commit();
    }

    public String getFamilyType() {
        return sharedPreferences.getString(PREF_FAMILY_TYPE, "");
    }

    public void setFamilyType(String annualIncome) {
        sEditor.putString(PREF_FAMILY_TYPE, annualIncome);
        sEditor.commit();
    }

    public String getlivingWithParents() {
        return sharedPreferences.getString(PREF_LIVING_WITH_PARENTS, "");
    }

    public void setlivingWithParents(String livingWithParents) {
        sEditor.putString(PREF_LIVING_WITH_PARENTS, livingWithParents);
        sEditor.commit();
    }


    public void setFatherOccupation(String fatherOccupation) {
        sEditor.putString(PREF_FATHER_OCCUPATION, fatherOccupation);
        sEditor.commit();
    }

    public String getFatherOccupation() {
        return sharedPreferences.getString(PREF_FATHER_OCCUPATION, "");
    }

    public void setMotherOccupation(String motherOccupation) {
        sEditor.putString(PREF_MOTHER_OCCUPATION, motherOccupation);
        sEditor.commit();
    }

    public String getMotherOccupation() {
        return sharedPreferences.getString(PREF_MOTHER_OCCUPATION, "");
    }

    public String getFamilyIncome() {
        return sharedPreferences.getString(PREF_FAMILY_INCOME, "");
    }

    public void setFamilyIncome(String familyincome) {
        sEditor.putString(PREF_FAMILY_INCOME, familyincome);
        sEditor.commit();
    }

    public String getBrother() {
        return sharedPreferences.getString(PREF_BROTHER, "");
    }

    public void setBrother(String brother) {
        sEditor.putString(PREF_BROTHER, brother);
        sEditor.commit();
    }

    public String getMarriedBrother() {
        return sharedPreferences.getString(PREF_MARRIED_BROTHER, "");
    }

    public void setMarriedBrother(String marriedBrother) {
        sEditor.putString(PREF_MARRIED_BROTHER, marriedBrother);
        sEditor.commit();
    }

    public String getSister() {
        return sharedPreferences.getString(PREF_SISTER, "");
    }

    public void setSister(String sister) {
        sEditor.putString(PREF_SISTER, sister);
        sEditor.commit();
    }

    public String getMarriedSister() {
        return sharedPreferences.getString(PREF_MARRIED_SISTER, "");
    }

    public void setMarriedSister(String marriedSister) {
        sEditor.putString(PREF_MARRIED_SISTER, marriedSister);
        sEditor.commit();
    }


    public String getHoroscopeMatch() {
        return sharedPreferences.getString(PREF_HOROSCOPE_MATCH, "");
    }

    public void setHoroscopeMatch(String horoscopeMatch) {
        sEditor.putString(PREF_HOROSCOPE_MATCH, horoscopeMatch);
        sEditor.commit();
    }

    public String getRashi() {
        return sharedPreferences.getString(PREF_RASHI, "");
    }

    public void setRashi(String rashi) {
        sEditor.putString(PREF_RASHI, rashi);
        sEditor.commit();
    }

    public String getDiet() {
        return sharedPreferences.getString(PREF_DIET, "");
    }

    public void setDiet(String diet) {
        sEditor.putString(PREF_DIET, diet);
        sEditor.commit();
    }

    public String getSmoke() {
        return sharedPreferences.getString(PREF_SMOKE, "");
    }

    public void setSmoke(String smoke) {
        sEditor.putString(PREF_SMOKE, smoke);
        sEditor.commit();
    }

    public String getDrink() {
        return sharedPreferences.getString(PREF_DRINK, "");
    }

    public void setDrink(String drink) {
        sEditor.putString(PREF_DRINK, drink);
        sEditor.commit();
    }

    public String getpets() {
        return sharedPreferences.getString(PREF_PETS, "");
    }

    public void setPets(String pets) {
        sEditor.putString(PREF_PETS, pets);
        sEditor.commit();
    }

    public String getNakshatra() {
        return sharedPreferences.getString(PREF_NAKSHATRA, "");
    }

    public void setNakshatra(String nakshatra) {
        sEditor.putString(PREF_NAKSHATRA, nakshatra);
        sEditor.commit();
    }

    public int getNoOfImage() {
        return sharedPreferences.getInt(PREF_No_Of_IMAGE, 0);
    }

    public void setNoOfImage(int imageNo) {
        sEditor.putInt(PREF_No_Of_IMAGE, imageNo);
        sEditor.commit();
    }

    public String getOwnCar() {
        return sharedPreferences.getString(PREF_OWN_CAR, "");
    }

    public void setOwnCar(String ownCar) {
        sEditor.putString(PREF_OWN_CAR, ownCar);
        sEditor.commit();
    }

    public String getOwnHouse() {
        return sharedPreferences.getString(PREF_OWN_HOUSE, "");
    }

    public void setOwnHouse(String ownHouse) {
        sEditor.putString(PREF_OWN_HOUSE, ownHouse);
        sEditor.commit();
    }

    public String getLanguageSpeak() {
        return sharedPreferences.getString(PREF_LANGUAGE_SPEAK, "");
    }

    public void setLanguageSpeak(String languageSpeak) {
        sEditor.putString(PREF_LANGUAGE_SPEAK, languageSpeak);
        sEditor.commit();
    }

    public String getFoodCook() {
        return sharedPreferences.getString(PREF_COOK_FOOD, "");
    }

    public void setFoodCook(String foodCook) {
        sEditor.putString(PREF_COOK_FOOD, foodCook);
        sEditor.commit();
    }

    public String getHobbies() {
        return sharedPreferences.getString(PREF_HOBBIES, "");
    }

    public void setHobbies(String hobbies) {
        sEditor.putString(PREF_HOBBIES, hobbies);
        sEditor.commit();
    }

    public String getInterest() {
        return sharedPreferences.getString(PREF_INTERESTS, "");
    }

    public void setInterest(String interset) {
        sEditor.putString(PREF_INTERESTS, interset);
        sEditor.commit();
    }

    public String getFavMusic() {
        return sharedPreferences.getString(PREF_FAV_MUSIC, "");
    }

    public void setFavMusic(String favMusic) {
        sEditor.putString(PREF_FAV_MUSIC, favMusic);
        sEditor.commit();
    }

    public String getFavBook() {
        return sharedPreferences.getString(PREF_FEV_BOOK, "");
    }

    public void setFavBook(String favBook) {
        sEditor.putString(PREF_FEV_BOOK, favBook);
        sEditor.commit();
    }

    public String getDressStyle() {
        return sharedPreferences.getString(PREF_DRESS_STYLE, "");
    }

    public void setDressStyle(String dressStyle) {
        sEditor.putString(PREF_DRESS_STYLE, dressStyle);
        sEditor.commit();
    }

    public String getSports() {
        return sharedPreferences.getString(PREF_SPORTS, "");
    }

    public void setSports(String sports) {
        sEditor.putString(PREF_SPORTS, sports);
        sEditor.commit();
    }

    public String getCuisine() {
        return sharedPreferences.getString(PREF_CUISINE, "");
    }

    public void setCuisine(String cuisine) {
        sEditor.putString(PREF_CUISINE, cuisine);
        sEditor.commit();
    }

    public String getMovies() {
        return sharedPreferences.getString(PREF_MOVIES, "");
    }

    public void setMovies(String movies) {
        sEditor.putString(PREF_MOVIES, movies);
        sEditor.commit();
    }

    public String getFavRead() {
        return sharedPreferences.getString(PREF_FAV_READ, "");
    }

    public void setFavRead(String favRead) {
        sEditor.putString(PREF_FAV_READ, favRead);
        sEditor.commit();
    }

    public String getTVShows() {
        return sharedPreferences.getString(PREF_TV_SHOWS, "");
    }

    public void setTVShows(String tvShows) {
        sEditor.putString(PREF_TV_SHOWS, tvShows);
        sEditor.commit();
    }

    public String getVacationDestination() {
        return sharedPreferences.getString(PREF_VAC_DESTINATION, "");
    }

    public void setVacationDestination(String vacationDestination) {
        sEditor.putString(PREF_VAC_DESTINATION, vacationDestination);
        sEditor.commit();
    }

    public String getAltEmail() {
        return sharedPreferences.getString(PREF_ALT_EMAIL, "");
    }

    public void setAltEmail(String altEmail) {
        sEditor.putString(PREF_ALT_EMAIL, altEmail);
        sEditor.commit();
    }

    public String getAltMobile() {
        return sharedPreferences.getString(PREF_ALT_MOBILE, "");
    }

    public void setAltMobile(String altMobile) {
        sEditor.putString(PREF_ALT_MOBILE, altMobile);
        sEditor.commit();
    }

    public boolean getMobileVerify() {
        return sharedPreferences.getBoolean(PREF_MOBILE_VERIFY, false);
    }

    public void setMobileVerify(boolean mobileverify) {
        sEditor.putBoolean(PREF_MOBILE_VERIFY, mobileverify);
        sEditor.commit();
    }

    public String getImageUrls() {
        return sharedPreferences.getString(PREF_IMAGE_URL, "");
    }

    public void setImageUrls(String imageUrls) {
        sEditor.putString(PREF_IMAGE_URL, imageUrls);
        sEditor.commit();
    }

    public String getSavedSearch() {
        return sharedPreferences.getString(PREF_SAVED_SEARCH, "");
    }

    public void setSavedSearch(String data) {
        sEditor.putString(PREF_SAVED_SEARCH, data);
        sEditor.commit();
    }

    public String getRegToken() {
        return sharedPreferences.getString(PREF_REG_TOKEN, "");
    }

    public void setRegToken(String token_data) {
        sEditor.putString(PREF_REG_TOKEN, token_data);
        sEditor.commit();
    }


    public int getHeightId() {
        return sharedPreferences.getInt(PREF_HEIGHT_ID, -99);
    }

    public void setHeightId(int height_id) {
        sEditor.putInt(PREF_HEIGHT_ID, height_id);
        sEditor.commit();
    }

    public int getCounrtyId() {
        return sharedPreferences.getInt(PREF_COUNTRY_ID, -99);
    }

    public void setCounrtyId(int country_id) {
        sEditor.putInt(PREF_COUNTRY_ID, country_id);
        sEditor.commit();
    }

    public int getStateId() {
        return sharedPreferences.getInt(PREF_STATE_ID, -99);
    }

    public void setStateId(int stateId) {
        sEditor.putInt(PREF_STATE_ID, stateId);
        sEditor.commit();
    }

    public int getCityId() {
        return sharedPreferences.getInt(PREF_CITY_ID, -99);
    }

    public void setCityId(int cityId) {
        sEditor.putInt(PREF_CITY_ID, cityId);
        sEditor.commit();
    }

    public int getHighestEduId() {
        return sharedPreferences.getInt(PREF_HIGHEST_EDUCATION_ID, -99);
    }

    public void setHighestEduId(int highestEduId) {
        sEditor.putInt(PREF_HIGHEST_EDUCATION_ID, highestEduId);
        sEditor.commit();
    }

    public int getOccupdationId() {
        return sharedPreferences.getInt(PREF_OCCUPATION_ID, -99);
    }

    public void setOccupdationId(int occupdationId) {
        sEditor.putInt(PREF_OCCUPATION_ID, occupdationId);
        sEditor.commit();
    }

    public int getIncomeId() {
        return sharedPreferences.getInt(PREF_INCOME_ID, -99);
    }

    public void setIncomeId(int incomeId) {
        sEditor.putInt(PREF_INCOME_ID, incomeId);
        sEditor.commit();
    }

    public int getMaritalStatusId() {
        return sharedPreferences.getInt(PREF_MARITAL_STATUS_ID, -99);
    }

    public void setMaritalStatusId(int maritalStatusId) {
        sEditor.putInt(PREF_MARITAL_STATUS_ID, maritalStatusId);
        sEditor.commit();
    }

    public int getMotherTongueId() {
        return sharedPreferences.getInt(PREF_MOTHER_T0NGUE_ID, -99);
    }

    public void setMotherTongueId(int motherTongueId) {
        sEditor.putInt(PREF_MOTHER_T0NGUE_ID, motherTongueId);
        sEditor.commit();
    }

    public int getReligionId() {
        return sharedPreferences.getInt(PREF_RELIGIIN_ID, -99);
    }

    public void setReligionId(int religionId) {
        sEditor.putInt(PREF_RELIGIIN_ID, religionId);
        sEditor.commit();
    }

    public int getCasteId() {
        return sharedPreferences.getInt(PREF_CASTE_ID, -99);
    }

    public void setCasteId(int casteId) {
        sEditor.putInt(PREF_CASTE_ID, casteId);
        sEditor.commit();
    }

    public int getSubCasteId() {
        return sharedPreferences.getInt(PREF_SUB_CASTE_ID, -99);
    }

    public void setSubCasteId(int subCasteId) {
        sEditor.putInt(PREF_SUB_CASTE_ID, subCasteId);
        sEditor.commit();
    }

    public int getManglikId() {
        return sharedPreferences.getInt(PREF_MANGLIK_ID, -99);
    }

    public void setManglikId(int manglikId) {
        sEditor.putInt(PREF_MANGLIK_ID, manglikId);
        sEditor.commit();
    }

    public String getDeviceId() {
        return sharedPreferences.getString(PREF_DEVICE_ID, "");
    }

    public void setDeviceId(String deviceId) {
        sEditor.putString(PREF_DEVICE_ID, deviceId);
        sEditor.commit();
    }


}

