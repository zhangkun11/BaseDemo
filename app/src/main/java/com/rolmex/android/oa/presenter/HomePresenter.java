package com.rolmex.android.oa.presenter;

import com.rolmex.android.oa.activity.HomeActivity;
import com.rolmex.android.oa.activity.MenuBuilder;
import com.rolmex.android.oa.entity.Result;
import com.rolmex.android.oa.webservice.Context;
import com.rolmex.android.oa.webservice.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePresenter extends BasePresenter<HomeActivity> {

    @Override
    protected void onViewReady() {
        super.onViewReady();
        buildMenu();

    }

    private void buildMenu() {
        execute(new PresenterTask<Result>() {
            @Override
            public Result doInBackground() {
                Method method = Context.current().method("FetchNavNameToMobile").setCallback(new Method.Callback() {
                    @Override
                    public String beforeParse(String response) {
                        Pattern pattern = Pattern.compile("\\{\"\\w+\":(\".{0,20}\")\\}");
                        Matcher matcher = pattern.matcher(response);
                        return matcher.replaceAll("$1");
                    }
                });
                method.add("strUID", getUser().getVarUID());
                method.add("strUserID", getUser().getVarUserID(),false,true);
                return method.invoke(Result.class, Result.DEFAULT_RESULT);
            }

            @Override
            public void postResult(Result result) {
                if (result.isSuccess()) {
                    getSession().set("navName", result.navNameList);
                    view.bindMenu(new MenuBuilder().buildMenu());
                } else {
                    view.showToast(result.strMsg);
                }
            }
        });
    }

}
