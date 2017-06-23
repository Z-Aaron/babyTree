package io.github.kolacbb.babytree.model;

import java.io.Serializable;

/**
 * Created by zhangd on 2017/6/13.
 */

public class UserLocation implements Serializable{
    private long id;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String location;
    private boolean defaultLocation;

    public boolean isDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(boolean defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
