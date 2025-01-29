package com.example.a10_140.repository

import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.service.AktivitasPertanianService
import java.io.IOException


interface AktivitasPertanianRepository {
    suspend fun getAktivitas(): List<Aktivitaspertanian>
    suspend fun insertAktivitas(aktivitaspertanian: Aktivitaspertanian)
    suspend fun updateAktivitas(id: String, aktivitaspertanian: Aktivitaspertanian)
    suspend fun deleteAktivitas(id: String)
    suspend fun getAktivitasById(id: String): Aktivitaspertanian
}

class NetworkAktivitasPertanianRepository(
    private val AktivitasApiService: AktivitasPertanianService
) : AktivitasPertanianRepository {
    override suspend fun insertAktivitas(aktivitaspertanian: Aktivitaspertanian) {
        AktivitasApiService.insertAktivitas(aktivitaspertanian)
    }

    override suspend fun updateAktivitas(id: String, aktivitaspertanian: Aktivitaspertanian) {
        AktivitasApiService.updateAktivitas(id, aktivitaspertanian)
    }

    override suspend fun deleteAktivitas(id: String) {
        try {
            val response = AktivitasApiService.deleteAktivitas(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Aktivitas. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAktivitas(): List<Aktivitaspertanian> = AktivitasApiService.getAktivitas()
    override suspend fun getAktivitasById(id: String): Aktivitaspertanian {
        return AktivitasApiService.getAktivitasById(id)
    }
}