package io.github.kolacbb.babytree.welcome;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.kolacbb.babytree.R;

/**
 * Created by zhangd on 2016/11/15.
 */

public class IntroduceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.welcome_introduce_fragment, container, false);
    }
}
