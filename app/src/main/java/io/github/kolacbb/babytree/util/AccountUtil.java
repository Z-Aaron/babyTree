package io.github.kolacbb.babytree.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import io.github.kolacbb.babytree.model.Account;

/**
 * Created by kolab on 2016/11/13.
 */

public class AccountUtil {
    private static final String TAG = Account.class.getSimpleName();
    private static final String ACCOUNT_INFO = "account_info";

    private static Account mAccount;
    private static Gson mGson;

    private AccountUtil() {}

    static {
        mGson = new Gson();
    }

    private static Account loadAccountInfo() {
        if (mAccount == null) {
            synchronized (AccountUtil.class) {
                if (mAccount == null) {
                    String json = SpUtils.find(ACCOUNT_INFO);
                    mAccount = mGson.fromJson(json, Account.class);
                }
            }
        }
        return mAccount;
    }

    public static void saveAccount(Account account) {
        SpUtils.saveOrUpdate(ACCOUNT_INFO, mGson.toJson(account));
    }

    public static String getAccountId() {
        return loadAccountInfo().getId();
    }

    public static Account getAccount() {
        return loadAccountInfo();
    }

}
