package com.rolmex.android.oa.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ListAdapter;

import com.commonsware.cwac.endless.EndlessAdapter;
import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */

public class ProductListAdapter extends EndlessAdapter{
    private RotateAnimation rotate = null;
    private View pendingView = null;
    private List<Product> list=new ArrayList<Product>();


    public ProductListAdapter(ListAdapter wrapped,List<Product> list) {
        super(wrapped);
        this.list=list;
        initAnimation();
    }

    public ProductListAdapter(ListAdapter wrapped, boolean keepOnAppending) {
        super(wrapped, keepOnAppending);
        initAnimation();
    }

    public ProductListAdapter(Context context, ListAdapter wrapped, int pendingResource) {
        super(context, wrapped, pendingResource);
        initAnimation();
    }

    public ProductListAdapter(Context context, ListAdapter wrapped, int pendingResource, boolean keepOnAppending) {
        super(context, wrapped, pendingResource, keepOnAppending);
        initAnimation();
    }
    private void initAnimation(){
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(600);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(Animation.INFINITE);
    }
    //显示加载时的view
    @Override
    protected View getPendingView(ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row, null);
        pendingView = row.findViewById(android.R.id.text1);
        pendingView.setVisibility(View.GONE);
        pendingView = row.findViewById(R.id.throbber);
        pendingView.setVisibility(View.VISIBLE);
        startProgressAnimation();

        return row;

    }

    private void startProgressAnimation() {
        if (pendingView != null) {
            pendingView.startAnimation(rotate);
        }
    }


    //设置加载时间
    @Override
    protected boolean cacheInBackground() throws Exception {
        SystemClock.sleep(3000);
        //设置限制总数据大小
        return (getWrappedAdapter().getCount() < 1000);
    }
    //添加数据
    @Override
    protected void appendCachedData() {
        if(getWrappedAdapter().getCount() < 1000){
            for(int i=0;i<10;i++){
                Product product=new Product();
                product.setName("wwwww");
                product.setPrice("100");
                product.setPv("100");
                list.add(product);
            }
        }

    }


}
