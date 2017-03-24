package com.rolmex.android.oa.presenter;

import android.content.Intent;
import android.os.Bundle;
import com.rolmex.android.emalltone.BuildConfig;
import com.rolmex.android.oa.activity.HomeActivity;
import com.rolmex.android.oa.activity.LoginActivity;
import com.rolmex.android.oa.entity.Platform;
import com.rolmex.android.oa.entity.Result;
import com.rolmex.android.oa.entity.User;
import com.rolmex.android.oa.util.SdcardLibHelper;
import com.rolmex.android.oa.utils.ParamKeyUtil;
import com.rolmex.android.oa.webservice.Session;
import com.rolmex.android.oa.webservice.Webservice;


public class LoginPresenter extends BasePresenter<LoginActivity> {

    private String errorMsg;

    private Bundle bundle=null;

    @Override
    protected void onViewReady() {
        super.onViewReady();
        getSession().clear();
        bundle=new Bundle();
        init();
    }

    private void init() {
        view.setVersionName(BuildConfig.VERSION_NAME);
    }

    private boolean checkInputValue(String value){
        return value == null || value.isEmpty();
    }

    public void onLogin(final String name, final String password) {
        if(checkInputValue(name)||checkInputValue(password)){
            view.showToast("用户名和密码不能为空");
            return;
        }
        view.showProgress("正在登录...");
        execute(new PresenterTask<Boolean>() {
            @Override
            public Boolean doInBackground() {
                return doLogin(name, password);
            }

            @Override
            public void postResult(Boolean success) {
                view.dismissProgress();
                if (success) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    view.finish();
                } else {
                    view.showToast(errorMsg);
                }
            }
        });
    }


    private boolean doLogin(String name, String password) {
        Session session = getSession();
        session.clear();
        try {
            Platform platform = fetchDepartment(name);
            session.set("RequestIP", platform.RequestIP);
            session.set("AuthToken", platform.AuthToken);

            User user = fetchUserInfo(name, password);
            storeUserInfo(session, user);

            String status = checkAgreement();
            session.set("status", status);
            Result result = getVQC(user);
            session.set("vqc", result.strCode);
            session.set("strMsg", result.strMsg);
            session.set("agreementSigned", status.equals("1"));
            session.set("isCertificated", !result.strCode.equals("7"));

            loadDic(session);
            session.loginAs(name);
            return true;
        } catch (Exception e) {
            errorMsg = e.getMessage();
            return false;
        }
    }

    private void loadDic(Session session) {

    }


    private void storeUserInfo(Session session, User user) {
        session.set("user", user);

        String paramKey = ParamKeyUtil.parseKey(user.ValidCode);
        session.set("paramKey", paramKey);
        session.set("validCode", ParamKeyUtil.loginEncrypt(user.ValidCode));

        session.set("varOperatPWD", user.getVarOperatPWD());
        session.set("secondPwd", user.getVarOperatPWD());
    }

    private Result getVQC(User user) {
        Result result = Webservice.getVQC(user.getVarUID());
        if (!result.isSuccess()) {
            throw new LoginException(result.strMsg);
        }
        return result;
    }

    private String checkAgreement() {
        Result result = Webservice.VerificationActivateCode(getUser().getVarUserID());
        if (!result.isSuccess()) {
            throw new LoginException(result.strMsg);
        }
        return result.strStatus;
    }

    private Platform fetchDepartment(String userId) {
        SdcardLibHelper sdcardHelper = new SdcardLibHelper();
        Platform platform = Webservice.UserGetIPKeyService("1", userId,
                sdcardHelper.getCsn());
        checkPlatform(platform);
        return platform;
    }

    private void checkPlatform(Platform department) {
        if (department == null)
            throw new LoginException("获取部门信息失败");
        if (!department.bSuccess)
            throw new LoginException(department.strMsg);
    }

    private User fetchUserInfo(String userId, String pwd) {
        Result response = Webservice.UserLoginService("1", userId, pwd, "", "127.0.0.1");
        checkUserInfo(response);
        return response.LoginData.get(0);
    }


    private void checkUserInfo(Result response) {
        if (response == null)
            throw new LoginException("获取用户信息失败");
        if (!response.isSuccess())
            throw new LoginException(response.strMsg);
    }


    public static final class LoginException extends RuntimeException {
        LoginException(String msg) {
            super(msg);
        }
    }


}
