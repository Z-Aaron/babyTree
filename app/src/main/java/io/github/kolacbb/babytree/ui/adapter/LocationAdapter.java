package io.github.kolacbb.babytree.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.UserLocation;

/**
 * Created by zhangd on 2017/6/13.
 */

public class LocationAdapter extends BaseAdapter {
    List<UserLocation> locations;

    public List<UserLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<UserLocation> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return locations == null ? 0 : locations.size();
    }

    @Override
    public UserLocation getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, null);
        TextView defaultTv = (TextView) root.findViewById(R.id.default_location);
        TextView nameTv = (TextView) root.findViewById(R.id.name);
        TextView locationTv = (TextView) root.findViewById(R.id.location);

        UserLocation ul = getItem(position);
        defaultTv.setVisibility(ul.isDefaultLocation() ? View.VISIBLE : View.GONE);
        nameTv.setText(ul.getName() + "    " + ul.getPhone());
        locationTv.setText(ul.getProvince() + ul.getCity() + ul.getLocation());

        return root;
    }
}
