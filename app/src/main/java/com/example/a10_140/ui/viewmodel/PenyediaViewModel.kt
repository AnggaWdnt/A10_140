package com.example.a10_140.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a10_140.ui.A10Application
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.DetailAktivitasViewModel
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.HomeAktivitasViewModel
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.InsertAktivitasViewModel
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.UpdateAktivitasViewModel
import com.example.a10_140.ui.viewmodel.CatatanPanen.DetailCatatanViewModel
import com.example.a10_140.ui.viewmodel.CatatanPanen.HomeCatatanViewModel
import com.example.a10_140.ui.viewmodel.CatatanPanen.InsertCatatanViewModel
import com.example.a10_140.ui.viewmodel.CatatanPanen.UpdateCatatanViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.DetailPekerjaViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.HomePekerjaViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.InsertPekerjaViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.DetailTanamanViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.HomeTanamanViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.InsertTanamanViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.UpdateTanamanViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeTanamanViewModel(aplikasiA10().container.tanamanRepository) }
        initializer { InsertTanamanViewModel(aplikasiA10().container.tanamanRepository) }
        initializer { UpdateTanamanViewModel(aplikasiA10().container.tanamanRepository) }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            DetailTanamanViewModel(savedStateHandle, aplikasiA10().container.tanamanRepository)
        }

        initializer { HomePekerjaViewModel(aplikasiA10().container.pekerjaRepository) }
        initializer { InsertPekerjaViewModel(aplikasiA10().container.pekerjaRepository) }
        initializer { UpdatePekerjaViewModel(aplikasiA10().container.pekerjaRepository) }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            DetailPekerjaViewModel(savedStateHandle, aplikasiA10().container.pekerjaRepository)
        }

        initializer { HomeCatatanViewModel(aplikasiA10().container.catatanPanenRepository) }
        initializer { InsertCatatanViewModel(aplikasiA10().container.catatanPanenRepository) }
        initializer { UpdateCatatanViewModel(aplikasiA10().container.catatanPanenRepository) }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            DetailCatatanViewModel(savedStateHandle, aplikasiA10().container.catatanPanenRepository)
        }

        initializer { HomeAktivitasViewModel(aplikasiA10().container.aktivitasPertanianRepository) }
        initializer { InsertAktivitasViewModel(aplikasiA10().container.aktivitasPertanianRepository) }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            DetailAktivitasViewModel(savedStateHandle, aplikasiA10().container.aktivitasPertanianRepository)
        }
        initializer { UpdateAktivitasViewModel(aplikasiA10().container.aktivitasPertanianRepository) }
    }

fun CreationExtras.aplikasiA10(): A10Application =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as A10Application)
}