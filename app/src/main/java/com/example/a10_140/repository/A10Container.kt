package com.example.a10_140.repository

import com.example.a10_140.service.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val tanamanRepository: TanamanRepository
    val pekerjaRepository: PekerjaRepository
    val catatanPanenRepository: CatatanPanenRepository
    val aktivitasPertanianRepository: AktivitasPertanianRepository
}


class A10Container : AppContainer {

    private val baseUrl = "http://10.0.2.2/a10140/"


    private val json = Json { ignoreUnknownKeys = true }


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // Services
    private val tanamanService: TanamanService by lazy { retrofit.create(TanamanService::class.java) }
    private val pekerjaService: PekerjaService by lazy { retrofit.create(PekerjaService::class.java) }
    private val catatanPanenService: CatatanPanenService by lazy { retrofit.create(CatatanPanenService::class.java) }
    private val aktivitasPertanianService: AktivitasPertanianService by lazy { retrofit.create(AktivitasPertanianService::class.java) }

    // Repositories
    override val tanamanRepository: TanamanRepository by lazy { NetworkTanamanRepository(tanamanService) }
    override val pekerjaRepository: PekerjaRepository by lazy { NetworkPekerjaRepository(pekerjaService) }
    override val catatanPanenRepository: CatatanPanenRepository by lazy { NetworkCatatanPanenRepository(catatanPanenService) }
    override val aktivitasPertanianRepository: AktivitasPertanianRepository by lazy { NetworkAktivitasPertanianRepository(aktivitasPertanianService) }
}
