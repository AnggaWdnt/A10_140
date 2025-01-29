package com.example.a10_140.ui.viewmodel.Tanaman

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Tanaman
import com.example.a10_140.repository.TanamanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class DetailTanamanUiState {
    data class Success(val tanaman: Tanaman) : DetailTanamanUiState()
    data class Error(val message: String) : DetailTanamanUiState()
    object Loading : DetailTanamanUiState()
}

class DetailTanamanViewModel(
    savedStateHandle: SavedStateHandle,
    private val tanamanRepository: TanamanRepository
) : ViewModel() {
    private val _idTanaman: String = checkNotNull(savedStateHandle["id"])

    private val _detailTanamanUiState = MutableStateFlow<DetailTanamanUiState>(DetailTanamanUiState.Loading)
    val detailTanamanUiState: StateFlow<DetailTanamanUiState> = _detailTanamanUiState.asStateFlow()

    init {
        getTanamanById(_idTanaman)
    }

        fun getTanamanById(idTanaman: String) {
        viewModelScope.launch {
            _detailTanamanUiState.value = DetailTanamanUiState.Loading
            _detailTanamanUiState.value = try {
                val tanaman = tanamanRepository.getTanamanById(idTanaman)
                DetailTanamanUiState.Success(tanaman)
            } catch (e: IOException) {
                DetailTanamanUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailTanamanUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}