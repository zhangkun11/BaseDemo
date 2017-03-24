package com.rolmex.android.oa.utils;


import com.rolmex.android.oa.MainApp;

import java.util.Set;

public class NavigationHelper {
    Set<String> navNames = MainApp.getSession().getObj("navName");

    public boolean isOk(String nav) {
        return navNames.contains(nav);
    }

    public void test(String name){

    }

}
