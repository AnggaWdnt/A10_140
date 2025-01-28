package com.example.a10_140.service

import com.example.a10_140.model.Panen
import com.example.a10_140.model.PanenDetailResponse
import com.example.a10_140.model.PanenResponse
import retrofit2.http.*

interface CatatanPanenService {

    @GET("panen")
    suspend fun getCatatanPanen(): PanenResponse

    @GET("panen/{id}")
    suspend fun getCatatanPanenById(@Path("id") id: String): PanenDetailResponse

    @POST("panen")
    suspend fun insertCatatanPanen(@Body panen: Panen)

    @PUT("panen/{id}")
    suspend fun editCatatanPanen(@Path("id") id: String, @Body panen: Panen)

    @DELETE("panen/{id}")
    suspend fun deleteCatatanPanen(@Path("id") id: String)
}
