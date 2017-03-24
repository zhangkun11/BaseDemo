package com.rolmex.android.oa.webservice;

import com.rolmex.android.oa.Config;
import com.rolmex.android.oa.MainApp;
import com.rolmex.android.oa.utils.MD5Util;

import static com.rolmex.android.oa.Config.LOGIN_SIGN_KEY;
import static com.rolmex.android.oa.Config.LOGIN_URL;
import static com.rolmex.android.oa.Config.PARAMETER_KEY;

public class Context {

    private String url;
    private String signKey;

    public final String apkNo;
    public final String strAuthorizationCode;

    public final String encryptKey;

    public Context(String url, String signKey) {
        this(url, signKey, PARAMETER_KEY);
    }

    public Context(String url, String signKey, String encryptKey) {
        this.url = url;
        this.signKey = signKey;
        this.encryptKey = encryptKey;

        apkNo = Config.APP_CODE;
        strAuthorizationCode = MainApp.getInstance().getValidCode();
    }

    public String getUrl() {
        return url;
    }

    public String sign(String target) {
        return MD5Util.getMD5String(target + signKey);
    }

    public Method method(String name) {
        return new Method(this, name, encryptKey);
    }

    public Method method(String name, String key) {
        return new Method(this, name, key);
    }

    public static Context current() {
        String url = MainApp.getInstance().getSession().get("RequestIP");
        String encryptKey = MainApp.getInstance().getSession().get("paramKey");
        String signKey = MainApp.getInstance().getSession().get("AuthToken");

        return new Context(url, signKey, encryptKey);
    }

    public static Context main() {
        return new Context(LOGIN_URL, LOGIN_SIGN_KEY, PARAMETER_KEY);
    }
}
