package io.github.kolacbb.babytree.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseFragment;
import io.github.kolacbb.babytree.model.Account;
import io.github.kolacbb.babytree.ui.activity.UserCircleActivity;
import io.github.kolacbb.babytree.ui.activity.UserLocationActivity;
import io.github.kolacbb.babytree.util.AccountUtil;

/**
 * 我模块的Fragment 继承自BaseFragment，BaseFragment中封装了一部分方法，使Fragment更加易于使用
 * Created by kolab on 2016/11/6.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = MyFragment.class.getSimpleName();

    /**
     * 外部使用该Fragment时，获取该Fragment实例的方法
     * 在这里不推荐使用在外部使用new 关键字获取该实例
     * @return: MyFragment 的一个新的实例
     */
    public static MyFragment getInstance() {
        return new MyFragment();
    }

    /**
     * 由BaseFragment 封装的方法
     * 用于获取与该Fragment中绑定的xml视图，该方法主要由BaseFragment使用
     * @return: layout id
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
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
        mRootView.findViewById(R.id.user_circle).setOnClickListener(this);
        mRootView.findViewById(R.id.user_location).setOnClickListener(this);
        mRootView.findViewById(R.id.logout).setOnClickListener(this);

        TextView name = (TextView) mRootView.findViewById(R.id.user_name);
        TextView desc = (TextView) mRootView.findViewById(R.id.user_desc);

        Account account = AccountUtil.getAccount();
        name.setText(account.getName());
        desc.setText(account.getDesc());
    }

    /**
     * 该方法为控件注册的监听，当布局中的控件被点击之后，该方法会被调用
     * 通过获取View中的Id来判断真正被点击的控件，从而执行下一步操作
     * 该方法完成的动作，在时光周条目被点击后跳转到时光轴页面
     * 在我的地址管理模块被点击后，跳转到我的地址管理界面
     * @param v 被点击的View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_circle:
                startActivity(new Intent(getContext(), UserCircleActivity.class));
                break;
            case R.id.user_location:
                startActivity(new Intent(getContext(), UserLocationActivity.class));
                break;
            case R.id.logout:
                AccountUtil.logout();
                getActivity().finish();
                break;
        }
    }
}
