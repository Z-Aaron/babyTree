package io.github.kolacbb.babytree.ui.fragment;

import android.os.Bundle;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;

/**
 * Created by kolab on 2016/11/6.
 */

public class GroupFragment extends BaseFragment {

    public static final String TAG = GroupFragment.class.getSimpleName();

    public static GroupFragment getInstance() {
        return new GroupFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }
}
