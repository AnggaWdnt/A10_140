package com.example.a10_140.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Tanaman
import com.example.a10_140.repository.TanamanRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class TanamanUiState{
    data class Success(val tanaman: List<Tanaman>): TanamanUiState()
    object Error: TanamanUiState()
    object Loading: TanamanUiState()
}

class HomeTanamanViewModel (private val tanaman: TanamanRepository): ViewModel(){
    var tanamanUiState: TanamanUiState by mutableStateOf(TanamanUiState.Loading)
        private set

    init {
        gettanaman()
    }

    fun gettanaman(){
        viewModelScope.launch {
            tanamanUiState = TanamanUiState.Loading
            tanamanUiState = try {
                TanamanUiState.Success(tanaman.getTanaman())
            }catch (e:IOException){
                TanamanUiState.Error
            }catch (e: HttpException){
                TanamanUiState.Error
            }
        }
    }

    fun deletetanaman(id:String){
        viewModelScope.launch {
            try {
                tanaman.deleteTanaman(id)
            }catch (e: IOException){
                TanamanUiState.Error
            }catch (e:HttpException){
                TanamanUiState.Error
            }
        }
    }
}