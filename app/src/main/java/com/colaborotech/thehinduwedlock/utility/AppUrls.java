package com.colaborotech.thehinduwedlock.utility;

/**
 * Created by him on 27-May-17.
 */

public class AppUrls {
    public static final String BASE_URL_ADMIN = "http://192.168.0.102:2000/apiadmin/";
    //public static final String BASE_URL = "http://192.168.0.107:3000/api/";
    public static final String BASE_URL = "https://thehinduwedlock.herokuapp.com/api/";
    public static final String UPDATE_URL = "https://thehinduwedlock.herokuapp.com/update/";
    public static final String GET_ALL_DATA = BASE_URL + "alldata";
    //login_register
    public static final String LOGIN = BASE_URL + "user_login"; //
    public static final String REGISTER_URL = BASE_URL + "register_user";
    public static final String COUNT_DATA = BASE_URL + "get_count";

    //update Url
    public static final String UPDATE_ABOUT_YOURSELF = UPDATE_URL + "update_your_self";
    public static final String UPDATE_FAMILY_DETAIL = UPDATE_URL + "update_family_detail";
    public static final String UPDATE_ABOUT_FAMILY = UPDATE_URL + "update_about_family";
    public static final String UPDATE_MOBILE_VERIFY = UPDATE_URL + "mobile_verify";
    public static final String UPDATE_IMAGE = UPDATE_URL + "image_upload";
    public static final String UPDATE_ETHNICITY = UPDATE_URL + "update_ethnicity";
    public static final String UPDATE_APPEARANCE = UPDATE_URL + "update_appearance";
    public static final String UPDATE_SPCL_CASE = UPDATE_URL + "update_spclcase";
    public static final String UPDATE_KUNDLI = UPDATE_URL + "update_kundli";
    public static final String UPDATE_ABOUT_EDUCATION = UPDATE_URL + "update_about_edu";
    public static final String UPDATE_COLLEGE = UPDATE_URL + "update_college";
    public static final String UPDATE_TOKEN = UPDATE_URL + "update_token";
    //user list
    public static final String USER_LIST = BASE_URL + "userlist";
    //send
    public static final String SEND_INTEREST = BASE_URL + "send_interest";
    public static final String SEND_SHORTLIST = BASE_URL + "send_shortlist";
    public static final String SEND_BLOCK = BASE_URL + "send_block";
    // interest
    public static final String GET_INTEREST_SENT = BASE_URL + "get_interest_sent";
    public static final String GET_INTEREST_RECEIVED = BASE_URL + "get_interest_received";
    public static final String INTEREST_ACCEPT_REJECT = BASE_URL + "accept_decline_interest";
    public static final String DELETE_INTEREST = BASE_URL + "delete_interest";
    public static final String I_DECLINED = BASE_URL + "get_i_declined";
    public static final String THEY_DECLINED = BASE_URL + "get_they_declined";
    public static final String SEND_REMINDER = BASE_URL + "send_reminder";
    //accept
    public static final String ACCEPTED_BY_ME = BASE_URL + "get_accepted_by_me";
    public static final String ACCEPTED_ME = BASE_URL + "get_accepted_me";
    //get Info
    public static final String CONTACT_DETAIL = BASE_URL + "get_contact_detail";
    //full detail
    public static final String USER_FULL_DETAIL = BASE_URL + "get_user_detail";
    //blok
    public static final String BLOCKED_USER_LIST = BASE_URL + "block_user_list";
    //image
    public static final String MAKE_PROFILE_PIC = UPDATE_URL + "make_profile";
    public static final String DELETE_IMAGE = UPDATE_URL + "delete_image";
    public static final String ALL_IMAGE = UPDATE_URL + "all_image";


}
