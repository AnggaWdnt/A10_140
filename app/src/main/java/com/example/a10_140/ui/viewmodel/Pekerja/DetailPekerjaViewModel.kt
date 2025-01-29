package com.example.a10_140.ui.viewmodel.Pekerja

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.repository.PekerjaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class DetailPekerjaUiState {
    data class Success(val pekerja: Pekerja) : DetailPekerjaUiState()
    data class Error(val message: String) : DetailPekerjaUiState()
    object Loading : DetailPekerjaUiState()
}

class DetailPekerjaViewModel(
    savedStateHandle: SavedStateHandle,
    private val pekerjaRepository: PekerjaRepository
) : ViewModel() {
    private val _idPekerja: String = checkNotNull(savedStateHandle["id"])

    private val _detailPekerjaUiState = MutableStateFlow<DetailPekerjaUiState>(DetailPekerjaUiState.Loading)
    val detailPekerjaUiState: StateFlow<DetailPekerjaUiState> = _detailPekerjaUiState.asStateFlow()

    init {
        getPekerjaById(_idPekerja)
    }

     fun getPekerjaById(id: String) {
        viewModelScope.launch {
            _detailPekerjaUiState.value = DetailPekerjaUiState.Loading
            _detailPekerjaUiState.value = try {
                val pekerja = pekerjaRepository.getPekerjaById(id)
                DetailPekerjaUiState.Success(pekerja)
            } catch (e: IOException) {
                DetailPekerjaUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailPekerjaUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}