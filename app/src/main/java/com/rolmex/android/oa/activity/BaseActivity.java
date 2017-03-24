package com.rolmex.android.oa.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class BaseActivity extends ActionBarActivity {
    private static final String TAG_FRAG_PROGRESS = "frag_progress";

    private static final String SESSION_TIMEOUT = "SessionTimeout";

    private ProgressDialog progressDialog;

    protected void init() {

    }

    protected int getLayout() {
        return 0;
    }

    public void showProgress(CharSequence message) {
        if(progressDialog!=null&&progressDialog.isShowing()){
            return;
        }
            progressDialog=new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
            progressDialog.show();
    }

    public void dismissProgress() {
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public void setProgressMessage(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        }
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        if (getLayout() != 0) {
            setContentView(getLayout());
            init();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public <T extends Fragment> T initPresenter(Class<T> clz) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        T presenter = (T) fragmentManager.findFragmentByTag("presenter");
        if (presenter == null) {
            try {
                presenter = clz.newInstance();
                fragmentManager.beginTransaction().add(presenter, "presenter").commit();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
