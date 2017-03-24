
package com.rolmex.android.oa.utils;

import android.text.TextUtils;

import com.rolmex.android.oa.Config;
import com.rolmex.android.oa.MainApp;

public class ParamKeyUtil {

    public static String parseKey(String str) {

        StringBuilder sb = new StringBuilder();
        for (Integer i : Config.index) {
            sb.append(str.charAt(i - 1));
        }
        return sb.toString();
    }


    public static String loginEncrypt(String param) {
        if (param == null) {
            param = "";
        }
        return TrippleDes.encrypt(Config.PARAMETER_KEY, param);
    }

    public static String loginDecrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        } else {
            return TrippleDes.decrypt(Config.PARAMETER_KEY, str);
        }
    }

    public static String encrp(String param) {
        if (param == null) {
            param = "";
        }
        return TrippleDes.encrypt(MainApp.getInstance().getSession().get("paramKey"), param);
    }
    public static String decrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        String decryptedStr = TrippleDes.decrypt(MainApp.getInstance().getSession().get("paramKey"), str);
        if (TextUtils.isEmpty(decryptedStr)) {
            decryptedStr = "{'bSuccess':False,'strMsg':'SessionTimeout'}";
        }
        return decryptedStr;
    }
}
