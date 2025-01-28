package com.example.a10_140.ui.viewmodel.CatatanPanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.repository.CatatanPanenRepository
import kotlinx.coroutines.launch


class UpdateCatatanViewModel(
    private val ctt: CatatanPanenRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun updateUpdateCatatanState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getCatatanById(id: String) {
        viewModelScope.launch {
            try {
                val CatatanPanen = ctt.getCatatanById(id)
                uiState = UpdateUiState(updateUiEvent = CatatanPanen.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateCatatan() {
        viewModelScope.launch {
            try {
                val CatatanPanen = uiState.updateUiEvent.toCatatan()
                ctt.updateCatatan(CatatanPanen.idPanen, CatatanPanen)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}
data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdateUiEvent(
    val idPanen: String = "",
    val idTanaman: String = "",
    val tanggalpanen: String = "",
    val jumlahpanen: String = "",
    val keterangan: String = ""
)

fun UpdateUiEvent.toCatatan(): CatatanPanen = CatatanPanen(
    idPanen = idPanen,
    idTanaman = idTanaman,
    tanggalPanen = tanggalpanen,
    jumlahPanen = jumlahpanen,
    keterangan = keterangan
)

fun CatatanPanen.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idPanen = idPanen,
    idTanaman = idTanaman,
    tanggalpanen = tanggalPanen,
    jumlahpanen = jumlahPanen,
    keterangan = keterangan
)