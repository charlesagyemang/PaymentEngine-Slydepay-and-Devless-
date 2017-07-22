package finney.charles.com.devlessproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.util.Map;

import androidsdk.devless.io.devless.interfaces.PostDataResponse;
import androidsdk.devless.io.devless.main.Devless;
import androidsdk.devless.io.devless.messages.ErrorMessage;
import androidsdk.devless.io.devless.messages.ResponsePayload;
import finney.charles.com.devlessproject.R;
import finney.charles.com.devlessproject.paymentengine.builder.PaymentBodyBuilder;
import finney.charles.com.devlessproject.paymentengine.main.PayForOrder;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        webView =  (WebView)findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        final String devlessToken = "1622f3b1f2ef6cd42ee4e739579eb523";
        final String appUrl       = "http://10.0.2.2:7070";
        String slyPay       = "slydepay";
        String configTable  = "config";
        String ordersTable  = "orders";


        Devless devless = new Devless(this, appUrl, devlessToken);
        devless.addUserToken(sp);

        // USAGE
        // INDIVIDUAL ORDERS
        String orderOne = PaymentBodyBuilder.formOrderBody("2342474", "Koobi ads", "300", "7");
        String orderTwo = PaymentBodyBuilder.formOrderBody("2342474", "Koobi ads", "300", "7");
        // FINAL ORDER
        String fullOrder =  orderOne + "," + orderTwo;

        //BATCH_ID
        final String batchId = "2345594";

        // DATE
        String date =  "2017-07-22";

        //CREATE THE POST HASH MAP

        Map<String, Object> orderDetails = PaymentBodyBuilder.formOrdersTablePostBody(batchId, fullOrder,date );


        // POST TO THE ORDERS TABLE ON THE DVELESS SERVICE
        devless.postData(slyPay, ordersTable, orderDetails, new PostDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload responsePayload) {
                Log.e("response", responsePayload.toString());
                PayForOrder pay =  new PayForOrder(batchId, appUrl);
                pay.getPaymentUrl(devlessToken, webView, progressBar);
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                Log.e("error", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {

            }
        });


    }


}
