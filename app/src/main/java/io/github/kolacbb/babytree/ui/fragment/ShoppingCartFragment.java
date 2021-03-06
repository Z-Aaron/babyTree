package io.github.kolacbb.babytree.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.model.UserLocation;
import io.github.kolacbb.babytree.ui.activity.SettleActivity;
import io.github.kolacbb.babytree.ui.activity.UserLocationActivity;
import io.github.kolacbb.babytree.ui.adapter.ShoppingCartAdapter;
import io.github.kolacbb.babytree.util.CommodityUtils;
import io.github.kolacbb.babytree.util.LocationUtils;

/**
 * 购物车模块的Fragment 继承自BaseFragment，BaseFragment中封装了一部分方法，使Fragment更加易于使用
 * Created by kolab on 2016/11/6.
 */
public class ShoppingCartFragment extends BaseFragment  implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView mListView;
    ShoppingCartAdapter mAdapter;
    TextView mPriceTv;

    public static final String TAG = ShoppingCartFragment.class.getSimpleName();
    /**
     * 外部使用该Fragment时，获取该Fragment实例的方法
     * 在这里不推荐使用在外部使用new 关键字获取该实例
     * @return: MyFragment 的一个新的实例
     */
    public static ShoppingCartFragment getInstance() {
        return new ShoppingCartFragment();
    }

    /**
     * 由BaseFragment 封装的方法
     * 用于获取与该Fragment中绑定的xml视图，该方法主要由BaseFragment使用
     * @return: layout id
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }
    /**
     * BaseFragment在将Fragment与xml视图绑定之后且当前Fragment所在的
     * Activity的onCreate方法调用完成之后，会调用该方法。
     * 可以在该方法内，通过id获取控件实例，初始化布局，注册监听等等。
     * 大部分的使用方法与在Activity中的onCreate中类似
     * @param savedInstanceState 恢复已保存的视图实例
     */
    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mListView = (ListView) mRootView.findViewById(R.id.list_view);
        mAdapter = new ShoppingCartAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(this);
        mPriceTv = (TextView) mRootView.findViewById(R.id.price);
        mRootView.findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettleActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setmCommodities(CommodityUtils.getShoppingCartCommoditys());
        mPriceTv.setText(String.valueOf(mAdapter.getTotalPrice()));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mAdapter.setmCommodities(CommodityUtils.getShoppingCartCommoditys());
            mPriceTv.setText(String.valueOf(mAdapter.getTotalPrice()));
        }
    }

    /**
     * 当一个商品条目被点击时调用该方法
     * 将该商品信息传入到下一个界面，在商品详情页显示详细信息
     * @param parent adapterview 的parent
     * @param view 被点击的item的view
     * @param position 当前item在list view 中的位置
     * @param id position id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 该方法为控件注册的监听，当布局中的控件被点击之后，该方法会被调用
     * 通过获取View中的Id来判断真正被点击的控件，从而执行下一步操作
     * 该方法主要时，当点击结算时，跳转到结算界面
     * @param v 被点击的View
     */
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), SettleActivity.class));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final Commodity co = mAdapter.getItem(position);
        new AlertDialog.Builder(getContext())
                .setItems(new String[]{"删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CommodityUtils.removeFromShoppingCart(co);
                        mAdapter.setmCommodities(CommodityUtils.getShoppingCartCommoditys());
                        mPriceTv.setText(String.valueOf(mAdapter.getTotalPrice()));
                    }
                })
                .show();
        return true;
    }
}
