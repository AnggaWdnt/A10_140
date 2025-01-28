package com.example.a10_140.repository

import com.example.a10_140.model.Tanaman
import com.example.a10_140.service.TanamanService
import java.io.IOException


interface TanamanRepository {
    suspend fun getTanaman(): List<Tanaman>
    suspend fun insertTanaman(tanaman: Tanaman)
    suspend fun updateTanaman(id: String, tanaman: Tanaman)
    suspend fun deleteTanaman(id: String)
    suspend fun getTanamanById(id: String): Tanaman
}

class NetworkTanamanRepository(
    private val tanamanApiService: TanamanService
) : TanamanRepository {
    override suspend fun insertTanaman(tanaman: Tanaman) {
        tanamanApiService.insertTanaman(tanaman)
    }

    override suspend fun updateTanaman(id: String, tanaman: Tanaman) {
        tanamanApiService.updateTanaman(id, tanaman)
    }

    override suspend fun deleteTanaman(id: String) {
        try {
            val response = tanamanApiService.deleteTanaman(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Tanaman. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTanaman(): List<Tanaman> = tanamanApiService.getTanaman()
    override suspend fun getTanamanById(id: String): Tanaman {
        return tanamanApiService.getTanamanById(id)
    }
}