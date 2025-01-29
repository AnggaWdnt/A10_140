package com.example.a10_140.ui.viewmodel.AktivitasPertanian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.repository.AktivitasPertanianRepository
import kotlinx.coroutines.launch

class InsertAktivitasViewModel(private val aktivitasRepository: AktivitasPertanianRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertAktivitasState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertAktivitas() {
        viewModelScope.launch {
            try {
                aktivitasRepository.insertAktivitas(uiState.insertUiEvent.toAktivitas())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idAktivitas: String = "",
    val idTanaman: String = "",
    val idPekerja: String = "",
    val tanggalAktivitas: String = "",
    val deskripsiAktivitas: String = ""
)

fun InsertUiEvent.toAktivitas(): Aktivitaspertanian = Aktivitaspertanian(
    idAktivitas = idAktivitas,
    idTanaman = idTanaman,
    idPekerja = idPekerja,
    tanggalAktivitas = tanggalAktivitas,
    deskripsiAktivitas = deskripsiAktivitas
)

fun Aktivitaspertanian.toUiStateAktivitas(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Aktivitaspertanian.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idAktivitas = idAktivitas,
    idTanaman = idTanaman,
    idPekerja = idPekerja,
    tanggalAktivitas = tanggalAktivitas,
    deskripsiAktivitas = deskripsiAktivitas
)
