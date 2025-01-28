package com.example.a10_140.service

import com.example.a10_140.model.Pekerja
import com.example.a10_140.model.PekerjaDetailResponse
import com.example.a10_140.model.PekerjaResponse
import retrofit2.http.*

interface PekerjaService {
    @GET("pekerja")
    suspend fun getDaftarPekerja(): PekerjaResponse

    @GET("pekerja/{id}")
    suspend fun getPekerjaById(@Path("id") id: String): PekerjaDetailResponse

    @POST("pekerja")
    suspend fun insertPekerja(@Body pekerja: Pekerja)

    @PUT("pekerja/{id}")
    suspend fun editPekerja(@Path("id") id: String, @Body pekerja: Pekerja)

    @DELETE("pekerja/{id}")
    suspend fun deletePekerja(@Path("id") id: String)
}
