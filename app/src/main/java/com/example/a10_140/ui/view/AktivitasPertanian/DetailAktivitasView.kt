package com.example.a10_140.ui.view.AktivitasPertanian

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a10_140.model.Aktivitaspertanian
import com.example.a10_140.model.Pekerja
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.DetailAktivitasUiState
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.DetailAktivitasViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.DetailPekerjaUiState
import com.example.a10_140.ui.viewmodel.Pekerja.DetailPekerjaViewModel
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiDetailAktivitas : DestinasiNavigasi {
    override val route = "detail_aktivitas"
    override val titleRes = "Detail aktivitas"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAktivitasView(
    navigateBack: () -> Unit,
    idAktivitas: String,
    modifier: Modifier = Modifier,
    viewModel: DetailAktivitasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(idAktivitas) {
        viewModel.getAktivitasById(idAktivitas)
    }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Aktivitas",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        val uiState by viewModel.detailAktivitasUiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Memberi jarak antara top app bar dan card detail
            BodyDetailAktivitas(
                detailAktivitasUiState = uiState,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BodyDetailAktivitas(
    detailAktivitasUiState: DetailAktivitasUiState,
    modifier: Modifier = Modifier
) {
    when (detailAktivitasUiState) {
        is DetailAktivitasUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DetailAktivitasUiState.Success -> {
            val Aktivitas = detailAktivitasUiState.Aktivitas
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
            ) {
                ItemDetailAktivitas(Aktivitas = Aktivitas)
            }
        }
        is DetailAktivitasUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailAktivitasUiState.message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailAktivitas(
    Aktivitas: Aktivitaspertanian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailAktivitas(judul = "ID Aktivitas", isinya = Aktivitas.idAktivitas)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailAktivitas(judul = "ID Tanaman", isinya = Aktivitas.idTanaman)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailAktivitas(judul = "ID Pekerja", isinya = Aktivitas.idPekerja)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailAktivitas(judul = "Tanggal Aktivitas", isinya = Aktivitas.tanggalAktivitas)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailAktivitas(judul = "Deskripsi Aktivitas", isinya = Aktivitas.deskripsiAktivitas)
        }
    }
}

@Composable
fun ComponentDetailAktivitas(
    judul: String,
    isinya: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Text(
            text = isinya,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}