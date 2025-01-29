package com.example.a10_140.ui.viewmodel.AktivitasPertanian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.repository.AktivitasPertanianRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class AktivitasUiState{
    data class Success(val aktivitas: List<Aktivitaspertanian>): AktivitasUiState()
    object Error: AktivitasUiState()
    object Loading: AktivitasUiState()
}

class HomeAktivitasViewModel (private val aktivitas: AktivitasPertanianRepository): ViewModel(){
    var aktivitasUiState: AktivitasUiState by mutableStateOf(AktivitasUiState.Loading)
        private set

    init {
        getaktivitas()
    }

    fun getaktivitas(){
        viewModelScope.launch {
            aktivitasUiState = AktivitasUiState.Loading
            aktivitasUiState = try {
                AktivitasUiState.Success(aktivitas.getAktivitas())
            }catch (e:IOException){
                AktivitasUiState.Error
            }catch (e: HttpException){
                AktivitasUiState.Error
            }
        }
    }

    fun deleteAktivitas(id:String){
        viewModelScope.launch {
            try {
                aktivitas.deleteAktivitas(id)
            }catch (e: IOException){
                AktivitasUiState.Error
            }catch (e: HttpException){
                AktivitasUiState.Error
            }
        }
    }
}