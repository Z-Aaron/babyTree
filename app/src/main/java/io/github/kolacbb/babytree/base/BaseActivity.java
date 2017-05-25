package io.github.kolacbb.babytree.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import io.github.kolacbb.babytree.util.AccountUtil;
import io.github.kolacbb.babytree.welcome.WelcomeActivity;

/**
 * 该 Activity 内用于防止所有页面通用共有的方法，如显示隐藏加载dialog
 * 统一处理登录逻辑，如果没有登录，就直接finish掉当前页面，打开登录页面
 * Created by kolab on 2016/11/5.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AccountUtil.getAccount() == null) {
//            startActivity(new Intent(this, WelcomeActivity.class));
//            finish();
//        }
    }

    /**
     * 显示加载的dialog
     */
    public void showBaseLoadingDialog() {

    }

    /**
     * 隐藏加载的dialog
     */
    public void hideBaseLoadingDialog() {

    }
}
