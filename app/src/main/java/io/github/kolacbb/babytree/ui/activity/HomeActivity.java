package io.github.kolacbb.babytree.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.ui.fragment.GroupFragment;
import io.github.kolacbb.babytree.ui.fragment.HomeFragment;
import io.github.kolacbb.babytree.ui.fragment.MyFragment;
import io.github.kolacbb.babytree.ui.fragment.StoreFragment;

/**
 * Created by kolab on 2016/11/5.
 */

public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private BottomNavigationView mBottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        showFragment(HomeFragment.TAG);
                        break;
                    case R.id.menu_group:
                        showFragment(GroupFragment.TAG);
                        break;
                    case R.id.menu_store:
                        showFragment(StoreFragment.TAG);
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
        } else if (tag.equals(GroupFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = GroupFragment.getInstance();
                Log.e(TAG, "showFragment: new GroupFragment fragment");
            }
        } else if (tag.equals(StoreFragment.TAG)) {
            if (baseFragment == null) {
                baseFragment = StoreFragment.getInstance();
                Log.e(TAG, "showFragment: new StoreFragment fragment");
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
