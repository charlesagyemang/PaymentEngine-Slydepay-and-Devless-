package finney.charles.com.devlessproject.paymentengine.interfaces;

/**
 * Created by pianoafrik on 7/22/17.
 */

public interface OnPaymentResponse {

    void onSuccess (String url);
    void onFailure (String error);
}
