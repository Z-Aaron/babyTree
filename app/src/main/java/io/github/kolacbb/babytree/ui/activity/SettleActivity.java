package io.github.kolacbb.babytree.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.model.UserLocation;
import io.github.kolacbb.babytree.util.CommodityUtils;
import io.github.kolacbb.babytree.util.LocationUtils;

/**
 * Created by zhangd on 2017/6/15.
 */

public class SettleActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        getSupportActionBar().setTitle("结算");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        UserLocation ul = LocationUtils.getDefaultLocation();
        TextView nameTv = (TextView) findViewById(R.id.name);
        TextView locationTv = (TextView) findViewById(R.id.location);
        nameTv.setText(ul.getName() + "    " + ul.getPhone());
        locationTv.setText(ul.getProvince() + ul.getCity() + ul.getLocation());

        findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView goods = (TextView) findViewById(R.id.goods);
        List<Commodity> commodities = CommodityUtils.getShoppingCartCommoditys();
        String goodsStr = "";
        for (Commodity co : commodities) {
            goodsStr += co.getTitle() + "  " + co.getPrice() + "\n";
        }
        goods.setText(goodsStr);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
