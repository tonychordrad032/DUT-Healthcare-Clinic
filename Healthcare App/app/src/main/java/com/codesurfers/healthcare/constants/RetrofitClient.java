package com.codesurfers.healthcare.constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static IClinicAPI ClinicAPI;

    public RetrofitClient(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)  // Set the baseUrl to your API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI = retrofit.create(IClinicAPI.class);
    }

    public IClinicAPI getClinicAPI(){
        return ClinicAPI;
    }
}
