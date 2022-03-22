package com.example.stlukesmedicalcentre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        try {
            web = (WebView) findViewById(R.id.webview);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://stlukesmed.co.nz/");
        }catch (Exception e){

        }
    }
}