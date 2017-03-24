package com.rolmex.android.oa.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class MenuEntity {
    public MenuEntity parent;

    boolean enabled = false;

    public int icon;

    public String name;

    public String hideName;

    ActivityInfo activityInfo;

    List<MenuEntity> menuIntems;

    public MenuEntity(String name, int icon) {
        this.name = name;
        this.hideName = "";
        this.icon = icon;
    }

    public MenuEntity(String name,String hideName, int icon) {
        this.name = name;
        this.hideName = hideName;
        this.icon = icon;
    }

    public void addSubMenu(String name) {
        MenuEntity child = new MenuEntity(name, 0);
        child.parent = this;
        addItem(child);
    }

    public void addSubMenu(String name ,String hideName) {
        MenuEntity child = new MenuEntity(name,hideName, 0);
        child.parent = this;
        addItem(child);
    }

    public Intent getIntent(Context context) {
        if (activityInfo == null) {
            PackageManager pm = context.getPackageManager();
            try {
                PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
                for (ActivityInfo ai : packageInfo.activities) {
                    if ((name+hideName).equals(ai.nonLocalizedLabel)) {
                        activityInfo = ai;
                        break;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (activityInfo == null) {
            return null;
        } else {
            Intent intent = new Intent();                         //启动应用程序
            intent.setClassName(context.getPackageName(), activityInfo.name);
            return intent;
        }
    }

    public boolean isMenuItem() {
        return menuIntems == null;
    }

    public void enable() {
        enabled = true;
        if (parent != null) {
            parent.enable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<MenuEntity> getMenuIntems() {
        return menuIntems;
    }

    public void addItem(MenuEntity item) {
        if (menuIntems == null) {
            menuIntems = new ArrayList<>();
        }
        menuIntems.add(item);
    }
}
