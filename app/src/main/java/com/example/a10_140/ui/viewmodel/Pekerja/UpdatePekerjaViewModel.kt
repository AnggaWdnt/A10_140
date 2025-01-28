package com.example.a10_140.ui.viewmodel.Pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.repository.PekerjaRepository
import kotlinx.coroutines.launch


class UpdatePekerjaViewModel(
    private val pkj: PekerjaRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun updateUpdatePekerjaState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getPekerjaById(id: String) {
        viewModelScope.launch {
            try {
                val pekerja = pkj.getPekerjaById(id)
                uiState = UpdateUiState(updateUiEvent = pekerja.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updatePekerja() {
        viewModelScope.launch {
            try {
                val pekerja = uiState.updateUiEvent.toPekerja()
                pkj.updatePekerja(pekerja.idPekerja, pekerja)
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
    val idPekerja: String = "",
    val namaPekerja: String = "",
    val jabatan: String = "",
    val kontakpekerja: String = ""
)

fun UpdateUiEvent.toPekerja(): Pekerja = Pekerja(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakPekerja = kontakpekerja
)

fun Pekerja.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakpekerja = kontakPekerja
)