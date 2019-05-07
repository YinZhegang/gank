package io.gank.gank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.gank.gank.sort.data.SortWebViewData;
import io.gank.gank.sqlBean.ResourceBean;

public class Webview extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private SortWebViewData data;
    String url;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.webview_toolbar)
    Toolbar webviewToolbar;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        webviewToolbar.inflateMenu(R.menu.web_nav);
        webviewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        webviewToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.collect:
                        Toast.makeText(Webview.this, url, Toast.LENGTH_SHORT).show();
                        ResourceBean resourceBean = new ResourceBean();
                        resourceBean.setCreate(data.getCreatedAt());
                        resourceBean.setPublished(data.getPublishedAt());
                        resourceBean.setSource(data.getSource());
                        resourceBean.setType(data.getType());
                        resourceBean.setDesc(data.getDesc());
                        resourceBean.setUrl(data.getUrl());
                        resourceBean.setWho(data.getWho());
                        ResourceDao.insertResource(resourceBean);

                }
                return false;
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            data = (SortWebViewData) bundle.getSerializable("data");
            Toast.makeText(Webview.this, data.getCreatedAt(), Toast.LENGTH_SHORT).show();
            webview.loadUrl(url = data.getUrl());
            webview.addJavascriptInterface(this, "android");
            webview.setWebChromeClient(new WebChromeClient());
            webview.setWebViewClient(new WebViewClient());
            WebSettings webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(false);
            webSettings.setBuiltInZoomControls(true);
        }
    }

}
