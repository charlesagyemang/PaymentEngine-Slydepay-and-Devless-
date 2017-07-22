package finney.charles.com.devlessproject.paymentengine.webviewclient;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class DefaultWeViewClient extends WebViewClient {

    private ProgressBar progressBar;

    public DefaultWeViewClient(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        progressBar.setVisibility(View.GONE);
        super.onPageFinished(view, url);


    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        progressBar.setVisibility(View.VISIBLE);
        super.onPageStarted(view, url, favicon);


    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        view.loadUrl("javascript:document.getElementById('site-navigation').style.display='none'; " +
                "document.getElementById('post-4').style.display='none'; "+
                "document.getElementById('actionbar').style.display='none'; "+
                "document.getElementById('colophon').style.display='none'; "+
                "void(0);");
    }


    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

        view.loadUrl("about:blank");
//        errorText.setVisibility(View.VISIBLE);
//        errorLink.setVisibility(View.VISIBLE);
//        Toast.makeText(MainActivity.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
        super.onReceivedError(view, errorCode, description, failingUrl);

    }
}
