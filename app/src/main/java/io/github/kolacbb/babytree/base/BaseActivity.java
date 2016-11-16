package io.github.kolacbb.babytree.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.github.kolacbb.babytree.util.AccountUtil;
import io.github.kolacbb.babytree.welcome.WelcomeActivity;

/**
 * Created by kolab on 2016/11/5.
 */

public class BaseActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AccountUtil.getAccount() == null) {
//            startActivity(new Intent(this, WelcomeActivity.class));
//            finish();
//        }
    }
}
