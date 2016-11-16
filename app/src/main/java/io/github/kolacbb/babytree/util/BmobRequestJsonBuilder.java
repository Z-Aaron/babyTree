package io.github.kolacbb.babytree.util;

/**
 * Created by zhangd on 2016/11/15.
 */

public class BmobRequestJsonBuilder {

    private String json = "{";

    public BmobRequestJsonBuilder put(String value, String key) {
        if (json.charAt(json.length() - 1) == '\"') {
            json += ",\""+value +"\":\"" + key + "\"";
        } else {
            json += "\""+value +"\":\"" + key + "\"";
        }
        return this;
    }

    public String build() {
        return json + '}';
    }

}
