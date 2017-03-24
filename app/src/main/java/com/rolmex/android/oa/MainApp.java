package com.rolmex.android.oa;

import android.app.Application;
import android.text.TextUtils;

import com.rolmex.android.oa.entity.User;
import com.rolmex.android.oa.webservice.Session;
import com.squareup.otto.Bus;

public class MainApp extends Application {

    private static MainApp sInstance;

    private static Session session;

    private static Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        session = new Session();
        bus = new Bus();
        bus.register(this);
    }

    public static Bus getBus() {
        return bus;
    }

    public static Session getSession() {
        return session;
    }

    public static MainApp getInstance() {
        return sInstance;
    }

    public User getUser() {
        return session.getObj("user");
    }

    public boolean isOperatPwdMatched(String input) {
        return TextUtils.equals(input, session.get("secondPwd"));
    }

    public void setOperatPwd(String newPwd) {
        session.set("secondPwd", newPwd);
    }

    public String getOperatPwd() {
        return session.get("secondPwd");
    }


    public String getValidCode() {
        return session.get("validCode");
    }

}
