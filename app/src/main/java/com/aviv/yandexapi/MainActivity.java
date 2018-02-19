package com.aviv.yandexapi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String yandexKey;
    private String yandexUrl;
    private TextView textView;

    String textToBeTranslated = "Android Developer";
    String languagePair = "en-hi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.txt_result);
        yandexKey = "trnsl.1.1.20180216T073427Z.8b4f516133cea359.bc57200540a8e9d1d58efb3d3c08360da642362b";
        yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                + "&text=" + textToBeTranslated + "&lang=" + languagePair;

        getRetrofitObject();
    }


    public void getRetrofitObject() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/api/v1.5/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<model> call = service.getJsonObjectData(yandexKey, textToBeTranslated, languagePair);
        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                try {

                    if (response.body().getText().get(0) != null) {
                        Log.d("VoiceToTextURL ", "  getText  RESPONSE ==>>> " + response.body().getText().get(0));
                        textView.setText(response.body().getText().get(0));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                Log.e("VoiceToTextURL ", "  Error  Code==>>> " + t.toString());

            }
        });
    }
}