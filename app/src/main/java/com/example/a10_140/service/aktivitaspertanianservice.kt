package com.example.a10_140.service

import com.example.a10_140.model.AktivitasPertanian
import com.example.a10_140.model.AktivitasPertanianDetailResponse
import com.example.a10_140.model.AktivitasPertanianResponse
import retrofit2.http.*

interface AktivitasPertanianService {
    @GET("aktivitas")
    suspend fun getDaftarAktivitas(): AktivitasPertanianResponse

    @GET("aktivitas/{id}")
    suspend fun getAktivitasPertanianById(@Path("id") id: String): AktivitasPertanianDetailResponse

    @POST("aktivitas")
    suspend fun insertAktivitasPertanian(@Body aktivitas: AktivitasPertanian)

    @PUT("aktivitas/{id}")
    suspend fun editAktivitas(@Path("id") id: String, @Body aktivitas: AktivitasPertanian)

    @DELETE("aktivitas/{id}")
    suspend fun deleteAktivitas(@Path("id") id: String)
}
