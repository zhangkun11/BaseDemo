package com.rolmex.android.oa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.rolmex.android.oa.MainApp;
import com.rolmex.android.oa.entity.User;
import com.rolmex.android.oa.webservice.Session;

public abstract class SessionActivity extends BaseActivity {
    private static final String TAG = SessionActivity.class.getName();

    @Override
    protected void onResume() {
        super.onResume();
    }

    public interface MainTask<T> {
        public T doInBackground();
        public void postResult(T t);
    }

    public <T> void execute(final MainTask<T> task) {
        new AsyncTask<Void, Void, T>() {
            @Override
            protected T doInBackground(Void... params) {
                if (isFinishing()) {
                    return null;
                }
                return task.doInBackground();
            }

            @Override
            protected void onPostExecute(T t) {
                super.onPostExecute(t);
                if (isFinishing()) {
                    return;
                }
                task.postResult(t);
            }
        }.execute();
    }

    public Session getSession() {
        return MainApp.getSession();
    }

    public User getUser() {
        return MainApp.getInstance().getUser();
    }



    public void startAct(Class<?> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

    public void startActForResult(Class<?> clazz,int resultCode){
        Intent intent=new Intent(this,clazz);
        startActivityForResult(intent, resultCode);
    }

    public void startActWithBundle(Class<?> clazz, Bundle bundle){
        Intent intent=new Intent(this,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActKillSelf(Class<?> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
        finish();
    }

    public void redirectTo(Class<? extends Activity> activity, String msg, int id, String title) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("msg", msg);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        startActivityForResult(intent, 0);
    }

    public void redirectTo(Class<? extends Activity> activity, String msg, int id, String title,int length) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("msg", msg);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("length", length);
        startActivityForResult(intent, 0);
    }

    public void redirectTo(Class<? extends Activity> activity, String msg, int id, String title,
                            String hint) {
        Intent intent = new Intent(getApplicationContext(), activity);
        intent.putExtra("msg", msg);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("hint", hint);
        startActivityForResult(intent, 0);
    }

    public String readInputStr(Intent intent){
        return intent.getStringExtra("data");
    }

}
