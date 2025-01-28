package com.example.a10_140.service

import com.example.a10_140.model.Tanaman
import com.example.a10_140.model.TanamanDetailResponse
import com.example.a10_140.model.TanamanResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TanamanService {
    @GET("tanaman")
    suspend fun getTanaman(): TanamanResponse

    @GET("tanaman/{id}")
    suspend fun getTanamanById(@Path("id") id: String): TanamanDetailResponse

    @POST("tanaman")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("tanaman/{id}")
    suspend fun updateTanaman(@Path("id") id: String, @Body tanaman: Tanaman)

    @DELETE("tanaman/{id}")
    suspend fun deleteTanaman(@Path("id") id: String)
}
