package com.example.a10_140.repository

import com.example.a10_140.model.Panen
import com.example.a10_140.model.PanenDetailResponse
import com.example.a10_140.model.PanenResponse
import com.example.a10_140.service.CatatanPanenService
import java.io.IOException

interface CatatanPanenRepository {
    suspend fun getRiwayatPanen(): PanenResponse
    suspend fun getPanenById(id: String): PanenDetailResponse
    suspend fun insertCatatanPanen(panen: Panen)
    suspend fun updateCatatanPanen(id: String, panen: Panen)
    suspend fun deleteCatatanPanen(id: String)
}

class NetworkCatatanPanenRepository(private val service: CatatanPanenService) : CatatanPanenRepository {
    override suspend fun getRiwayatPanen(): PanenResponse {
        return try {
            service.getCatatanPanen()
        } catch (e: IOException) {
            throw IllegalStateException("Error: Unable to fetch Panen data: ${e.message}")
        }
    }

    override suspend fun getPanenById(id: String): PanenDetailResponse {
        return try {
            service.getCatatanPanenById(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Unable to fetch Panen by ID: ${e.message}")
        }
    }

    override suspend fun insertCatatanPanen(panen: Panen) {
        try {
            service.insertCatatanPanen(panen)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Unable to insert Panen: ${e.message}")
        }
    }

    override suspend fun updateCatatanPanen(id: String, panen: Panen) {
        try {
            service.editCatatanPanen(id, panen)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Unable to update Panen: ${e.message}")
        }
    }

    override suspend fun deleteCatatanPanen(id: String) {
        try {
            service.deleteCatatanPanen(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Unable to delete Panen: ${e.message}")
        }
    }
}
