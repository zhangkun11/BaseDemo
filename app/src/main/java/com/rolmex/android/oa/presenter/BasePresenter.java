package com.rolmex.android.oa.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rolmex.android.oa.MainApp;
import com.rolmex.android.oa.activity.BaseActivity;
import com.rolmex.android.oa.entity.User;
import com.rolmex.android.oa.webservice.Session;

public class BasePresenter<View extends BaseActivity> extends Fragment {
    public View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        view = (View) activity;
        MainApp.getBus().register(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewReady();
    }

    protected void onViewReady() {

    }

    public interface PresenterTask<Result> {
        public Result doInBackground();

        public void postResult(Result result);
    }

    public <T> void execute(final PresenterTask<T> task) {
        new AsyncTask<Void, Void, T>() {
            @Override
            protected T doInBackground(Void... params) {
                if (view == null) {
                    return null;
                }
                return task.doInBackground();
            }

            @Override
            protected void onPostExecute(T t) {
                super.onPostExecute(t);
                if (view == null) {
                    return;
                }
                task.postResult(t);
            }
        }.execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainApp.getBus().unregister(this);
        view = null;
    }


    public User getUser() {
        return MainApp.getInstance().getUser();
    }

    public Session getSession() {
        return MainApp.getSession();
    }
}
