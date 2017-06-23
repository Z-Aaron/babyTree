package io.github.kolacbb.babytree.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.Article;

/**
 * 用于展示Feed页面的Adapter，可以展示三种不不同的布局方式
 * 用于业务务求扩展
 * Created by kolab on 2016/11/6.
 */
public class HomeAdapter extends RecyclerView.Adapter {

    private static final int TYPE_IMAGE = 0x30;
    private static final int TYPE_BOX_MENU = 0x31;
    private static final int TYPE_ARTICLE = 0x32;

    private Context mCtx;
    private LayoutInflater mInflater;

    List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    /**
     * 在adapter创建时，需要传入context用来初始化
     * @param context 上下文环境
     */
    public HomeAdapter(Context context) {
        mCtx = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 创建view holder的方法，view holder可以加快item加载速度
     * find view 操作是一个比较耗时的操作
     * 使用view holder 可以有效的减少find view 的耗时
     * @param parent item的parent view
     * @param viewType view 的类型，不用的view 使用不同的view holder
     * @return 创建出的view holder
     */
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

    /**
     * 用于绑定不同的view 和view holder
     * 1. 通过position 判断该view hold 的类型
     * 2. 获取数据，将数据与view holder 中的类型绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadImageViewHolder) {

        } else if (holder instanceof MenuViewHolder) {

        } else {
            Article article = articles.get(position);
            ArticleViewHolder vh = (ArticleViewHolder) holder;
            vh.title.setText(article.getTitle());
            Picasso.with(mCtx).load(article.getImage()).into(vh.image);
        }
    }

    /**
     * 获取item的数量，一般为list的数量，当list为null时，返回数量应为0
     * @return item总数
     */
    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    /**
     * 根据位置获取该位置的类型
     * @param position item的位置
     * @return 类型
     */
    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TYPE_IMAGE;
//        } else if (position == 1) {
//            return TYPE_BOX_MENU;
//        } else {
            return TYPE_ARTICLE;
//        }
    }

    /**
     * Feed页展示头图的View holder 类型为TYPE_IMAGE
     */
    private class HeadImageViewHolder extends RecyclerView.ViewHolder {
        ViewPager pager;
        HeadImageViewHolder(View itemView) {
            super(itemView);
            pager = (ViewPager) itemView.findViewById(R.id.view_pager);
        }
    }

    /**
     * Feed 也main用于展示各种类型不同type的viewholder
     * 可以增加不同的功能选项
     */
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

    /**
     * Feed页母婴文章的view holder 类型为TYPE_ARTICLE
     */
    private class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        ArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    /**
     * 用于头图滚动的pager adapter
     */
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
