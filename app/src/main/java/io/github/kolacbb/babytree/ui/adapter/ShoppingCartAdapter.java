package io.github.kolacbb.babytree.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.model.ShoppingCart;

/**
 * Created by zhangd on 2017/5/25.
 */

public class ShoppingCartAdapter extends BaseAdapter {

    List<Commodity> mCommodities;

    public void setmCommodities(List<Commodity> mCommodities) {
        this.mCommodities = mCommodities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCommodities == null ? 0 : mCommodities.size();
    }

    @Override
    public Commodity getItem(int position) {
        return mCommodities.get(mCommodities.size() - position -1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, null);
        Commodity commodity = getItem(position);
        ImageView icon = (ImageView) root.findViewById(R.id.icon);
        TextView title = (TextView) root.findViewById(R.id.title);
        TextView price = (TextView) root.findViewById(R.id.price);

        title.setText(commodity.getTitle());
        price.setText(String.valueOf(commodity.getPrice()));

        return root;
    }

    public float getTotalPrice() {
        float total = 0;
        if (mCommodities != null)
            for (Commodity c : mCommodities) {
                total += c.getPrice();
            }
        return total;
    }
}
