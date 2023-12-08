package com.otaku.otaku.api;

import com.otaku.otaku.model.DataRequest;
import com.otaku.otaku.model.DataResponse;
import com.otaku.otaku.model.SizeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/products?populate=*")
    Call<DataResponse> getProducts();

    @GET("api/sizes")
    Call<SizeResponse> getSizes();

    @POST("api/products")
    Call<DataRequest> createProduct(
            @Body DataRequest dataRequest
    );

    @PUT("api/products/{id}")
    Call<DataRequest> updateTask(
            @Path("id") int id,
            @Body DataRequest dataRequest
    );

    @DELETE("api/products/{id}")
    Call<DataRequest> deleteTask(
            @Path("id") int id
    );
}
