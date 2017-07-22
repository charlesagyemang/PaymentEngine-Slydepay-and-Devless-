package finney.charles.com.devlessproject.paymentengine.main;


import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import finney.charles.com.devlessproject.paymentengine.interfaces.SLYDEPAYAPI;
import finney.charles.com.devlessproject.paymentengine.webviewclient.DefaultWeViewClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PayForOrder {

    private String batchId, appUrl;

    public PayForOrder(String batchId, String appUrl) {
        this.batchId = batchId;
        this.appUrl =  appUrl;
    }

    public void getPaymentUrl (String devlessToken, final WebView webView, final ProgressBar progressBar){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appUrl + "/service/slydepay/view/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SLYDEPAYAPI postapi = retrofit.create(SLYDEPAYAPI.class);
        Call<ResponseBody> result = postapi.getCalls("pay?batch_id=" + batchId +"&pay_option=null",
                devlessToken);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String less = response.body().string();
                    Log.e("response", less);
                    JSONObject JO =  new JSONObject(less);
                    String url =  JO.getString("url");
                    Log.e("===url===", url);

                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.loadUrl(url);
                    webView.setWebViewClient(new DefaultWeViewClient(progressBar));



                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String less = t.toString();
                Log.e("error", less);
            }
        });

    }
}
