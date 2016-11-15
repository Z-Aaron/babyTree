package io.github.kolacbb.babytree.welcome;

import android.app.FragmentTransaction;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.github.kolacbb.babytree.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private Button mPositiveBtn;
    private Button mNegativeBtn;
    private AccountFragment mAccountFragment = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        // Wire up the fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.welcome_content, new IntroduceFragment());
        fragmentTransaction.commit();

        setupAnimation();
    }

    private void setupAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView iv = (ImageView) findViewById(R.id.logo);
            AnimatedVectorDrawable logoAnim = (AnimatedVectorDrawable) getDrawable(R.drawable.io_logo_white_anim);
            iv.setImageDrawable(logoAnim);
            logoAnim.start();
        }
    }

    public void setPositiveButtonText(String str) {
        mPositiveBtn.setText(str);
    }

    public void setNegativeButtonText(String str) {
        mNegativeBtn.setText(str);
    }

    private void initView() {
        mNegativeBtn = (Button) findViewById(R.id.button_decline);
        mPositiveBtn = (Button) findViewById(R.id.button_accept);
        mNegativeBtn.setOnClickListener(this);
        mPositiveBtn.setOnClickListener(this);
        setPositiveButtonText("下一步");
        setNegativeButtonText("取消");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_accept:
                if (mAccountFragment == null) {
                    mAccountFragment = new AccountFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.welcome_content, mAccountFragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    mAccountFragment.onPositiveButtonClicked();
                }
                break;
            case R.id.button_decline:
                if (mAccountFragment == null) {
                    finish();
                } else {
                    mAccountFragment.onNegativeButtonClicked();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAccountFragment = null;
        setPositiveButtonText("下一步");
        setNegativeButtonText("取消");
    }
}