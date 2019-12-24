package com.my.study.iotclass.Activities;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.my.study.iotclass.Model.User;
import com.my.study.iotclass.R;

public class HomeFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        WebView webView = view.findViewById(R.id.HomeFrag);
        //解决点击链接跳转浏览器问题
        webView.setWebViewClient(new WebViewClient());
        //js支持
        WebSettings settings = webView.getSettings();
        // 允许高版本http和https混合访问
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置网络安排
        settings.setAllowUniversalAccessFromFileURLs(true);
        //添加user接口
        webView.addJavascriptInterface(new User(), "user");
        //assets文件路径
        String path = "file:///android_asset/home.html";
        //加载Html页面
        webView.loadUrl(path);

        return view;
    }

}
