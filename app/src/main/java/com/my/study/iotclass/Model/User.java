package com.my.study.iotclass.Model;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.my.study.iotclass.Activities.Navigation;
import com.my.study.iotclass.Activities.SelfFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private static String id;
    private static String name;
    private static String stuNum;
    private static String qq;
    private static String phone;
    private static String sex;

    @JavascriptInterface
    public static void setUser(String uid, String uname, String ustuNum, String uqq, String uphone, String  usex) {
        id = uid;
        name = uname;
        stuNum = ustuNum;
        qq = uqq;
        phone = uphone;
        sex = usex;
    }

    @JavascriptInterface
    public static void setUser(String uqq, String uphone) {
        qq = uqq;
        phone = uphone;
    }

    @JavascriptInterface
    public static String getUser() {
        JSONObject object = new JSONObject();
        try {
            object.put("id", id);
            object.put("name", name);
            object.put("stuNum", stuNum);
            object.put("qq", qq);
            object.put("phone", phone);
            object.put("sex", sex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
    @JavascriptInterface
    public static void clearUser() {
        id = null;
        name = null;
        stuNum = null;
        qq = null;
        phone = null;
        sex = null;
    }
}
