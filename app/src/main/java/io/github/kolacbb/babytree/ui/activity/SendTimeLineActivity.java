package io.github.kolacbb.babytree.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.TimeLine;
import io.github.kolacbb.babytree.ui.adapter.ImagePickerAdapter;
import io.github.kolacbb.babytree.util.PicassoImageLoader;
import io.github.kolacbb.babytree.util.SelectDialog;
import io.github.kolacbb.babytree.util.SpUtils;

/**
 * 发送时光轴模块代码，用户可以在该界面内输入文字，选择图片发送到服务端
 * 之后可以在时光轴模块界面查看已经发送的时光轴
 */
public class SendTimeLineActivity extends AppCompatActivity
        implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private EditText mSendEditText;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    /**
     * 用于初始化布局，绑定监听，等操作
     * 在这个页面中主要做的工作有，加载布局，绑定xml布局到本Activity
     * 初始化选图片控件，初始化标题栏，初始化该Activity中的控件
     * @param savedInstanceState 用于获取在页面销毁时保存到该变量中的数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxdemo);

        //最好放到 Application oncreate执行
        initImagePicker();
        initTitleBar();
        initWidget();
    }

    /**
     * 初始化标题栏，设置左上角的返回按钮，设置标题
     */
    private void initTitleBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("发布时光轴");
    }

    /**
     * 为了兼容老版本的android手机上带的菜单键，标题栏右上角菜单键的为可选择菜单。
     * 所以要使用该方法加载xml中的菜单布局，来为这个页面设置可选择菜单。即发送按钮
     * @param menu 此页面上的菜单
     * @return 是否创建了菜单项
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }

    /**
     * 当标题栏上的按钮被点击的时候，会回调该方法，然后根据MenuItem的id去判断
     * 真正被点击的菜单项是哪一个。
     * 该方法中主要做的事情是，当左上角的箭头被点击的时候，关闭当前页面。
     * 当发送菜单被点击的时候，调用commit方法，向服务端提交数据。
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.send:
                commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 向服务端提交用户填写的数据，比如文本框输入的数据，还有使用图片控件选择的图片数据
     */
    private void commit() {
        TimeLine timeLine = new TimeLine();
        timeLine.setSendTime(new Date());
        timeLine.setContent(mSendEditText.getText().toString());
        String imgs = "";
        for (ImageItem item : adapter.getImages()) {
            imgs += item.path + ",";
        }
        timeLine.setImgs(imgs);

        String timelineJson = SpUtils.find("timeline");
        Gson g = new Gson();
        ArrayList<TimeLine> ts;
        if (TextUtils.isEmpty(timelineJson)) {
            ts = new ArrayList<>();
            ts.add(timeLine);
        } else {
            ts = g.fromJson(timelineJson, new TypeToken<List<TimeLine>>(){}.getType());
            ts.add(timeLine);

        }
        SpUtils.saveOrUpdate("timeline", g.toJson(ts));
        finish();
    }

    /**
     * 初始化选择图片的控件，为选图片的控件设置图片加载器，还有选择图片的数量限制
     * 还有一些选图的细节问题。
     */
    private void initImagePicker() {
        ImagePicker mImgPicke = ImagePicker.getInstance();
        mImgPicke.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        mImgPicke.setShowCamera(true);                      //显示拍照按钮
        mImgPicke.setCrop(true);                           //允许裁剪（单选才有效）
        mImgPicke.setSaveRectangle(true);                   //是否按矩形区域保存
        mImgPicke.setSelectLimit(maxImgCount);              //选中数量限制
        mImgPicke.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        mImgPicke.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        mImgPicke.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        mImgPicke.setOutPutX(1000);                         //保存文件的宽度。单位像素
        mImgPicke.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    /**
     * 初始化当前页面中的控件，比如发送框，还有图片展示框，还要控制图片联动
     * 图片展示框使用了一个recycler view 实现，在这里给recycler view 设置adapter
     */
    private void initWidget() {

        mSendEditText = (EditText) findViewById(R.id.send_edit);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 在点击了选图控件之后选择显示的Dialog
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机

                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(SendTimeLineActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(SendTimeLineActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    /**
     * 用于接收选择图片之后的图片数据，或者预览图片后，图片是否删除的数据，用于更新当前界面
     * 与准备发送到服务端的数据
     * @param requestCode 打开新的Activity的请求码
     * @param resultCode 新的Activity在关闭时，设置的返回码
     * @param data 打开的Activity返回的数据，比如图片数据，是否删除了当前照片的数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }
}
