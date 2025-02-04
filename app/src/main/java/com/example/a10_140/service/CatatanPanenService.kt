package com.example.a10_140.service

import com.example.a10_140.model.CatatanPanen
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CatatanPanenService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("getpanen.php")
    suspend fun getCatatan(): List<CatatanPanen>

    @GET("get1panen.php")
    suspend fun getCatatanById(@Query("id_panen")id:String):CatatanPanen

    @POST("insertpanen.php")
    suspend fun insertCatatan(@Body catatanPanen: CatatanPanen)

    @PUT("editpanen.php")
    suspend fun updateCatatan(@Query("id_panen")id: String, @Body catatanPanen: CatatanPanen)

    @DELETE("deletepanen.php")
    suspend fun deleteCatatan(@Query("id_panen")id: String): Response<Void>
}