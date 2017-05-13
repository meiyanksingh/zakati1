package com.mks.zakati.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by Rahil on 10/9/15.
 */
public class PreferenceUtil {
    private Context mContext;
    private String TAG = PreferenceUtil.class.getSimpleName();

    private SharedPreferences mSpref;

    public static final String GLOBAL_SHARED_PREF_NAME = "your_app_global_shared_pref";
    public static final String SHARED_PREF_NAME = "your_app_shared_pref";


    //public static final String KEY_GCM_REGISTRATION_ID="gcm_reg_id";

    public void save(String key, String value) {
        SharedPreferences.Editor editor = mSpref.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public void save(String key, boolean value) {
        SharedPreferences.Editor editor = mSpref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void save(String key, float value) {
        SharedPreferences.Editor editor = mSpref.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void save(String key, int value) {
        SharedPreferences.Editor editor = mSpref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void save(String key, long value) {
        SharedPreferences.Editor editor = mSpref.edit();
        editor.putLong(key, value);
        editor.apply();
    }


    public String getStringValue(String key) {
        // if (mSpref.contains(key))
        return mSpref.getString(key, "");
       /* else
            Lg.e(TAG, "KEY NOT FOUND");

        return null;*/
    }

    public static SharedPreferences getDefaultSharedPrefernce(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void clearDefaultPref(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();
    }

    public boolean getBooleanValue(String key) {
        //  if (mSpref.contains(key))
        return mSpref.getBoolean(key, false);
        /*else
            Lg.e(TAG, "KEY NOT FOUND");

        return false;*/
    }

    public float getFloatValue(String key) {
        if (mSpref.contains(key))
            return mSpref.getFloat(key, 0f);
        else
            Lg.e(TAG, "KEY NOT FOUND");

        return 0f;
    }

    public long getLongValue(String key) {
        if (mSpref.contains(key))
            return mSpref.getLong(key, 0l);
        else
            Lg.e(TAG, "KEY NOT FOUND");

        return 0l;
    }

    public int getIntValue(String key) {
        if (mSpref.contains(key))
            return mSpref.getInt(key, 0);
        else
            Lg.e(TAG, "KEY NOT FOUND");

        return 0;
    }

    public void clear() {
        mSpref.edit().clear().apply();
    }

    public void clearAll() {
        mSpref.edit().clear().apply();
        mContext.getSharedPreferences(GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply();
    }


    public PreferenceUtil(Context context) {
        mContext = context;
        mSpref = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return mSpref;
    }

    // for global value
    public static long getGlobalPrefLong(Context context, String key) {
        SharedPreferences userAcountPreference = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return userAcountPreference.getLong(key, 0);

    }

    public static void setGlobalPref(Context context, String key, long value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putLong(key, value);
        appInstallInfoEditor.apply();
    }

    public static boolean getGlobalPrefBoolean(Context context, String key) {

        SharedPreferences userAcountPreference = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return userAcountPreference.getBoolean(key, false);

    }

    public static void setGlobalPref(Context context, String key, boolean value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putBoolean(key, value);
        appInstallInfoEditor.apply();
    }

    public static void setGlobalPref(Context context, String key, String value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }

    public static String getGlobalPrefString(Context context, String key) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String res = appInstallInfoSharedPref.getString(key, "");
        return res;
    }

    public static int getGlobalPrefInt(Context context, String key, int defValue) {
        SharedPreferences userAcountPreference = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return userAcountPreference.getInt(key, defValue);
    }

    public static void setGlobalPref(Context context, String key, int value) {
        SharedPreferences appInstallInfoSharedPref = context.getSharedPreferences(
                GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putInt(key, value);
        appInstallInfoEditor.apply();
    }

    /* clear all shared prefrence of wallet and retailer */
    public static void clearGlobalPreferences(Context context) {
        SharedPreferences appInstallInfoSharedPref = context
                .getSharedPreferences(GLOBAL_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.clear();
        appInstallInfoEditor.apply();

    }


}
