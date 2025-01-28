package com.example.a10_140.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AktivitasPertanianDetailResponse(
    val status: Boolean,
    val message: String,
    val data: AktivitasPertanian
)

@Serializable
data class AktivitasPertanianResponse(
    val status: Boolean,
    val message: String,
    val data: List<AktivitasPertanian>
)

@Serializable
data class AktivitasPertanian(
    @SerialName("id_aktivitas")
    val idAktivitas: String,
    @SerialName("id_tanaman")
    val idTanaman: String,
    @SerialName("id_pekerja")
    val idPekerja: String,
    @SerialName("tanggal_aktivitas")
    val tanggalAktivitas: String,
    @SerialName("deskripsi_aktivitas")
    val deskripsiAktivitas: String
)

