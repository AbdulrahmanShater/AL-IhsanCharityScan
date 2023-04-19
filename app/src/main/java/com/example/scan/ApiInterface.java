package com.example.scan;
;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getAll.php")
    Call<ArrayList<Id>> getId();


}
