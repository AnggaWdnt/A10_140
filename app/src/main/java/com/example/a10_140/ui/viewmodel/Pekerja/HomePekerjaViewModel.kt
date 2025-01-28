package com.example.a10_140.ui.viewmodel.Pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.repository.PekerjaRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class PekerjaUiState{
    data class Success(val pekerja: List<Pekerja>): PekerjaUiState()
    object Error: PekerjaUiState()
    object Loading: PekerjaUiState()
}

class HomePekerjaViewModel (private val pekerja: PekerjaRepository): ViewModel(){
    var pekerjaUiState: PekerjaUiState by mutableStateOf(PekerjaUiState.Loading)
        private set

    init {
        getpekerja()
    }

    fun getpekerja(){
        viewModelScope.launch {
            pekerjaUiState = PekerjaUiState.Loading
            pekerjaUiState = try {
                PekerjaUiState.Success(pekerja.getPekerja())
            }catch (e:IOException){
                PekerjaUiState.Error
            }catch (e: HttpException){
                PekerjaUiState.Error
            }
        }
    }

    fun deletepekerja(id:String){
        viewModelScope.launch {
            try {
                pekerja.deletePekerja(id)
            }catch (e: IOException){
                PekerjaUiState.Error
            }catch (e:HttpException){
                PekerjaUiState.Error
            }
        }
    }
}