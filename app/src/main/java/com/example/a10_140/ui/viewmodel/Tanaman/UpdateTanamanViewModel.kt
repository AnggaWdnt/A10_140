package com.example.a10_140.ui.viewmodel.Tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Tanaman
import com.example.a10_140.repository.TanamanRepository
import kotlinx.coroutines.launch


class UpdateTanamanViewModel(
    private val tnm: TanamanRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun updateUpdateTanamanState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getTanamanById(id: String) {
        viewModelScope.launch {
            try {
                val tanaman = tnm.getTanamanById(id)
                uiState = UpdateUiState(updateUiEvent = tanaman.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateTanaman() {
        viewModelScope.launch {
            try {
                val tanaman = uiState.updateUiEvent.toTanaman()
                tnm.updateTanaman(tanaman.idTanaman, tanaman)
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
val idTanaman: String = "",
val namaTanaman: String = "",
val periodeTanaman: String = "",
val deskripsiTanaman: String = ""
)

fun UpdateUiEvent.toTanaman(): Tanaman = Tanaman(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanaman = periodeTanaman,
    deskripsiTanaman = deskripsiTanaman
)

fun Tanaman.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanaman = periodeTanaman,
    deskripsiTanaman = deskripsiTanaman
)