package io.github.kolacbb.babytree.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.util.CommodityUtils;

/**
 * Created by zhangd on 2017/5/23.
 */

public class StoreAdapter extends  RecyclerView.Adapter {

    List<Commodity> mCommodities;
    Context mCtx;

    public void setmCommodities(List<Commodity> mCommodities) {
        this.mCommodities = mCommodities;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mCtx = parent.getContext();
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sotre_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Commodity co = mCommodities.get(position);
        VH vh = (VH) holder;

        vh.mPrice.setText("价格：" + String.valueOf(co.getPrice()));
        vh.mTitle.setText(co.getTitle());
        Picasso.with(mCtx).load(co.getImg()).into(vh.mImg);
        vh.mMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommodityUtils.addComondityToShoppingCart(co);
                Toast.makeText(mCtx, "加入购物车成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommodities == null ? 0 : mCommodities.size();
    }

    private class VH extends RecyclerView.ViewHolder {

        ImageView mImg;
        TextView mTitle;
        TextView mPrice;
        ImageView mMoreImg;
        private VH(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mPrice = (TextView) itemView.findViewById(R.id.content);
            mMoreImg = (ImageView) itemView.findViewById(R.id.more_menu);
        }
    }

}
