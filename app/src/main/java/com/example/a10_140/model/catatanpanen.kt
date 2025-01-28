package com.example.a10_140.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PanenDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Panen
)

@Serializable
data class PanenResponse(
    val status: Boolean,
    val message: String,
    val data: List<Panen>
)

@Serializable
data class Panen(
    @SerialName("id_panen")
    val idPanen: String,
    @SerialName("id_tanaman")
    val idTanaman: String,
    @SerialName("tanggal_panen")
    val tanggalPanen: String,
    @SerialName("jumlah_panen")
    val jumlahPanen: Int,
    @SerialName("keterangan")
    val keterangan: String
)