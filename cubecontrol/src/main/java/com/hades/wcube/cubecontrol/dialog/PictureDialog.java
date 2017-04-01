package com.hades.wcube.cubecontrol.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hades.wcube.cubecontrol.R;


public class PictureDialog extends Dialog {

    private static String gurl = null;
    private WebView webview = null;

    Context context;


    public interface OnCustomDialogListener {
         void back(String name);
    }

    public PictureDialog(Context context, String url) {
        super(context, R.style.AppTheme);
        this.context = context;
        this.gurl = url;


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void colseDialog() {
        PictureDialog.this.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_exam_layout);

        webview = (WebView) findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                Toast.makeText(context,"ll",Toast.LENGTH_SHORT).show();
                if (url.contains("action=close")) {
                    colseDialog();
                    return true;
                } else if (url.contains("action=share")) {
                    webview.loadUrl(url);
                    return false;
                }
                return true;
            }
            
        });


        webview.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int newProgress)
            {

            }
        });
        

        if (webview != null && gurl != null && gurl.length() > 0) {
            try {
                WebSettings webSettings = webview.getSettings();
                webSettings.setSavePassword(false);
                webSettings.setSaveFormData(false);
                webSettings.setJavaScriptEnabled(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setSupportZoom(true);
                webSettings.setDomStorageEnabled(true);

//                webSettings.setAllowFileAccessFromFileURLs(true);
//                webSettings.setAllowUniversalAccessFromFileURLs(true);
//                webSettings.setAllowFileAccess(true);

                webview.clearSslPreferences();
                webview.setWebViewClient(new webViewClient());
                webview.setWebChromeClient(new MyWebChromeClient());
                webview.loadUrl(gurl);


                webview.clearSslPreferences();
                webview.setWebViewClient(new webViewClient());


            } catch (Throwable t) {
            }
        }

        webview.setDownloadListener(new MyWebViewDownLoadListener());

    }


    class webViewClient extends WebViewClient {
        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开�?  
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            WebView.HitTestResult hit = view.getHitTestResult();
            if (hit != null) {
                int hitType = hit.getType();
                if (hitType == WebView.HitTestResult.SRC_ANCHOR_TYPE) {//点击超链�?
                    //这里执行自定义的操
                    
                    if (url.contains("action=close")) {
                        colseDialog();
                        view.loadUrl(url);
                    }
                    return true;//返回true浏览器不再执行默认的操作
                } else if (hitType == 0) {//重定向时hitType�?0
                    return false;//不捕�?302重定�?
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * debugging your javascript.
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }
}
