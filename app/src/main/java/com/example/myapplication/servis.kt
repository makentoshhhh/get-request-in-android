package com.example.myapplication

import retrofit2.http.GET

interface servis {
    @GET("/random")
    suspend fun get_data():
        dataM


}