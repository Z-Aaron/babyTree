package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.ui.adapter.HomeAdapter;

/**
 * Created by kolab on 2016/11/6.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRefreshLayout.setOnRefreshListener(this);
//        mRefreshLayout.setRefreshing(true);
        mAdapter = new HomeAdapter(mCtx);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onRefresh() {

    }
}
