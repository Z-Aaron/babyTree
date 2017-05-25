/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kolacbb.babytree.welcome;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.Account;
import io.github.kolacbb.babytree.model.ResponsBody;
import io.github.kolacbb.babytree.model.Result;
import io.github.kolacbb.babytree.net.RetrofitManager;
import io.github.kolacbb.babytree.net.service.AccountService;
import io.github.kolacbb.babytree.ui.activity.HomeActivity;
import io.github.kolacbb.babytree.util.AccountUtil;
import io.github.kolacbb.babytree.util.BmobRequestJsonBuilder;
import io.github.kolacbb.babytree.util.StringUtils;
import io.github.kolacbb.babytree.util.ValidateUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 显示登录注册的界面，调用登录注册的接口，完成登录注册相关逻辑
 */
public class AccountFragment extends Fragment {
    private static final String TAG = AccountFragment.class.getSimpleName();

    private EditText mEmailText;
    private EditText mPasswordText;
    private EditText mNameText;
    private EditText mDescText;

    private static final int LOGIN_MODE = 0;
    private static final int SIGN_UP_MODE = 1;

    private int mState = SIGN_UP_MODE;

    /**
     * 初始化注册登录界面，加载xml布局，初始化相关控件注册监听。
     * @param inflater Inflater 加载器
     * @param container 父容器
     * @param savedInstanceState 界面被销毁时所保存的数据
     * @return 加载的当前fragment的跟布局
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        WelcomeActivity activity = (WelcomeActivity) getActivity();
        activity.setNegativeButtonText("登录");
        activity.setPositiveButtonText("注册");
        View view = inflater.inflate(R.layout.welcome_account_fragment, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件，从xml布局中通过id获取控件实例
     * @param view
     */
    public void initView(View view) {
        mEmailText = (EditText) view.findViewById(R.id.email);
        mPasswordText = (EditText) view.findViewById(R.id.password);
        mNameText = (EditText) view.findViewById(R.id.name);
        mDescText = (EditText) view.findViewById(R.id.desc);
    }

    /**
     * 当用户点击了注册按钮时，调用该方法进行注册信息校验
     * 如：邮箱格式，密码格式，用户名格式
     * 校验通过则调用调用注册接口进行用户注册
     */
    public void onPositiveButtonClicked() {
        if (mState == SIGN_UP_MODE) {

           if (!ValidateUtils.validateEmail(mEmailText.getText()+"")){
               Toast.makeText(getActivity(), "邮箱格式不正确！", Toast.LENGTH_SHORT).show();
           }else if (!ValidateUtils.validatePassWord(mPasswordText.getText()+"")){
               Toast.makeText(getActivity(), "密码格式不正确！", Toast.LENGTH_SHORT).show();
           }else if (!ValidateUtils.validateUserName(mNameText.getText()+"")){
               Toast.makeText(getActivity(), "用户名格式不正确！", Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(getActivity(), "注册", Toast.LENGTH_SHORT).show();
           }

            if (!TextUtils.isEmpty(mEmailText.getText().toString())
                    && !TextUtils.isEmpty(mPasswordText.getText().toString())) {
                signUp(mEmailText.getText().toString(), mPasswordText.getText().toString(),
                        mNameText.getText().toString(), mDescText.getText().toString());
            } else {
                Toast.makeText(getActivity(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            mState = SIGN_UP_MODE;
            updateViewState();
        }
    }
    /**
     * 当用户点击了登录按钮时，调用该方法进行登录信息校验
     * 如：密码格式
     * 校验通过则调用调用登录接口进行用户登录
     */
    public void onNegativeButtonClicked() {
        if (mState == LOGIN_MODE) {
            Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();

            if (!TextUtils.isEmpty(mEmailText.getText().toString())
                    && !TextUtils.isEmpty(mPasswordText.getText().toString())) {
                login(mEmailText.getText().toString(), mPasswordText.getText().toString());
            } else {
                Toast.makeText(getActivity(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            mState = LOGIN_MODE;
            updateViewState();
        }

    }

    /**
     * 该方法用于注册界面与登录页面的切换
     */
    private void updateViewState() {
        if (mState == LOGIN_MODE) {
            mNameText.setVisibility(View.GONE);
            mDescText.setVisibility(View.GONE);
        } else if (mState == SIGN_UP_MODE) {
            mNameText.setVisibility(View.VISIBLE);
            mDescText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 登录接口调用
     * @param email 用户注册时的邮箱，即用户账户
     * @param password 用户注册时的密码
     */
    private void login(String email, String password) {
        String queryJson = new BmobRequestJsonBuilder()
                .put("email", email)
                .put("password", password)
                .build();

        RetrofitManager.create(AccountService.class)
                .query(StringUtils.bombEncode(queryJson))
                .enqueue(new Callback<Result<Account>>() {
                    @Override
                    public void onResponse(Call<Result<Account>> call, Response<Result<Account>> response) {
                        Result<Account> result = response.body();
                        if (result.getResults() == null || result.getResults().size() == 0) {
                            Toast.makeText(getActivity(), "账号密码不匹配", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Account account = result.getResults().get(0);
                        AccountUtil.saveAccount(account);
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(Call<Result<Account>> call, Throwable throwable) {
                        Toast.makeText(getActivity(), "请求网络失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 用户注册接口调用
     * @param email 用户邮箱，即用户账户
     * @param password 用户密码
     * @param name 用户姓名
     * @param desc 用户描述
     */
    private void signUp(String email, String password, String name, String desc) {
        // 待注册账号，返回结果只返回objectId，不必再次访问网络
        final Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setName(name);
        account.setDesc(desc);

        String accountJson = new BmobRequestJsonBuilder()
                .put("email", email)
                .put("password", password)
                .put("name", name)
                .put("desc", desc)
                .build();

        Gson gson = new Gson();
        Log.e(TAG, "signUp, accountJson: " + accountJson);
        Log.e(TAG, "signUp, gson: " + gson.toJson(account));

        RetrofitManager.create(AccountService.class)
                .signup(account)
                .enqueue(new Callback<ResponsBody>() {
                    @Override
                    public void onResponse(Call<ResponsBody> call, Response<ResponsBody> response) {
                        ResponsBody body = response.body();
                        if (body == null) {
                            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        account.setId(body.getObjectId());
                        AccountUtil.saveAccount(account);
                    }

                    @Override
                    public void onFailure(Call<ResponsBody> call, Throwable throwable) {
                        Toast.makeText(getActivity(), "请求网络失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
