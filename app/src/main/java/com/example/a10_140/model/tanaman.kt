package com.example.a10_140.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TanamanDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Tanaman
)

@Serializable
data class TanamanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Tanaman>
)

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
