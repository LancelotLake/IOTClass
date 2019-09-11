package com.my.study.iotclass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WebView mWebView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    private void addJson() {
        JsSupport jsSupport = new JsSupport(this);
        List<FriendsZone> zones = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            zones.add(new FriendsZone("鹿鹿" + i, "images/icon.png", "这里是Html测试数据, 这里是Html测试数据, 这里是Html测试数据" + i));
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject tmpObj = null;

        int count = zones.size();
        for(int i = 0; i < count; i++) {
            tmpObj = new JSONObject();

            try {
                tmpObj.put("name" , zones.get(i).getName());
                tmpObj.put("icon", zones.get(i).getIcon());
                tmpObj.put("content", zones.get(i).getContent());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(tmpObj);
            tmpObj = null;
        }


        String json = jsonArray.toString();
//        Log.d(TAG, "addJson: json => " + json);
        jsSupport.setJson(json);
        //添加js交互接口, 并指明js中对象的调用名称
        mWebView.addJavascriptInterface(jsSupport, "weichat");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mWebView = (WebView) findViewById(R.id.test_web_view);
//解决点击链接跳转浏览器问题
        mWebView.setWebViewClient(new WebViewClient());
//js支持
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
//允许访问assets目录
        settings.setAllowFileAccess(true);
//设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//assets文件路径
        String path = "file:///android_asset/index.html";
//添加Json数据
        addJson();
//加载Html页面
        mWebView.loadUrl(path);


    }

}
