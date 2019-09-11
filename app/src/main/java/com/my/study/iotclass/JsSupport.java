package com.my.study.iotclass;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JsSupport {
    private Context context;
    private String json;

    public JsSupport(Context context) {
        this.context = context;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @JavascriptInterface
    public String getJson(){
        return json;
    }

    @JavascriptInterface
    public void showToast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
