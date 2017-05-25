package io.github.kolacbb.babytree.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.ui.fragment.ShoppingCartFragment;
import io.github.kolacbb.babytree.ui.fragment.StoreFragment;
import io.github.kolacbb.babytree.ui.fragment.HomeFragment;
import io.github.kolacbb.babytree.ui.fragment.MyFragment;

/**
 * 系统的主页面，其中有4个fragment，分别为feed页，商城页，购物车页，“我”页
 * 该页面的主要的工作就是控制4个fragment之间的切换。通过fragmentTransaction
 * 统一管理这几个fragment，设置title，初始化底部的BottomNavigationView。
 * Created by kolab on 2016/11/5.
 */

public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;

    /**
     * 在该方法里，设置加载xml布局，初始化Bottom Navigation View，设置点击监听
     * 控制监听回调，点击不同的Bottom View，会切换到不同的fragment
     * 初始化Title
     * @param savedInstanceState 用于恢复该Activity被销毁时保存的变量
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // init toolbar
        getSupportActionBar().setTitle("掌上孕婴平台");

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        showFragment(HomeFragment.TAG);
                        break;
                    case R.id.menu_group:
                        showFragment(StoreFragment.TAG);
                        break;
                    case R.id.menu_store:
                        showFragment(ShoppingCartFragment.TAG);
                        break;
                    case R.id.menu_my:
                        showFragment(MyFragment.TAG);
                        break;
                }
                return true;
            }
        });

        showFragment(HomeFragment.TAG);
    }

    private BaseFragment mOldFragment;

    /**
     * 用于切换不同Fragment 的方法，通过传入不同的Tag，Tag为Fragment实例中设置的。
     * 1. 判断是否可以根据tag从fragment manager中获取该Fragment
     * 2. 如果可以获取，而且当前显示的Fragment就是该fragment，那么直接return
     * 3. 开启事务，隐藏其它的fragment
     * 4. 如果根据tag没有获取到Fragment，那么根据Fragment的newInstance方法获取Fragment实例
     * 5. 将Fragment添加到事务，并且显示该Fragment
     * @param tag
     */
    public void showFragment(String tag) {
        // 从事务中获取指定tag的fragment
        BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);

        // 若是该fragment已经为当前页面，则返回
        if (baseFragment != null && baseFragment.isVisible()) {
            return;
        }

        // 开启事务
        android.support.v4.app.FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        // 隐藏其他Fragment
        if (mOldFragment != null && mOldFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(mOldFragment).commit();
            //transaction.hide(mFeedFragment);
            Log.e(TAG, "showFragment: old fragment is hide");
        }

        if (tag.equals(HomeFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = HomeFragment.getInstance();
                Log.e(TAG, "showFragment: new home fragment");
            }
        } else if (tag.equals(StoreFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = StoreFragment.getInstance();
                Log.e(TAG, "showFragment: new StoreFragment fragment");
            }
        } else if (tag.equals(ShoppingCartFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = ShoppingCartFragment.getInstance();
                Log.e(TAG, "showFragment: new ShoppingCartFragment fragment");
            }
        } else if (tag.equals(MyFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = MyFragment.getInstance();
                Log.e(TAG, "showFragment: new MyFragment fragment");
            }
        }

        // 添加Fragment 到事物
        if (baseFragment == null) {
            return;
        }

        if (baseFragment.isAdded()) {
            transaction.show(baseFragment).commit();
            Log.e(TAG, "showFragment: base fragment is show");
        } else {
            transaction.add(R.id.container, baseFragment, tag).commitAllowingStateLoss();
            Log.e(TAG, "showFragment: base fragment is added");
        }
        mOldFragment = baseFragment;
    }
}
