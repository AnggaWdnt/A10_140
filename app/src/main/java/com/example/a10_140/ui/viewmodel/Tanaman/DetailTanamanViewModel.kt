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
    private val tanaman: TanamanRepository
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle["id"])

    private val _detailUiState = MutableStateFlow<DetailTanamanUiState>(DetailTanamanUiState.Loading)
    val detailUiState: StateFlow<DetailTanamanUiState> = _detailUiState.asStateFlow()

    init {
        getTanamanById(_id)
    }

    private fun getTanamanById(id: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailTanamanUiState.Loading
            _detailUiState.value = try {
                val tanaman = tanaman.getTanamanById(id)
                DetailTanamanUiState.Success(tanaman)
            } catch (e: IOException) {
                DetailTanamanUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailTanamanUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}