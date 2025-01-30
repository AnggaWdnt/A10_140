package com.example.a10_140.service

import com.example.a10_140.model.Aktivitaspertanian
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface AktivitasPertanianService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("getaktivitas.php")
    suspend fun getAktivitas(): List<Aktivitaspertanian>

    @GET("get1aktivitas.php")
    suspend fun getAktivitasById(@Query("id_aktivitas") idAktivitas:String): Aktivitaspertanian

    @POST("insertaktivitas.php")
    suspend fun insertAktivitas(@Body aktivitaspertanian: Aktivitaspertanian)

    @PUT("editaktivitas.php")
    suspend fun updateAktivitas(@Query("id_aktivitas") idAktivitas: String, @Body aktivitaspertanian: Aktivitaspertanian)

    @DELETE("deleteaktivitas.php")
    suspend fun deleteAktivitas(@Query("id_aktivitas") idAktivitas: String) : retrofit2.Response<Void>
}