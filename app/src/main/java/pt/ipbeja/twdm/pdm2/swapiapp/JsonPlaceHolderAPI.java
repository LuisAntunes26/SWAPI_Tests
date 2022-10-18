package pt.ipbeja.twdm.pdm2.swapiapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {

    @GET("api/people")
    Call<APIResponse> getPersonsFromPageNumber(
            @Query("page") String pageNumber,
            @Query("format") String format);
}
