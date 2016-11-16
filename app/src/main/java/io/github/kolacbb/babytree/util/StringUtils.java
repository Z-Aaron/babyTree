package io.github.kolacbb.babytree.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhangd on 2016/11/15.
 */

public class StringUtils {
    public static String encodeGB2312(String str) {
        try {
            return URLEncoder.encode(str, "GB2312");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String encodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
}
