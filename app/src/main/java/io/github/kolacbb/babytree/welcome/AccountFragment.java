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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import io.github.kolacbb.babytree.R;

public class AccountFragment extends Fragment {
    private static final String TAG = AccountFragment.class.getSimpleName();

    private EditText mEmailText;
    private EditText mPasswordText;
    private EditText mNameText;
    private EditText mDescText;

    private static final int LOGIN_MODE = 0;
    private static final int SIGN_UP_MODE = 1;

    private int mState = SIGN_UP_MODE;

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

    public void initView(View view) {
        mEmailText = (EditText) view.findViewById(R.id.email);
        mPasswordText = (EditText) view.findViewById(R.id.password);
        mNameText = (EditText) view.findViewById(R.id.name);
        mDescText = (EditText) view.findViewById(R.id.desc);
    }

    public void onPositiveButtonClicked() {
        if (mState == SIGN_UP_MODE) {
            Toast.makeText(getActivity(), "注册", Toast.LENGTH_SHORT).show();
        } else {
            mState = SIGN_UP_MODE;
            updateViewState();
        }
    }

    public void onNegativeButtonClicked() {
        if (mState == LOGIN_MODE) {
            Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
        } else {
            mState = LOGIN_MODE;
            updateViewState();
        }

    }

    private void updateViewState() {
        if (mState == LOGIN_MODE) {
            mNameText.setVisibility(View.GONE);
            mDescText.setVisibility(View.GONE);
        } else if (mState == SIGN_UP_MODE) {
            mNameText.setVisibility(View.VISIBLE);
            mDescText.setVisibility(View.VISIBLE);
        }
    }
}
