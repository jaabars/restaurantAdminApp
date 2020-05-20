package kg.megacom.restaurantAdminApp.okHttp.dishHttpHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import kg.megacom.restaurantAdminApp.models.Dish;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class DishOkHttp {
    public static  DishOkHttp getInstance(){
        return new DishOkHttp();
    }
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient okHttpClient = new OkHttpClient();

    public void  postDish(Dish dish) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),objectMapper.writeValueAsString(dish));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/dish/save")
                .post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            dish=objectMapper.readValue(response.body().string(),Dish.class);
        }
    }
    public List<Dish> getDishes() throws IOException {
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/dish/get").build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            List<Dish> dishList = objectMapper.readValue(response.body().string(), new TypeReference<List<Dish>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return dishList;
        }else {
            return null;
        }
    }
    public void updateDish(Dish dish) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),objectMapper.writeValueAsString(dish));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/dish/update")
                .put(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            dish = objectMapper.readValue(response.body().string(),Dish.class);
        }

    }
}
