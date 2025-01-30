package com.example.a10_140.service

import com.example.a10_140.model.Tanaman
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TanamanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("gettanaman.php")
    suspend fun getTanaman(): List<Tanaman>

    @GET("get1tanaman.php")
    suspend fun getTanamanById(@Query("id_tanaman") id: String): Tanaman

    @POST("inserttanaman.php")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("edittanaman.php")
    suspend fun updateTanaman(@Query("id_tanaman") id: String, @Body tanaman: Tanaman)

    @DELETE("deletetanaman.php")
    suspend fun deleteTanaman(@Query("id_tanaman") id: String): Response<Unit>
}
