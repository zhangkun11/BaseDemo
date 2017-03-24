package com.rolmex.android.oa.activity;

import android.util.Log;

import com.rolmex.android.oa.utils.NavigationHelper;

import java.util.Iterator;
import java.util.List;

public abstract class  AbsMenuBuilder {
    NavigationHelper navigationHelper = new NavigationHelper();

    public final List<MenuEntity> buildMenu() {
        List<MenuEntity> menuList = buildAllMenu();
        enableMenu(menuList);
        removeDisabledMenu(menuList);
        return menuList;
    }

    private void enableMenu(List<MenuEntity> menuList) {
        for (MenuEntity m : menuList) {
            if (m.isMenuItem()) {
                if (navigationHelper.isOk(m.name+m.hideName)) {
                    m.enable();
                }
            } else {
                enableMenu(m.getMenuIntems());
            }
        }
    }

    private void removeDisabledMenu(List<MenuEntity> menus) {
        if (menus == null) {
            return;
        }
        Iterator<MenuEntity> i = menus.iterator();
        while (i.hasNext()) {
            MenuEntity m = i.next();
            if (m.isEnabled()) {
                removeDisabledMenu(m.getMenuIntems());
            } else {
                Log.i("vidic","menu name"+m.name);
                i.remove();
            }
        }
    }

    protected abstract List<MenuEntity> buildAllMenu();
}
