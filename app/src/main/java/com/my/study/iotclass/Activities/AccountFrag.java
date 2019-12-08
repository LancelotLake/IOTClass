package com.my.study.iotclass.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.my.study.iotclass.R;


public class AccountFrag extends Fragment {
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
        settings.setJavaScriptEnabled(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //assets文件路径
        String path = "file:///android_asset/account.html";
        //加载Html页面
        webView.loadUrl(path);
        return view;
    }
}
