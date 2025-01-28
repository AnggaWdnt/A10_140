package com.example.a10_140.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tanaman(
    @SerialName("id_tanaman")
    val idTanaman: String,
    @SerialName("nama_tanaman")
    val namaTanaman: String,
    @SerialName("periode_tanam")
    val periodeTanaman: String,
    @SerialName("deskripsi_tanaman")
    val deskripsiTanaman: String
)
