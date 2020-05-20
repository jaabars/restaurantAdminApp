package kg.megacom.restaurantAdminApp.okHttp.priceHttpHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import kg.megacom.restaurantAdminApp.models.Price;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PriceOkHttp {
    public static PriceOkHttp getInstance(){
        return new PriceOkHttp();
    }
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient okHttpClient = new OkHttpClient();
    public void postPrice (Price price) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(price));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/price/save").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            price = objectMapper.readValue(response.body().string(), Price.class);
        }
    }
    public List<Price> getPriceList() throws IOException {
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/price/get").build();
        Response response = okHttpClient.newCall(request).execute();
        List<Price> priceList = new ArrayList<>();
        if (response.isSuccessful()){
            priceList = objectMapper.readValue(response.body().string(), new TypeReference<List<Price>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return priceList;
        }
        else {
            return priceList;
        }
    }
    public void updatePrice(Price price) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("applcation/json"),objectMapper.writeValueAsString(price));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/price/update").put(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            price = objectMapper.readValue(response.body().string(),Price.class);
        }
    }
}
