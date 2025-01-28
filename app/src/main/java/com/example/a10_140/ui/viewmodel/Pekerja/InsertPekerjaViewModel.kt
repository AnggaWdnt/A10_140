package com.example.a10_140.ui.viewmodel.Pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_140.model.Pekerja
import com.example.a10_140.model.Tanaman
import com.example.a10_140.repository.PekerjaRepository
import com.example.a10_140.repository.TanamanRepository
import com.example.a10_140.ui.viewmodel.Tanaman.toInsertUiEvent
import kotlinx.coroutines.launch


class InsertPekerjaViewModel (private val pekerja: PekerjaRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPekerjaState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPekerja(){
        viewModelScope.launch {
            try {
                pekerja.insertPekerja(uiState.insertUiEvent.toPekerja())
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
    val idPekerja: String = "",
    val namaPekerja: String = "",
    val jabatan: String = "",
    val kontakpekerja: String = ""
)

fun InsertUiEvent.toPekerja(): Pekerja = Pekerja(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakPekerja = kontakpekerja
)

fun Pekerja.toUiStatePekerja():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pekerja.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    idPekerja = idPekerja,
    namaPekerja = namaPekerja,
    jabatan = jabatan,
    kontakpekerja = kontakPekerja
)