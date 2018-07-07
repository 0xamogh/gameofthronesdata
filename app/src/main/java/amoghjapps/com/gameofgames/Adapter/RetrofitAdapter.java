package amoghjapps.com.gameofgames.Adapter;

import java.util.List;

import amoghjapps.com.gameofgames.Model.ModelClass;
import amoghjapps.com.gameofgames.Model.ModelClass;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitAdapter {
    public static GOTInterface getClient(){
    Retrofit retro=new Retrofit.Builder()
            .baseUrl(urlManager.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GOTInterface gotInterface=retro.create(GOTInterface.class);
        return gotInterface;
    }

    public interface GOTInterface{
        @GET(urlManager.baseUrl+"characters/{name}")
        Call<ModelClass> getChracterInfo(@Path("name") String name);
    }
}
