package com.example.a10_140.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.repository.PekerjaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PekerjaViewModel(private val repository: PekerjaRepository) : ViewModel() {

    private val _pekerjaList = MutableStateFlow<List<Pekerja>>(emptyList())
    val pekerjaList: StateFlow<List<Pekerja>> = _pekerjaList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchPekerja() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getDaftarPekerja()
                _pekerjaList.value = response.data
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat data pekerja: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addPekerja(pekerja: Pekerja) {
        viewModelScope.launch {
            try {
                repository.insertPekerja(pekerja)
                fetchPekerja()
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menambahkan pekerja: ${e.message}"
            }
        }
    }

    fun editPekerja(pekerja: Pekerja) {
        viewModelScope.launch {
            try {
                repository.updatePekerja(pekerja.idPekerja, pekerja)
                fetchPekerja()
            } catch (e: Exception) {
                _errorMessage.value = "Gagal mengedit pekerja: ${e.message}"
            }
        }
    }

    fun deletePekerja(id: String) {
        viewModelScope.launch {
            try {
                repository.deletePekerja(id)
                fetchPekerja()
            } catch (e: Exception) {
                _errorMessage.value = "Gagal menghapus pekerja: ${e.message}"
            }
        }
    }
}
