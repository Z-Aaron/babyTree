package io.github.kolacbb.babytree.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.base.TimeLine;
import io.github.kolacbb.babytree.ui.widget.NightImgView;
import io.github.kolacbb.babytree.util.StringUtils;

/**
 * 时光轴的Adapter，用于显示时光轴数据列表项中的每一个item
 * Created by zhangd on 2017/5/23.
 */

public class TimeLineAdapter extends BaseAdapter {

    List<TimeLine> mTimeLines;

    /**
     * 重新设置当前数据流
     * @param tm 新的数据集合
     */
    public void setTimeLineDate(List<TimeLine> tm) {
        mTimeLines = tm;
        notifyDataSetChanged();
    }

    /**
     * 获取当前列表中，共有多少条数据
     * @return 数量
     */
    @Override
    public int getCount() {
        return mTimeLines == null ? 0 : mTimeLines.size();
    }

    /**
     * 获取在position位置的数据
     * @param position
     * @return
     */
    @Override
    public TimeLine getItem(int position) {
        return mTimeLines.get(getCount() - position -1);
    }

    /**
     * 获取每个item的id
     * @param position 当前的位置
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取Item布局，随后对该布局进行操作
     * @param position 当前的位置
     * @param convertView 缓存的view
     * @param parent 当前布局的父布局
     * @return 在position位置创建出的新的View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_line, null);

        TextView date = (TextView) root.findViewById(R.id.date);
        TextView content = (TextView) root.findViewById(R.id.content);
        NightImgView imgs = (NightImgView) root.findViewById(R.id.imgs);

        TimeLine tl = getItem(position);
        date.setText(tl.getSendTime().getMonth() + "/" + tl.getSendTime().getDay());
        content.setText(tl.getContent());
        if (!TextUtils.isEmpty(tl.getImgs())) {
            String[] imgPaths = tl.getImgs().split(",");
            for (String path : imgPaths) {
                if (!TextUtils.isEmpty(path))
                    imgs.add(path);
            }
        }
        return root;
    }
}
