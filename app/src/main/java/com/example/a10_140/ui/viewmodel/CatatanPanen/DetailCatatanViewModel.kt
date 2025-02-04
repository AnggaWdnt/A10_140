package com.example.a10_140.ui.viewmodel.CatatanPanen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.repository.CatatanPanenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class DetailCatatanPanenUiState {
    data class Success(val catatanPanen: CatatanPanen) : DetailCatatanPanenUiState()
    data class Error(val message: String) : DetailCatatanPanenUiState()
    object Loading : DetailCatatanPanenUiState()
}

class DetailCatatanViewModel(
    savedStateHandle: SavedStateHandle,
    private val CatatanPanen: CatatanPanenRepository
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle["id"])

    private val _detailCatatannUiState = MutableStateFlow<DetailCatatanPanenUiState>(DetailCatatanPanenUiState.Loading)
    val detailCatatanlUiState: StateFlow<DetailCatatanPanenUiState> = _detailCatatannUiState.asStateFlow()

    init {
        getCatatanPanenById(_id)
    }

    fun getCatatanPanenById(id: String) {
        viewModelScope.launch {
            _detailCatatannUiState.value = DetailCatatanPanenUiState.Loading
            _detailCatatannUiState.value = try {
                val catatan = CatatanPanen.getCatatanById(id)
                DetailCatatanPanenUiState.Success(catatan)
            } catch (e: IOException) {
                DetailCatatanPanenUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailCatatanPanenUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}