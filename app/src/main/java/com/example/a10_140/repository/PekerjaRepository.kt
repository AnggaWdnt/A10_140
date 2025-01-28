package com.example.a10_140.repository

import com.example.a10_140.model.Pekerja
import com.example.a10_140.model.PekerjaDetailResponse
import com.example.a10_140.model.PekerjaResponse
import com.example.a10_140.service.PekerjaService
import java.io.IOException

interface PekerjaRepository {
    /**
     * Mengambil daftar semua pekerja.
     */
    suspend fun getDaftarPekerja(): PekerjaResponse

    /**
     * Mengambil detail pekerja berdasarkan ID.
     */
    suspend fun getPekerjaById(id: String): PekerjaDetailResponse

    /**
     * Menambahkan pekerja baru.
     */
    suspend fun insertPekerja(pekerja: Pekerja)

    /**
     * Memperbarui data pekerja berdasarkan ID.
     */
    suspend fun updatePekerja(id: String, pekerja: Pekerja)

    /**
     * Menghapus pekerja berdasarkan ID.
     */
    suspend fun deletePekerja(id: String)
}

class NetworkPekerjaRepository(private val service: PekerjaService) : PekerjaRepository {
    override suspend fun getDaftarPekerja(): PekerjaResponse {
        return try {
            service.getDaftarPekerja()
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal mengambil daftar pekerja - ${e.message}")
        }
    }

    override suspend fun getPekerjaById(id: String): PekerjaDetailResponse {
        return try {
            service.getPekerjaById(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal mengambil data pekerja dengan ID $id - ${e.message}")
        }
    }

    override suspend fun insertPekerja(pekerja: Pekerja) {
        try {
            service.insertPekerja(pekerja)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal menambahkan pekerja - ${e.message}")
        }
    }

    override suspend fun updatePekerja(id: String, pekerja: Pekerja) {
        try {
            service.editPekerja(id, pekerja)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal memperbarui pekerja dengan ID $id - ${e.message}")
        }
    }

    override suspend fun deletePekerja(id: String) {
        try {
            service.deletePekerja(id)
        } catch (e: IOException) {
            throw IllegalStateException("Error: Gagal menghapus pekerja dengan ID $id - ${e.message}")
        }
    }
}
