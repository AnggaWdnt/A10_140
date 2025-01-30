package com.example.a10_140.ui.viewmodel.Pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.repository.PekerjaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UpdatePekerjaViewModel(
    private val pkj: PekerjaRepository
) : ViewModel() {

    var pekerjauiState by mutableStateOf(UpdatePekerjaUiState())
        private set

    fun updatePekerjaState(updatePekerjaUiEvent: UpdatePekerjaUiEvent) {
        pekerjauiState = pekerjauiState.copy(updatePekerjaUiEvent = updatePekerjaUiEvent)
    }

    fun getPekerjaById(id: String) {
        viewModelScope.launch {
            try {
                val pekerja = pkj.getPekerjaById(id)
                pekerjauiState = UpdatePekerjaUiState(updatePekerjaUiEvent = pekerja.toUpdatePekerjaUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                pekerjauiState = UpdatePekerjaUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    private fun validateFields(): Boolean {
        val event = pekerjauiState.updatePekerjaUiEvent
        val errorState = UpdatePekerjaErrorState(
            idPekerja = if (event.idPekerja.isNotEmpty()) null else "ID Pekerja tidak boleh kosong",
            namaPekerja = if (event.namaPekerja.isNotEmpty()) null else "Nama pekerja tidak boleh kosong",
            jabatan = if (event.jabatan.isNotEmpty()) null else "Jabatan tidak boleh kosong",
            kontakPekerja = if (event.kontakpekerja.isNotEmpty()) null else "Kontak Pekerja tidak boleh kosong",

            )
        pekerjauiState = pekerjauiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updatePekerja() {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    val pekerja = pekerjauiState.updatePekerjaUiEvent.toPekerja()
                    pkj.updatePekerja(pekerja.idPekerja, pekerja)
                    pekerjauiState = pekerjauiState.copy(isSuccess = true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    pekerjauiState = pekerjauiState.copy(isError = true, errorMessage = e.message)
                }
            }
        } else {
            pekerjauiState = pekerjauiState.copy(snackBarMessage = "Input tidak valid. Periksa kembali data pekerja Anda.")
            viewModelScope.launch {
                delay(100)
                resetSnackBarMessage()
            }
        }
    }

    fun resetSnackBarMessage() {
        pekerjauiState = pekerjauiState.copy(snackBarMessage = null)
    }
}
data class UpdatePekerjaUiState(
    val updatePekerjaUiEvent: UpdatePekerjaUiEvent = UpdatePekerjaUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isEntryValid: UpdatePekerjaErrorState = UpdatePekerjaErrorState(),
    val snackBarMessage: String? = null
)

data class UpdatePekerjaErrorState(
    val idPekerja: String? = null,
    val namaPekerja: String? = null,
    val jabatan: String? = null,
    val kontakPekerja: String? = null
) {
    fun isValid(): Boolean {
        return namaPekerja  == null && idPekerja == null && jabatan == null && kontakPekerja == null
    }
}


data class UpdatePekerjaUiEvent(
    val idPekerja: String = "",
    val namaPekerja: String = "",
    val jabatan: String = "",
    val kontakpekerja: String = ""
)

fun UpdatePekerjaUiEvent.toPekerja(): Pekerja = Pekerja(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakPekerja = kontakpekerja
)

fun Pekerja.toUpdatePekerjaUiEvent(): UpdatePekerjaUiEvent = UpdatePekerjaUiEvent(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakpekerja = kontakPekerja
)