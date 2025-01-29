package com.example.a10_140.ui.viewmodel.AktivitasPertanian

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.repository.AktivitasPertanianRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class DetailAktivitasUiState {
    data class Success(val Aktivitas: Aktivitaspertanian) : DetailAktivitasUiState()
    data class Error(val message: String) : DetailAktivitasUiState()
    object Loading : DetailAktivitasUiState()
}

class DetailAktivitasViewModel(
    savedStateHandle: SavedStateHandle,
    private val Aktivitas: AktivitasPertanianRepository
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle["id"])

    private val _detailUiState = MutableStateFlow<DetailAktivitasUiState>(DetailAktivitasUiState.Loading)
    val detailAktivitasUiState: StateFlow<DetailAktivitasUiState> = _detailUiState.asStateFlow()

    init {
        getAktivitasById(_id)
    }
    fun getAktivitasById(id: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailAktivitasUiState.Loading
            _detailUiState.value = try {
                val Aktivitas = Aktivitas.getAktivitasById(id)
                DetailAktivitasUiState.Success(Aktivitas)
            } catch (e: IOException) {
                DetailAktivitasUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailAktivitasUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}