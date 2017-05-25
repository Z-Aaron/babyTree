package io.github.kolacbb.babytree.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.kolacbb.babytree.R;

/**
 * Created by zhangd on 2017/5/23.
 */

public class StoreAdapter extends  RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sotre_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    private class VH extends RecyclerView.ViewHolder {

        private VH(View itemView) {
            super(itemView);

        }
    }

}
