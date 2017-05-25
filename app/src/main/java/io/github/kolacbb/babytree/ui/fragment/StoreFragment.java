package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.ui.adapter.ImagePickerAdapter;
import io.github.kolacbb.babytree.ui.adapter.StoreAdapter;

/**
 * 商城模块的Fragment 继承自BaseFragment，BaseFragment中封装了一部分方法，使Fragment更加易于使用
 * Created by kolab on 2016/11/6.
 */
public class StoreFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    RecyclerView mRecyclerView;
    StoreAdapter mSotreAdapter;

    public static final String TAG = StoreFragment.class.getSimpleName();

    /**
     * 外部使用该Fragment时，获取该Fragment实例的方法
     * 在这里不推荐使用在外部使用new 关键字获取该实例
     * @return: MyFragment 的一个新的实例
     */
    public static StoreFragment getInstance() {
        return new StoreFragment();
    }

    /**
     * 由BaseFragment 封装的方法
     * 用于获取与该Fragment中绑定的xml视图，该方法主要由BaseFragment使用
     * @return: layout id
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_store;
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
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setHasFixedSize(true);
        mSotreAdapter = new StoreAdapter();
        mRecyclerView.setAdapter(mSotreAdapter);
    }

    /**
     * 从服务端获取母婴商品数据
     */
    private void loadDate() {

    }

    /**
     * 该方法为控件注册的监听，当布局中的控件被点击之后，该方法会被调用
     * 通过获取View中的Id来判断真正被点击的控件，从而执行下一步操作
     * @param v 被点击的View
     */
    @Override
    public void onClick(View v) {

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
}
