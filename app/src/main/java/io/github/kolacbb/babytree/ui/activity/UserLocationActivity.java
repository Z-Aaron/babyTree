package io.github.kolacbb.babytree.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.model.UserLocation;
import io.github.kolacbb.babytree.ui.adapter.LocationAdapter;
import io.github.kolacbb.babytree.util.LocationUtils;

/**
 * Created by zhangd on 2017/5/22.
 */

public class UserLocationActivity extends BaseActivity {

    ListView mListView;
    LocationAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);
        getSupportActionBar().setTitle("地址管理");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add button
        findViewById(R.id.add_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLocationActivity.this, AddOrEditLocationActivity.class));
            }
        });
        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new LocationAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddOrEditLocationActivity.start(UserLocationActivity.this, mAdapter.getItem(position));
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final UserLocation ul = mAdapter.getItem(position);
                new AlertDialog.Builder(UserLocationActivity.this)
                        .setItems(new String[]{"设为默认地址", "删除"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    LocationUtils.setAsDefaultLocation(ul);
                                } else {
                                    LocationUtils.remove(ul);
                                }
                                mAdapter.setLocations(LocationUtils.getLocations());
                            }
                        })
                .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setLocations(LocationUtils.getLocations());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
