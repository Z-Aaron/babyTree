package io.github.kolacbb.babytree.ui.widget;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import io.github.kolacbb.babytree.R;

/**
 * Created by zhangd on 2017/5/21.
 */

public class NightImgView extends FrameLayout {

    GridLayout mGridLayout;
    BaseAdapter mAdapter;
    boolean mCanEdit = false;

    OnClickListener mAddClickListener;

    public NightImgView(@NonNull Context context) {
        this(context, null);
    }

    public NightImgView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NightImgView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_night_imgs, this, true);
        mGridLayout = (GridLayout) findViewById(R.id.gridview);
    }

    public void setCanEdit(OnClickListener addClickListener) {
        mCanEdit = true;
        mAddClickListener = addClickListener;
        mGridLayout.removeAllViews();
        addEmptyView();
    }

    private void addEmptyView() {
        final View root = LayoutInflater.from(getContext()).inflate(R.layout.night_img_item, null);
        ImageView view = (ImageView) root.findViewById(R.id.img);
        view.setImageResource(R.drawable.img_circlebg);
        root.setTag("delete");
        view.setOnClickListener(mAddClickListener);
        mGridLayout.addView(root);
    }

    public void add(Bitmap bitmap) {
        if (mGridLayout.getChildCount() == 10) {
            return;
        }
        final View root = LayoutInflater.from(getContext()).inflate(R.layout.night_img_item, null);
        if (mCanEdit) {
            root.findViewById(R.id.del).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGridLayout.removeView(root);
                    if (mGridLayout.getChildCount() == 0) {
                        addEmptyView();
                    }
                }
            });
        }
        ImageView iv = (ImageView) root.findViewById(R.id.img);
        iv.setImageBitmap(bitmap);
        if (mCanEdit && mGridLayout.getChildCount() == 1 && mGridLayout.getChildAt(0).getTag() != null) {
            mGridLayout.addView(root, mGridLayout.getChildCount() - 1);
        } else {
            mGridLayout.addView(root);
        }

    }

    public void add(String filePath) {
        if (mGridLayout.getChildCount() == 10) {
            return;
        }
        final View root = LayoutInflater.from(getContext()).inflate(R.layout.night_img_item, null);
        if (mCanEdit) {
            root.findViewById(R.id.del).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGridLayout.removeView(root);
                    if (mGridLayout.getChildCount() == 0) {
                        addEmptyView();
                    }
                }
            });
        }
        ImageView iv = (ImageView) root.findViewById(R.id.img);
        Picasso.with(getContext())//
                .load(Uri.fromFile(new File(filePath))).into(iv);
        if (mCanEdit && mGridLayout.getChildCount() == 1 && mGridLayout.getChildAt(0).getTag() != null) {
            mGridLayout.addView(root, mGridLayout.getChildCount() - 1);
        } else {
            mGridLayout.addView(root);
        }

    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
