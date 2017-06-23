package io.github.kolacbb.babytree.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.github.kolacbb.babytree.model.Commodity;

/**
 * Created by zhangd on 2017/6/15.
 */

public class CommodityUtils {
    private static final String KEY_SHOPPING_CART = "SHOPPING_CART";
    private static final String KEY_STORE = "STORE";

    public static List<Commodity> getShoppingCartCommoditys() {
        String json = SpUtils.find(KEY_SHOPPING_CART);
        if (json != null) {
            Gson g = new Gson();
            ArrayList<Commodity> ts = g.fromJson(json, new TypeToken<List<Commodity>>(){}.getType());
            return ts;
        }
        return null;
    }

    public static void addComondityToShoppingCart(Commodity commodity) {
        commodity.setId(System.currentTimeMillis());
        List<Commodity> commodities = getShoppingCartCommoditys();
        if (commodities == null) {
            commodities = new ArrayList<>();
        }
        commodities.add(commodity);
        SpUtils.saveOrUpdate(KEY_SHOPPING_CART, new Gson().toJson(commodities));
    }

    public static void removeFromShoppingCart(Commodity commodity) {
        List<Commodity> commodities = getShoppingCartCommoditys();
        int i;
        for (i = 0; i < commodities.size(); i++) {
            if (commodities.get(i).getId() == commodity.getId()) {
                break;
            }
        }
        commodities.remove(i);
        SpUtils.saveOrUpdate(KEY_SHOPPING_CART, new Gson().toJson(commodities));
    }


    public static List<Commodity> getStoreCommodities() {
        String json = SpUtils.find(KEY_STORE);
        if (json != null) {
            Gson g = new Gson();
            ArrayList<Commodity> ts = g.fromJson(json, new TypeToken<List<Commodity>>(){}.getType());
            return ts;
        }
        return null;
    }
}
