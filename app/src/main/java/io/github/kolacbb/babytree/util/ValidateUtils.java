package io.github.kolacbb.babytree.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016/11/16.
 */
public class ValidateUtils {

    /**
     * 判断用户名，是否合法
     * 姓名最多10个字，允许中文、英文和数字下划线
     */
    public static boolean validateUserName(String name) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5|[A-Za-z0-9_]|]{1,10}");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断邮箱是否合法
     */
    public static boolean validateEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断手机是否合法
     * @param phoneNumber
     * @return
     */
    public static boolean validateMobile(String phoneNumber) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");//1(3|4|5|7|8)[0-9]{9}
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断密码是否合法
     * 1.长度：8-16位，2.数字、字母、字符至少包含两种
     * @param str
     * @return
     */
    public static boolean validatePassWord(String str){
        String check="((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()){
            return true;
        }else {
            return false;
        }
    }
     public static boolean isEmptyStr(String str) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

}
