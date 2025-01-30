package com.example.a10_140.ui.viewmodel.Tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Tanaman
import com.example.a10_140.repository.TanamanRepository
import kotlinx.coroutines.launch


class InsertTanamanViewModel (private val tanaman: TanamanRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertTanamanState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun insertTanaman(){
        viewModelScope.launch {
            try {
                tanaman.insertTanaman(uiState.insertUiEvent.toTanaman())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertUiState(
    val insertUiEvent : InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idTanaman: String = "",
    val namaTanaman: String = "",
    val periodeTanaman: String = "",
    val deskripsiTanaman: String = ""
)

fun InsertUiEvent.toTanaman(): Tanaman = Tanaman(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanaman = periodeTanaman,
    deskripsiTanaman = deskripsiTanaman
)

fun Tanaman.toUiStateTanaman():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Tanaman.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idTanaman = idTanaman,
    namaTanaman = namaTanaman,
    periodeTanaman = periodeTanaman,
    deskripsiTanaman = deskripsiTanaman
)