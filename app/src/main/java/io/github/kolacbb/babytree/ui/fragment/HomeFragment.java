package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.model.Article;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.model.Result;
import io.github.kolacbb.babytree.net.RetrofitManager;
import io.github.kolacbb.babytree.net.service.BusinessService;
import io.github.kolacbb.babytree.ui.adapter.HomeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        mRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onRefresh() {
        RetrofitManager.create(BusinessService.class)
                .getArticles()
                .enqueue(new Callback<Result<Article>>() {
                    @Override
                    public void onResponse(Call<Result<Article>> call, Response<Result<Article>> response) {
                        mRefreshLayout.setRefreshing(false);
                        if (response.body() == null || response.body().getResults() == null) {
                            return;
                        }
                        mAdapter.setArticles(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<Result<Article>> call, Throwable throwable) {
                        mRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
