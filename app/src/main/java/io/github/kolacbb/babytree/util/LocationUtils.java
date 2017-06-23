package io.github.kolacbb.babytree.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.github.kolacbb.babytree.base.TimeLine;
import io.github.kolacbb.babytree.model.UserLocation;

/**
 * Created by zhangd on 2017/6/13.
 */

public class LocationUtils {
    private static final String KEY_LOCATION = "LOCATION";

    public static List<UserLocation> getLocations() {
        String json = SpUtils.find(KEY_LOCATION);
        if (json != null) {
            Gson g = new Gson();
            ArrayList<UserLocation> ts = g.fromJson(json, new TypeToken<List<UserLocation>>(){}.getType());
            return ts;
        }
        return null;
    }

    public static void saveOrUpdateLocation(UserLocation location) {
        List<UserLocation> locations = getLocations();
        if (locations == null) {
            locations = new ArrayList<>();
        }
        int i;
        for (i = 0; i < locations.size(); i++) {
            UserLocation ul = locations.get(i);
            if (ul.getId() == location.getId()) {
                locations.set(i, location);
                break;
            }
        }
        if (i == locations.size()) {
            locations.add(location);
        }
        Gson gson = new Gson();
        SpUtils.saveOrUpdate(KEY_LOCATION, gson.toJson(locations));
    }

    public static void setAsDefaultLocation(UserLocation location) {
        List<UserLocation> locations = getLocations();
        for (UserLocation uul : locations) {uul.setDefaultLocation(false);
            if (uul.getId() == location.getId()) {
                uul.setDefaultLocation(true);
            }
        }
        Gson gson = new Gson();
        SpUtils.saveOrUpdate(KEY_LOCATION, gson.toJson(locations));
    }

    public static UserLocation getDefaultLocation() {
        List<UserLocation> locations = getLocations();
        if (locations != null) {
            for (UserLocation location : locations) {
                if (location.isDefaultLocation()) {
                    return location;
                }
            }
        }
        return null;
    }

    public static void remove(UserLocation location) {
        List<UserLocation> locations = getLocations();
        if (locations == null) {
            return;
        }
        for (int i = 0; i < locations.size(); i++) {
            UserLocation ul = locations.get(i);
            if (ul.getId() == location.getId()) {
                locations.remove(i);
                break;
            }
        }
        Gson gson = new Gson();
        SpUtils.saveOrUpdate(KEY_LOCATION, gson.toJson(locations));
    }
}
