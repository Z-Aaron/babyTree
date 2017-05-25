package io.github.kolacbb.babytree.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.base.TimeLine;
import io.github.kolacbb.babytree.ui.adapter.TimeLineAdapter;
import io.github.kolacbb.babytree.util.SpUtils;

/**
 * Created by kolab on 2017/5/7.
 */

public class UserCircleActivity extends BaseActivity {

    private TimeLineAdapter mAdapter;
    private ListView mListView;
    boolean mIsMy = false;

    /**
     * 用于初始化布局，绑定监听，等操作
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_circle);

        mIsMy = getIntent().getBooleanExtra("isMy", false);
        if (mIsMy) {
            getSupportActionBar().setTitle("我的相册");
        } else {
            getSupportActionBar().setTitle("宝宝时光轴");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.list_view);
        ImageView userIcon = (ImageView) findViewById(R.id.user_img);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCircleActivity.this, UserCircleActivity.class);
                intent.putExtra("isMy", true);
                startActivity(intent);
            }
        });

        mAdapter = new TimeLineAdapter();
        mListView.setAdapter(mAdapter);

        loadDate();

    }

    /**
     * 用于加载数据
     */
    private void loadDate() {
        String timelineJson = SpUtils.find("timeline");
        Gson g = new Gson();
        ArrayList<TimeLine> ts = g.fromJson(timelineJson, new TypeToken<List<TimeLine>>(){}.getType());
        mAdapter.setTimeLineDate(ts);
    }

    /**
     * 在页面恢复时，重新假造数据
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadDate();
    }

    /**
     * 用于设置状态栏两个按钮的监听，返回上一个界面，与跳转到发送界面
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.send:
                startActivity(new Intent(this, SendTimeLineActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 用于创建右上角的发送按钮，作为一个可选择菜单项
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_send_timeline, menu);
        return true;
    }

}
