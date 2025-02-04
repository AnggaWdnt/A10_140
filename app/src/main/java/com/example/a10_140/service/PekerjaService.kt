package com.example.a10_140.service

import com.example.a10_140.model.Pekerja
import com.example.a10_140.model.Tanaman
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PekerjaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("getpekerja.php")
    suspend fun getPekerja(): List<Pekerja>

    @GET("get1pekerja.php")
    suspend fun getPekerjaById(@Query("id_pekerja")id:String):Pekerja

    @POST("insertpekerja.php")
    suspend fun insertPekerja(@Body pekerja: Pekerja)

    @PUT("editpekerja.php")
    suspend fun updatePekerja(@Query("id_pekerja") id: String, @Body pekerja: Pekerja): Response<Pekerja>


    @DELETE("deletepekerja.php")
    suspend fun deletePekerja(@Query("id_pekerja")id: String): Response<Void>
}