package com.example.bukuwarung;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {

    @GET("/users")
    Call <ApiResponse> getUsers();
}
