package com.rolmex.android.oa.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.activity.MenuEntity;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuEntity> {
    LayoutInflater inflater;

    public MenuAdapter(Context context, List<MenuEntity> objects) {
        super(context, 0, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_menu, parent, false);
        }

        bindView((TextView) convertView.findViewById(R.id.textView), getItem(position));
        return convertView;
    }

    private void bindView(TextView textView, MenuEntity item) {
        textView.setText(item.name);
        setTopIcon(textView, item.icon);
    }

    private void setTopIcon(TextView textView, int icon) {
        Drawable topIcon = getContext().getResources().getDrawable(icon);
        topIcon.setBounds(0, 0, topIcon.getMinimumWidth(), topIcon.getMinimumHeight());
        textView.setCompoundDrawables(null, topIcon, null, null);

    }

}
