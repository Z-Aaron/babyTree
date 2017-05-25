package io.github.kolacbb.babytree.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.ShoppingCart;

/**
 * Created by zhangd on 2017/5/25.
 */

public class ShoppingCartAdapter extends BaseAdapter {

    List<ShoppingCart> mShoppingCarts;

    public void setmShoppingCarts(List<ShoppingCart> mShoppingCarts) {
        this.mShoppingCarts = mShoppingCarts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 1;
        //return mShoppingCarts == null ? 0 : mShoppingCarts.size();
    }

    @Override
    public ShoppingCart getItem(int position) {
        return mShoppingCarts.get(mShoppingCarts.size() - position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, null);

        return root;
    }
}
