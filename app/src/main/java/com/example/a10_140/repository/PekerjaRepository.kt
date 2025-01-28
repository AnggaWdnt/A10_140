package com.example.a10_140.repository

import com.example.a10_140.model.Pekerja
import com.example.a10_140.service.PekerjaService
import java.io.IOException

interface PekerjaRepository {
    suspend fun getPekerja(): List<Pekerja>
    suspend fun insertPekerja(pekerja: Pekerja)
    suspend fun updatePekerja(id: String, pekerja: Pekerja)
    suspend fun deletePekerja(id: String)
    suspend fun getPekerjaById(id: String): Pekerja
}

class NetworkPekerjaRepository(
    private val pekerjaApiService: PekerjaService
) : PekerjaRepository {
    override suspend fun insertPekerja(pekerja: Pekerja) {
        pekerjaApiService.insertPekerja(pekerja)
    }

    override suspend fun updatePekerja(id: String, pekerja: Pekerja) {
        pekerjaApiService.updatePekerja(id, pekerja)
    }

    override suspend fun deletePekerja(id: String) {
        try {
            val response = pekerjaApiService.deletePekerja(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Pekerjax. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPekerja(): List<Pekerja> = pekerjaApiService.getPekerja()
    override suspend fun getPekerjaById(id: String): Pekerja {
        return pekerjaApiService.getPekerjaById(id)
    }
}