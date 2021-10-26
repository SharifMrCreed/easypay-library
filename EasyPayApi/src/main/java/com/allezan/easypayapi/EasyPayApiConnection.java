
package com.allezan.easypayapi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("unused")
public class EasyPayApiConnection {

    @Expose
    private String action;
    @Expose
    private int amount;
    @Expose
    private String currency;
    @Expose
    private String password;
    @Expose
    private String phone;
    @Expose
    private String reason;
    @Expose
    private String reference;
    @Expose
    private String username;


    /**
     * @param amount Amount to be paid
     */
    public EasyPayApiConnection amount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * @param secret This is the API secret that you are given within app after you enable API. see above
     * @return an Easy pay request object to call other builder methods on
     */
    public EasyPayApiConnection secret(String secret) {
        this.password = secret;
        return this;
    }

    /**
     * @param phone – Phone number including country code e.g for uganda 25677XXXXXXX.
     * @return an Easy pay request object to call other builder methods on
     */
    public EasyPayApiConnection phoneNumber(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * @param reason – This could be the description of the transaction.
     * @return an Easy pay request object to call other builder methods on
     */
    public EasyPayApiConnection reason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * @param reference – Your order Id, This will be returned in the instant paymenT
     *                  notification to your ipn url. This can be alphanumeric and must
     *                  always be unique for every call of mmdeposit.
     * @return an Easy pay request object to call other builder methods on
     */
    public EasyPayApiConnection orderId(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * @param clientId - This is the API clientId that you are given within app after you enable API.
     * @return an Easy pay request object to call other builder methods on
     */
    public EasyPayApiConnection clientId(String clientId) {
        this.username = clientId;
        return this;
    }

    /**
     * action – Leave this as-is ie mmdeposit.
     * currency – The currency the above amount is in. If it is not UGX amount is then converted to UGX
     */
    public void startPayment() {
        PayRequest easyPayRequest = new PayRequest();
        easyPayRequest.setAction("mmdeposit");
        easyPayRequest.setAmount(amount);
        easyPayRequest.setCurrency("UGX");
        easyPayRequest.setPassword(password);
        easyPayRequest.setPhone(phone);
        easyPayRequest.setReason(reason);
        easyPayRequest.setReference(reference);
        easyPayRequest.setUsername(username);

        startRequest(easyPayRequest);


    }

    private void startRequest(PayRequest easyPayRequest) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFetch retroFetch = retrofit.create(RetroFetch.class);

        Call<PayResponse> call  = retroFetch.requestPayment(easyPayRequest);
        call.enqueue(new Callback<PayResponse>() {
            @Override
            public void onResponse(@NonNull Call<PayResponse> call, @NonNull Response<PayResponse> response) {
                Log.d("Start Request", "onResponse: \nSuccessFull: " + response.toString());
            }

            @Override
            public void onFailure(@NonNull Call<PayResponse> call, @NonNull Throwable t) {
                Log.d("Start Request", "onResponse: \nFailed: " + t.toString());

            }
        });
    }


}
