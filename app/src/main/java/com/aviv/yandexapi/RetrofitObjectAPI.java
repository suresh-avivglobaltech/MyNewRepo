package com.aviv.yandexapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 17-02-2018.
 */

public interface RetrofitObjectAPI {


    @GET("tr.json/translate?")
    Call<model> getJsonObjectData(
            @Query("key") String key,
            @Query("text") String text,
            @Query("lang") String langCode

    );
}
