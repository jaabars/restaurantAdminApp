package kg.megacom.restaurantAdminApp.okHttp.restaurantHttpHelper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import kg.megacom.restaurantAdminApp.models.Restaurant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestaurantOkHttp {
    public  static RestaurantOkHttp getInstance(){
    return new RestaurantOkHttp();
    }
    private ObjectMapper om = new ObjectMapper();
    private OkHttpClient okHttpClient = new OkHttpClient();

    public void postRestaurant(Restaurant restaurant) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),om.writeValueAsString(restaurant));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/restaurant/save")
                .post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
    if(response.isSuccessful()){
    restaurant = om.readValue(response.body().string(), Restaurant.class);
    }
    }
    public List<Restaurant> getRestaurants () throws IOException {
        Request request = new Request.Builder().addHeader("Content-Type","application-json")
                .url("http://localhost:8080/restaurant/get").build();
        Response response = okHttpClient.newCall(request).execute();
        List<Restaurant> restaurantList = new ArrayList<>();
        if (response.isSuccessful()){
            restaurantList = om.readValue(response.body().string(), new TypeReference<List<Restaurant>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return  restaurantList;
        }else{
            return null;
        }

    }
    public void updateRestaurant(Restaurant restaurant) throws IOException {
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),om.writeValueAsString(restaurant));
    Request request = new Request.Builder().addHeader("Content-type","application/json")
            .url("http://localhost:8080/restaurant/update")
            .put(requestBody).build();
    Response response = okHttpClient.newCall(request).execute();
    if (response.isSuccessful()){
        restaurant = om.readValue(response.body().string(), Restaurant.class);
    }

    }
    }
