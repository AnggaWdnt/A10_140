package com.example.a10_140.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pekerja(
    @SerialName("id_pekerja")
    val idPekerja: String,
    @SerialName("nama_pekerja")
    val namaPekerja: String,
    @SerialName("jabatan_pekerja")
    val jabatan: String,
    @SerialName("kontak_pekerja")
    val kontakPekerja: String
)
