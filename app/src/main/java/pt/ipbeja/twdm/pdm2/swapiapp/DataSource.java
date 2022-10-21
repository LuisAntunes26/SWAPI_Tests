package pt.ipbeja.twdm.pdm2.swapiapp;

import android.net.Uri;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {

    public static APIResponse getFromURL(String url) throws IOException {
        if (url == null) return null;

        String pageNumber = getPageNumber(url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/").addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<APIResponse> call = jsonPlaceHolderAPI.getPersonsFromPageNumber(pageNumber, "json");

        APIResponse apiResponse = call.execute().body();
        return apiResponse;
    }


    public static String getPageNumber(String url){
        if (url == null) return null;
        Uri uri = Uri.parse(url);
        return uri.getQueryParameter("page");
    }
}
