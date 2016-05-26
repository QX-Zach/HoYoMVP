package com.example.ozner.hoyomvp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public class HoYoPreference {
    public final static String HoYo = "hoyomvp";
    public final static String UserToken = "usertoken";
    public final static String UserID = "userid";

    private static SharedPreferences Init(Context context) {
        if (context != null)
            return context.getSharedPreferences(HoYo, Context.MODE_PRIVATE);
        else return null;
    }

    private static SharedPreferences.Editor InitEditor(Context context) {
        if (context != null)
            return context.getSharedPreferences(HoYo, Context.MODE_PRIVATE).edit();
        else
            return null;
    }

    //保存usertoken
    public static void setUserToken(Context mContext, String usertoken) {
        SharedPreferences.Editor hoyoet = InitEditor(mContext);
        hoyoet.putString(UserToken, usertoken);
        hoyoet.commit();
    }

    //读取usertoken
    public static String getUserToken(Context mContext) {
        if (mContext != null) {
            SharedPreferences hoyo = Init(mContext);
            return hoyo.getString(UserToken, null);
        } else {
            return null;
        }
    }

    //保存userid
    public static void setUserID(Context context, String userID) {
        SharedPreferences.Editor hoyoet = InitEditor(context);
        hoyoet.putString(UserID, userID);
        hoyoet.commit();
    }

    //读取userid
    public static String getUserID(Context context) {
        if (context != null) {
            SharedPreferences hoyo = Init(context);
            return hoyo.getString(UserID, "");
        } else {
            return null;
        }
    }

    public static void SetValue(Context mycontex, String key, String value) {
        SharedPreferences.Editor hoyoet = InitEditor(mycontex);
        hoyoet.putString(key, value);
        hoyoet.commit();
    }

    public static String GetValue(Context mycontext, String key, String defaultValue) {
        SharedPreferences hoyo = Init(mycontext);
        return hoyo.getString(key, defaultValue);
    }
}
