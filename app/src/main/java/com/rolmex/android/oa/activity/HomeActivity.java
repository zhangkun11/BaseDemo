package com.rolmex.android.oa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.adapter.MenuAdapter;
import com.rolmex.android.oa.presenter.HomePresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends SessionActivity implements AdapterView.OnItemClickListener {
    public HomePresenter presenter;

    @InjectView(R.id.home_grid)
    GridView menuContainer;

    @InjectView(R.id.container)
    RelativeLayout container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        ButterKnife.inject(this);
        presenter = initPresenter(HomePresenter.class);
    }

    public void bindMenu(List<MenuEntity> menuEntityGroup) {
        MenuAdapter adapter = new MenuAdapter(this, menuEntityGroup);
        menuContainer.setAdapter(adapter);
        menuContainer.setOnItemClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean isMenuItem(MenuEntity menu) {
        return menu.getMenuIntems() == null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final MenuEntity entity = (MenuEntity) parent.getItemAtPosition(position);
        if (isMenuItem(entity)) {
            Log.e("HOMETHIS", "onItemClick: "+entity.name+entity.hideName);
            onMenuSelected(entity);
        }else{
            showMenu(view,entity.getMenuIntems());
        }
    }

    private void showMenu(View view, final List<MenuEntity> menus) {
        PopupMenu popup = new PopupMenu(this, view);
        for (int i = 0; i < menus.size(); i++) {
            popup.getMenu().add(Menu.NONE, i, Menu.NONE, menus.get(i).name);
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem menu) {
                onMenuSelected(menus.get(menu.getItemId()));
                Log.e("HOMETHIS", "onMenuItemClick: "+menu.getItemId()+"--"+menus.get(menu.getItemId()).name);
                Log.e("HOMETHIS", "onMenuItemClick2: "+menu.getItemId()+"--"+menus.get(menu.getItemId()).hideName);
                return true;
            }
        });
        popup.show();
    }

    public void onMenuSelected(MenuEntity entity) {
        Intent intent = entity.getIntent(this);

        if (intent != null) {
            startActivity(intent);
        }
    }
}
