package io.github.kolacbb.babytree;

import android.app.Application;
import android.preference.PreferenceManager;

//import com.tencent.bugly.crashreport.CrashReport;

import io.github.kolacbb.babytree.util.SpUtils;

/**
 * Created by kolab on 2016/11/13.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 腾讯 bugly
//        CrashReport.initCrashReport(getApplicationContext(), "25ac536135", false);

        SpUtils.init(PreferenceManager.getDefaultSharedPreferences(this));
    }
}
