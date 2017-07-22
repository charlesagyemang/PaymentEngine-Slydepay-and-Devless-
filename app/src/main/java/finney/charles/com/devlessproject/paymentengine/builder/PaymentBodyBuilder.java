package finney.charles.com.devlessproject.paymentengine.builder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pianoafrik on 7/22/17.
 */

public class PaymentBodyBuilder {


    public static String formOrderBody (String id, String name, String price, String quantity)
    {
        JSONObject JO =  new JSONObject();
        try {
            JO.put("id", id);
            JO.put("name", name);
            JO.put("price", price);
            JO.put("quantity", quantity);

            return  String.valueOf(JO);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, Object>  formOrdersTablePostBody(String batchId, String orders, String date)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("batch_id", batchId);
        params.put("orders", "[" + orders + "]");
        params.put("date", date);
        params.put("status", "0");
        params.put("processed", 0);

        return params;

    }
}
