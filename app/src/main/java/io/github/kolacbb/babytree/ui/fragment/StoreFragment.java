package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;

/**
 * Created by kolab on 2016/11/6.
 */

public class StoreFragment extends BaseFragment {
    public static final String TAG = StoreFragment.class.getSimpleName();

    public static StoreFragment getInstance() {
        return new StoreFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }
}
