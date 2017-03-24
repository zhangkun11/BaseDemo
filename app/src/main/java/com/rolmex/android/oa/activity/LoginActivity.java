package com.rolmex.android.oa.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.presenter.LoginPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    LoginPresenter presenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Required(order = 1, message = "请输入用户名")
    @InjectView(R.id.userName)
    EditText user_number;

    @Password(order = 2, message = "请输入密码")
    @InjectView(R.id.pwd)
    EditText user_password;

    /*@InjectView(R.id.rememberPwd)
    CheckBox rememberPwd;*/

    @InjectView(R.id.versionName)
    TextView versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.inject(this);
        presenter = initPresenter(LoginPresenter.class);
        //懒得写账号
        sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        getSharePreference(user_number,user_password);


    }

    public void setVersionName(String version) {
        versionName.setText(version);
    }

    @OnClick(R.id.login)
    void loginClicked() {
        setSharedPreferences(user_number.getText().toString(),user_password.getText().toString());
        presenter.onLogin(user_number.getText().toString().trim(),user_password.getText().toString().trim());
    }

    @OnClick(R.id.unlock)
    void unlockClicked() {
    }

    @OnClick(R.id.resetPwd)
    void resetClicked() {
    }
    private void getSharePreference(EditText userName,EditText passWord){
        if(sharedPreferences.getString("userName",null)!=null&&sharedPreferences.getString("passWord",null)!=null)
        {userName.setText(sharedPreferences.getString("userName",null));
            passWord.setText(sharedPreferences.getString("passWord",null));}
    }
    private void setSharedPreferences(String mUserName,String mPassWord){

        editor.putString("userName",mUserName);
        editor.putString("passWord",mPassWord);
        editor.commit();
    }
}
