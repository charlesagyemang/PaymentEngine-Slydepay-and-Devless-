package finney.charles.com.devlessproject.paymentengine.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface SLYDEPAYAPI {
    @POST
    Call<ResponseBody> getCalls(@Url String url, @Header("devless-token") String auth);
}
