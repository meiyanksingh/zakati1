package com.mks.zakati.utils;

import android.content.Context;

import com.dcs.clipazadmin.models.general.res.LoginRes;

import static com.dcs.clipazadmin.utils.PrefConstants.USER_ID;
import static com.dcs.clipazadmin.utils.PrefConstants.USER_TYPE;


/**
 * Created by rahil on 9/5/16.
 */
public class PrefMgr {

    private final PreferenceUtil mPref;
    private final Context mContext;

    private static PrefMgr instance;

    public static PrefMgr init(Context context) {
        instance = new PrefMgr(context);
        return instance;
    }

    public static PrefMgr get() {
        return instance;
    }


    private PrefMgr(Context context) {
        mContext = context;
        mPref = new PreferenceUtil(context);
    }

    public boolean isLoggedIn() {
        return mPref.getBooleanValue(PrefConstants.IS_LOGGED_IN);
    }


    public void clear() {
        mPref.clear();
    }

    public void clearAll() {
        mPref.clearAll();
    }

    public String getDeviceToken() {
        return PreferenceUtil.getDefaultSharedPrefernce(mContext).getString(PrefConstants.GCM_TOKEN, "");
    }

    public void setDeviceToken(String token) {
        PreferenceUtil.getDefaultSharedPrefernce(mContext).edit().putString(PrefConstants.GCM_TOKEN, token).commit();
    }

    public String getUserId() {
        return mPref.getStringValue(USER_ID);
    }

    public void setUserId(String id) {
        mPref.save(USER_ID, id);
    }

    public void setUserType(String type){
        mPref.save(USER_TYPE,type);
    }

    public String getUserType() {
        return mPref.getStringValue(USER_TYPE);
    }


    public void setIsLoggedIn(boolean b) {
        mPref.save(PrefConstants.IS_LOGGED_IN, b);
    }


    public String getUserFirstName() {
        return mPref.getStringValue(PrefConstants.USER_FIRST_NAME);
    }

    public void setUserName(String user_name) {
        mPref.save(PrefConstants.USER_NAME, user_name);
    }

    public String getEmail() {
        return mPref.getStringValue(PrefConstants.USER_EMAIL);
    }

    public void setEmail(String email) {
        mPref.save(PrefConstants.USER_EMAIL, email);
    }

    public String getUserImage() {
        return mPref.getStringValue(PrefConstants.USER_IMAGE);
    }

    public void setUserImage(String image) {
        mPref.save(PrefConstants.USER_IMAGE, image);
    }


    public void saveLoginData(LoginRes result) {

        mPref.save(USER_ID, result.getUserId());
        mPref.save(USER_TYPE,result.getUserType());
    }
}
