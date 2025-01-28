package com.example.a10_140.ui.viewmodel.CatatanPanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.repository.CatatanPanenRepository
import kotlinx.coroutines.launch


class InsertCatatanViewModel (private val Catatan: CatatanPanenRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertCatatanState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertCatatan(){
        viewModelScope.launch {
            try {
                Catatan.insertCatatan(uiState.insertUiEvent.toCatatan())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertUiState(
    val insertUiEvent : InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idPanen: String = "",
    val idTanaman: String = "",
    val tanggalpanen: String = "",
    val jumlahPanen: String = "",
    val keterangan: String = ""
)

fun InsertUiEvent.toCatatan(): CatatanPanen = CatatanPanen(
    idPanen = idPanen,
    idTanaman = idTanaman,
    tanggalPanen = tanggalpanen,
    jumlahPanen = jumlahPanen,
    keterangan = keterangan
)

fun CatatanPanen.toUiStatePekerja():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun CatatanPanen.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idPanen = idPanen,
    idTanaman = idTanaman,
    tanggalpanen = tanggalPanen,
    jumlahPanen = jumlahPanen,
    keterangan = keterangan
)