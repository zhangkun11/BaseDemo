package com.rolmex.android.oa.webservice;

import com.rolmex.android.oa.Config;
import com.rolmex.android.oa.entity.Platform;
import com.rolmex.android.oa.entity.Result;


public final class Webservice {

    public static Platform UserGetIPKeyService(String logType, String userName, String preLoginMsg) {
        Method method = Context.main().method("UserGetIPKeyService");
        method.add("logType", logType, true, true);
        method.add("userName", userName, true, true);
        method.add("strReqMsg", preLoginMsg, false, true);
        return method.invoke(Platform.class, null);
    }

    public static Result UserLoginService(String logType, final String userName, final String strUserPwd,
                                          String strDnyPwd, String loginIP) {
        Method method = Context.current().method("UserLoginService", Config.PARAMETER_KEY);
        method.add("logType", logType, true, true);
        method.add("userName", userName, true, true);
        method.add("strUserPwd", strUserPwd, true, true);
        method.add("strDnyPwd", strDnyPwd, true, true);
        method.add("loginIP", loginIP, true, true);
        return method.invoke(Result.class, Result.DEFAULT_RESULT);
    }

    public static Result getVQC (String varUID) {
        Method method = Context.current().method("VerificationQualificationsCertification");
        method.add("varUID", varUID);
        return method.invoke(Result.class, Result.DEFAULT_RESULT);
    }

    public static Result VerificationActivateCode(String varUserID) {
        Method method = Context.current().method("VerificationActivateCode");
        method.add("varUserID", varUserID);
        return method.invoke(Result.class, Result.DEFAULT_RESULT);
    }
}
