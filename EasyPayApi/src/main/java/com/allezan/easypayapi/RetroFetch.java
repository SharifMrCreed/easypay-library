package com.allezan.easypayapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroFetch {

    @POST(".")
    Call<PayResponse> requestPayment(@Body PayRequest request);
}
