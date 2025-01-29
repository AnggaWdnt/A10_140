package com.example.a10_140.ui.viewmodel.AktivitasPertanian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.repository.AktivitasPertanianRepository
import com.example.a10_140.repository.CatatanPanenRepository
import kotlinx.coroutines.launch


class UpdateAktivitasViewModel(
    private val ap: AktivitasPertanianRepository
) : ViewModel() {

    var AktivitasuiState by mutableStateOf(UpdateAktivitasUiState())
        private set

    fun UpdateAktivitasState(updateAktivitasUiEvent: UpdateAktivitasUiEvent) {
        AktivitasuiState = AktivitasuiState.copy(updateAktivitasUiEvent = updateAktivitasUiEvent)
    }

    fun getAktivitasById(id: String) {
        viewModelScope.launch {
            try {
                val Aktivitaspertanian = ap.getAktivitasById(id)
                AktivitasuiState = UpdateAktivitasUiState(updateAktivitasUiEvent = Aktivitaspertanian.toUpdateAktivitasUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                AktivitasuiState = UpdateAktivitasUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateAktivitas() {
        viewModelScope.launch {
            try {
                val Aktivitas = AktivitasuiState.updateAktivitasUiEvent.toAktivitas()
                ap.updateAktivitas(Aktivitas.idAktivitas, Aktivitas)
                AktivitasuiState = AktivitasuiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                AktivitasuiState = AktivitasuiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}
data class UpdateAktivitasUiState(
    val updateAktivitasUiEvent: UpdateAktivitasUiEvent = UpdateAktivitasUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdateAktivitasUiEvent(
    val idAktivitas: String = "",
    val idTanaman: String = "",
    val idpekerja: String = "",
    val tanggalAktivitas: String = "",
    val deskripsiAktivitas: String = ""
)

fun UpdateAktivitasUiEvent.toAktivitas(): Aktivitaspertanian = Aktivitaspertanian(
    idAktivitas = idAktivitas,
    idTanaman = idTanaman,
    idPekerja = idpekerja,
    tanggalAktivitas = tanggalAktivitas,
    deskripsiAktivitas = deskripsiAktivitas
)

fun Aktivitaspertanian.toUpdateAktivitasUiEvent(): UpdateAktivitasUiEvent = UpdateAktivitasUiEvent(
    idAktivitas = idAktivitas,
    idTanaman = idTanaman,
    idpekerja = idPekerja,
    tanggalAktivitas = tanggalAktivitas,
    deskripsiAktivitas = deskripsiAktivitas
)
