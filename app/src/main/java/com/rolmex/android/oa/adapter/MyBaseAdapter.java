package com.rolmex.android.oa.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class MyBaseAdapter extends ArrayAdapter<Product> {

    private List<Product> list=new ArrayList<Product>();
    private int resourceId;
    public MyBaseAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        resourceId=resource;
        list=objects;
    }

        class ViewHolder{
            ImageView productIcon;
            TextView productName;
            TextView productPrice;
            TextView productPv;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Product getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                view=convertView.inflate(getContext(),resourceId,null);
                viewHolder.productIcon= (ImageView) view.findViewById(R.id.product_icon);
                viewHolder.productName= (TextView) view.findViewById(R.id.product_name);
                viewHolder.productPrice= (TextView) view.findViewById(R.id.product_price);
                viewHolder.productPv= (TextView) view.findViewById(R.id.product_pv);
                view.setTag(viewHolder);
            }else{
                view=convertView;
                viewHolder=(ViewHolder) view.getTag();
            }
            viewHolder.productName.setText(list.get(position).getName());
            viewHolder.productPrice.setText(list.get(position).getPrice());
            viewHolder.productPv.setText(list.get(position).getPv());
            return view;
        }

}
