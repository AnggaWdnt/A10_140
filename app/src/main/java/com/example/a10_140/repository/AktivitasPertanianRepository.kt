package com.example.a10_140.repository

import com.example.a10_140.model.AktivitasPertanian
import com.example.a10_140.model.AktivitasPertanianDetailResponse
import com.example.a10_140.model.AktivitasPertanianResponse
import com.example.a10_140.service.AktivitasPertanianService

interface AktivitasPertanianRepository {
    suspend fun getDaftarAktivitas(): AktivitasPertanianResponse
    suspend fun getAktivitasById(id: String): AktivitasPertanianDetailResponse
    suspend fun insertAktivitas(aktivitas: AktivitasPertanian)
    suspend fun updateAktivitas(id: String, aktivitas: AktivitasPertanian)
    suspend fun deleteAktivitas(id: String)
}

class NetworkAktivitasPertanianRepository(
    private val service: AktivitasPertanianService
) : AktivitasPertanianRepository {
    override suspend fun getDaftarAktivitas(): AktivitasPertanianResponse {
        return service.getDaftarAktivitas()
    }

    override suspend fun getAktivitasById(id: String): AktivitasPertanianDetailResponse {
        return service.getAktivitasPertanianById(id)
    }

    override suspend fun insertAktivitas(aktivitas: AktivitasPertanian) {
        service.insertAktivitasPertanian(aktivitas)
    }

    override suspend fun updateAktivitas(id: String, aktivitas: AktivitasPertanian) {
        service.editAktivitas(id, aktivitas)
    }

    override suspend fun deleteAktivitas(id: String) {
        service.deleteAktivitas(id)
    }
}
