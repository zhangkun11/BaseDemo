package com.rolmex.android.oa.webservice;

import java.util.HashMap;
import java.util.Map;

public class Session {

    Map<String, Object> map;

    public Session() {
        map = new HashMap<>();
    }

    public void set(String key, Object value) {
        map.put(key, value);
    }


    public String get(String key) {
        return (String) map.get(key);
    }

    public boolean getBoolean(String key) {
        Object value = map.get(key);
        return value == null ? false:(boolean) value;
    }

    public <T> T getObj(String key) {
        return (T) map.get(key);
    }

    public void clear() {
        map.clear();
    }

    public void loginAs(String name) {
        map.put("userName", name);
        map.put("isLogin", true);
    }

    public boolean agreementSigned() {
        return getBoolean("agreementSigned");
    }

    public void setAgreementSigned(boolean value) {
        set("agreementSigned", value);
    }

    public boolean isLogin() {
        return getBoolean("isLogin");
    }

    public boolean isPwdChecked() {
        return getBoolean("isPwdChecked");
    }

    public void setCertificated(boolean value) {
        set("isCertificated", value);
    }

    public boolean isCertificated() {
        return getBoolean("isCertificated");
    }

    public void setPwdChecked(boolean value) {
        set("isPwdChecked", value);
    }

}
