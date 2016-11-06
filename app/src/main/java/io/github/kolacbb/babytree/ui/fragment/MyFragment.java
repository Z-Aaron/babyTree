package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;

/**
 * Created by kolab on 2016/11/6.
 */

public class MyFragment extends BaseFragment {
    public static final String TAG = MyFragment.class.getSimpleName();

    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }
}
