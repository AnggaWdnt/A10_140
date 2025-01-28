package com.example.a10_140.repository

import com.example.a10_140.model.Tanaman
import com.example.a10_140.model.TanamanDetailResponse
import com.example.a10_140.model.TanamanResponse
import com.example.a10_140.service.TanamanService
import java.io.IOException

interface TanamanRepository {
    suspend fun getDaftarTanaman(): TanamanResponse
    suspend fun getTanamanById(id: String): TanamanDetailResponse
    suspend fun insertTanaman(tanaman: Tanaman)
    suspend fun updateTanaman(id: String, tanaman: Tanaman)
    suspend fun deleteTanaman(id: String)
}

class NetworkTanamanRepository(private val service: TanamanService) : TanamanRepository {

    override suspend fun getDaftarTanaman(): TanamanResponse {
        return try {
            service.getTanaman()
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal mengambil daftar tanaman - ${e.message}")
        }
    }

    override suspend fun getTanamanById(id: String): TanamanDetailResponse {
        return try {
            service.getTanamanById(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal mengambil detail tanaman dengan ID $id - ${e.message}")
        }
    }

    override suspend fun insertTanaman(tanaman: Tanaman) {
        try {
            service.insertTanaman(tanaman)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal menambahkan tanaman baru - ${e.message}")
        }
    }

    override suspend fun updateTanaman(id: String, tanaman: Tanaman) {
        try {
            service.updateTanaman(id, tanaman)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal memperbarui tanaman dengan ID $id - ${e.message}")
        }
    }

    override suspend fun deleteTanaman(id: String) {
        try {
            service.deleteTanaman(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal menghapus tanaman dengan ID $id - ${e.message}")
        }
    }
}
