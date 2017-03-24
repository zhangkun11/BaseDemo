package com.rolmex.android.oa.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.rolmex.android.emalltone.R;
import com.rolmex.android.oa.Config;
import com.rolmex.android.oa.MainApp;
import com.rolmex.android.oa.adapter.MyBaseAdapter;
import com.rolmex.android.oa.adapter.ProductListAdapter;
import com.rolmex.android.oa.entity.Product;
import com.rolmex.android.oa.webservice.Context;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/9/27.
 */

public class ProductInfoActivity extends BaseActivity {
    @InjectView(R.id.product_list)
    ListView productList;
    List<Product> list=new ArrayList<Product>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_info);
        ButterKnife.inject(this);
        for(int i=0;i<20;i++){
            Product product=new Product();
            product.setName("mmmm");
            product.setPrice("100");
            product.setPv("100");
            list.add(product);
        }
        String uid=new MainApp().getUser().getVarUID();

        Context.current().method("").getParameter();
        Log.e("vidic", "--->"+uid );
        Log.e("vidic", "get ");
        Log.e("vidic", "onCreate: "+ Config.APP_CODE+"\\"+ MainApp.getInstance().getValidCode()+MainApp.getInstance().getUser().getVarUID());
        productList.setAdapter(
                new ProductListAdapter(
                        new MyBaseAdapter(ProductInfoActivity.this,R.layout.product_info_item,list),
                        list)
        );
    }
}
