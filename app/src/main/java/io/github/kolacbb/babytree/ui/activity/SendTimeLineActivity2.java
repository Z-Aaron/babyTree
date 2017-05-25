package io.github.kolacbb.babytree.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.BaseActivity;
import io.github.kolacbb.babytree.ui.widget.NightImgView;
import io.github.kolacbb.babytree.util.PicassoImageLoader;

/**
 * Created by zhangd on 2017/5/21.
 */

public class SendTimeLineActivity2 extends BaseActivity {

    EditText mContent;
    NightImgView mNightImgView;

    public static final int IMAGE_PICKER = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_time_line);
        mContent = (EditText) findViewById(R.id.content);
        mNightImgView = (NightImgView) findViewById(R.id.night_img_view);
        mNightImgView.setCanEdit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendTimeLineActivity2.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        //imagePicker.setCrop(true);        //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
//        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (ImageItem img : images) {
                    mNightImgView.add(img.path);
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
