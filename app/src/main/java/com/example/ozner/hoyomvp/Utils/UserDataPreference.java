package com.example.ozner.hoyomvp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public class UserDataPreference {
    public static final String IndexInfo = "indexinfo";
    public final static String AuthorityDetail = "authoritydetail";//团队信息，AuthorityDetail类的序列化

    public static SharedPreferences Init(Context context) {
        if (context != null) {
            String file = HoYoPreference.GetValue(context, HoYoPreference.UserID, "Oznerser");
            return context.getSharedPreferences(file, Context.MODE_PRIVATE);
        } else
            return null;
    }

    public static void SetUserData(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences sp = Init(context);
            SharedPreferences.Editor et = sp.edit();
            et.putString(key, value);
            et.commit();
        }
    }

    public static String GetUserData(Context context, String key, String defvalue) {
        if (context != null) {
            SharedPreferences sp = Init(context);
            String value2 = sp.getString(key, defvalue);
            if (value2 != null) {
                if (value2.equals("null"))
                    return defvalue;
                else
                    return value2;
            }
            return value2;
        } else {
            return defvalue;
        }
    }
}
