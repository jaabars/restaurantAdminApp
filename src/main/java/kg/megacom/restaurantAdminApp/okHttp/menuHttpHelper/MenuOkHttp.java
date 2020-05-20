package kg.megacom.restaurantAdminApp.okHttp.menuHttpHelper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import kg.megacom.restaurantAdminApp.models.Menu;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MenuOkHttp {
    public static MenuOkHttp getInstance(){
        return new MenuOkHttp();
    }
    private ObjectMapper om = new ObjectMapper();
    private OkHttpClient okHttpClient = new OkHttpClient();
    public void postMenu(Menu menu) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),om.writeValueAsString(menu));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/menu/save")
                .post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            menu = om.readValue(response.body().string(),Menu.class);
        }
    }
    public List<Menu> getMenus() throws IOException {
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/menu/get").build();
        Response response = okHttpClient.newCall(request).execute();
        List<Menu> menuList = new ArrayList<>();
        if (response.isSuccessful()){
            menuList = om.readValue(response.body().string(), new TypeReference<List<Menu>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            return menuList;
        }else{
            return null;
        }
    }
    public void updateMenu(Menu menu) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),om.writeValueAsString(menu));
        Request request = new Request.Builder().addHeader("Content-Type","application/json")
                .url("http://localhost:8080/menu/update")
                .put(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            menu = om.readValue(response.body().string(),Menu.class);
        }
    }
}
