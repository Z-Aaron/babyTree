package io.github.kolacbb.babytree.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.model.UserLocation;
import io.github.kolacbb.babytree.util.LocationUtils;

/**
 * Created by zhangd on 2017/6/12.
 */

public class AddOrEditLocationActivity extends BaseActivity {

    private EditText mProvinceEt, mCityEt, mLocationEt, mNameEt, mPhoneEt;

    public static final String KEY_USER_LOCATION = "key_user_locaiton";

    UserLocation mUserLocaiton = new UserLocation();

    public static void start(Context context, UserLocation location) {
        Intent intent = new Intent(context, AddOrEditLocationActivity.class);
        intent.putExtra(KEY_USER_LOCATION, location);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() == null || getIntent().getSerializableExtra(KEY_USER_LOCATION) == null) {
            getSupportActionBar().setTitle("添加地址");
        } else {
            getSupportActionBar().setTitle("修改地址");
            mUserLocaiton = (UserLocation) getIntent().getSerializableExtra(KEY_USER_LOCATION);
        }

        mProvinceEt = (EditText) findViewById(R.id.province);
        mCityEt = (EditText) findViewById(R.id.city);
        mLocationEt = (EditText) findViewById(R.id.location);
        mNameEt = (EditText) findViewById(R.id.name);
        mPhoneEt = (EditText) findViewById(R.id.phone);

        mProvinceEt.setText(mUserLocaiton.getProvince());
        mCityEt.setText(mUserLocaiton.getCity());
        mLocationEt.setText(mUserLocaiton.getLocation());
        mNameEt.setText(mUserLocaiton.getName());
        mPhoneEt.setText(mUserLocaiton.getProvince());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save:
                onSaveBtnClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSaveBtnClicked() {
        mUserLocaiton.setProvince(mProvinceEt.getText().toString());
        mUserLocaiton.setName(mNameEt.getText().toString());
        mUserLocaiton.setPhone(mPhoneEt.getText().toString());
        mUserLocaiton.setCity(mCityEt.getText().toString());
        mUserLocaiton.setLocation(mLocationEt.getText().toString());

        if (mUserLocaiton.getId() == 0) {
            mUserLocaiton.setId(System.currentTimeMillis());
        }

        LocationUtils.saveOrUpdateLocation(mUserLocaiton);
        finish();
    }
}
