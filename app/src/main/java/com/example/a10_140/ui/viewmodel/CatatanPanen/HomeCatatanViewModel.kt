package com.example.a10_140.ui.viewmodel.CatatanPanen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.repository.CatatanPanenRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class CatatanUiState{
    data class Success(val Catatan: List<CatatanPanen>): CatatanUiState()
    object Error: CatatanUiState()
    object Loading: CatatanUiState()
}

class HomeCatatanViewModel (private val Catatan: CatatanPanenRepository): ViewModel(){
    var catatanUiState: CatatanUiState by mutableStateOf(CatatanUiState.Loading)
        private set

    init {
        getCatatan()
    }

    fun getCatatan(){
        viewModelScope.launch {
            catatanUiState = CatatanUiState.Loading
            catatanUiState = try {
                CatatanUiState.Success(Catatan.getCatatan())
            }catch (e:IOException){
                CatatanUiState.Error
            }catch (e: HttpException){
                CatatanUiState.Error
            }
        }
    }

    fun deleteCatatan(id:String){
        viewModelScope.launch {
            try {
                Catatan.deleteCatatan(id)
            }catch (e: IOException){
                CatatanUiState.Error
            }catch (e:HttpException){
                CatatanUiState.Error
            }
        }
    }
}