package com.example.a10_140.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PekerjaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pekerja
)

@Serializable
data class PekerjaResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pekerja>
)

@Serializable
data class Pekerja(
    @SerialName("id_pekerja")
    val idPekerja: String,
    @SerialName("nama_pekerja")
    val namaPekerja: String,
    val jabatan: String,
    @SerialName("kontak_pekerja")
    val kontakPekerja: String
)
