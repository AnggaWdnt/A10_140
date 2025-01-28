package com.example.a10_140.repository

import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.service.CatatanPanenService
import java.io.IOException

interface CatatanPanenRepository {
    suspend fun getCatatan(): List<CatatanPanen>
    suspend fun insertCatatan(catatanPanen: CatatanPanen)
    suspend fun updateCatatan(id: String, catatanPanen: CatatanPanen)
    suspend fun deleteCatatan(id: String)
    suspend fun getCatatanById(id: String): CatatanPanen
}

class NetworkCatatanPanenRepository(
    private val catatanPanenApiService: CatatanPanenService
) : CatatanPanenRepository {
    override suspend fun getCatatan(): List<CatatanPanen> = catatanPanenApiService.getCatatan()

    override suspend fun insertCatatan(catatanPanen: CatatanPanen) {
        catatanPanenApiService.insertCatatan(catatanPanen)
    }

    override suspend fun updateCatatan(id: String, catatanPanen: CatatanPanen) {
        catatanPanenApiService.updateCatatan(id, catatanPanen)
    }

    override suspend fun deleteCatatan(id: String) {
        try {
            val response = catatanPanenApiService.deleteCatatan(id)
            if (!response.isSuccessful) {
                throw IOException("Gagal menghapus catatan panen. HTTP Status code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCatatanById(id: String): CatatanPanen {
        return catatanPanenApiService.getCatatanById(id)
    }
}
