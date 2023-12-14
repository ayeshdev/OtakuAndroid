package com.otaku.otaku.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String base_url = "http://192.168.13.210:1337/";
    private static final String token = "18c5a979ca10ae391f0cdf647e4366f87d51d69f56086e1836bf58760f18012d9a9c42a4778a760ba7a0e4b1c67d66a4c439b6c0741952484288d255f4545436451c89e2e1705744e511bd6d9c6819222f886cfa266225e577a24cb7b5fd0aa2dcc1696909bf26e2be6f0117a6ddf1508ff02cdb1633cf5bc7bace81dd3cc726";

//    This will observe requests from the
//    server and the corresponding responses coming back into the server.

    public static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @NonNull
        @Override
        public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();


//    Create a ApiConnection() singleton HTTP client to execute Retrofit.

    public static Retrofit ApiConnection(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
