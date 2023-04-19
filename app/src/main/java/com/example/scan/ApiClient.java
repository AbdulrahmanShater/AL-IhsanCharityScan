package com.example.scan;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL= "http:/192.168.0.151/Hreitan/";
    public static Retrofit retrofit=null;
    public static Retrofit getAppClient(){
        if (retrofit==null){
                    retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}


//
//apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
//        Call<Category> call = apiInterface.addCategory(name);
//
//        call.enqueue(new Callback<Category>() {
//@Override
//public void onResponse(Call<Category> call, Response<Category> response) {
//        System.out.println(response.body());
//        }
//
//@Override
//public void onFailure(Call<Category> call, Throwable t) {
//
//        }
//        });