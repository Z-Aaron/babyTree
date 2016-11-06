package io.github.kolacbb.babytree.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import io.github.kolacbb.babytree.R;

/**
 * Created by kolab on 2016/11/6.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private static final int TYPE_IMAGE = 0x30;
    private static final int TYPE_BOX_MENU = 0x31;
    private static final int TYPE_ARTICLE = 0x32;

    private Context mCtx;
    private LayoutInflater mInflater;

    public HomeAdapter(Context context) {
        mCtx = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMAGE) {
            return new HeadImageViewHolder(mInflater.inflate(R.layout.item_head_image_view, parent, false));
        } else if (viewType == TYPE_BOX_MENU) {
            return new MenuViewHolder(mInflater.inflate(R.layout.item_box_menu_view, parent, false));
        } else {
            return new ArticleViewHolder(mInflater.inflate(R.layout.item_article_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadImageViewHolder) {

        } else if (holder instanceof MenuViewHolder) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_IMAGE;
        } else if (position == 1) {
            return TYPE_BOX_MENU;
        } else {
            return TYPE_ARTICLE;
        }
    }

    private class HeadImageViewHolder extends RecyclerView.ViewHolder {
        ViewPager pager;
        HeadImageViewHolder(View itemView) {
            super(itemView);
            pager = (ViewPager) itemView.findViewById(R.id.view_pager);
        }
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        View calendarBtn; // 女性日历
        View diaryBtn; // 时光轴日历
        View queryBtn; // 在线专家
        MenuViewHolder(View itemView) {
            super(itemView);
            calendarBtn = itemView.findViewById(R.id.calendar_btn);
            diaryBtn = itemView.findViewById(R.id.diary_btn);
            queryBtn = itemView.findViewById(R.id.query_btn);
        }
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        ArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    private class ImagePagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

}
