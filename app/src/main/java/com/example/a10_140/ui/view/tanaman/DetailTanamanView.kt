package com.example.a10_140.ui.view.tanaman

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a10_140.model.Tanaman
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.DetailTanamanUiState
import com.example.a10_140.ui.viewmodel.Tanaman.DetailTanamanViewModel

object DestinasiDetailTanaman : DestinasiNavigasi {
    override val route = "detail_tanaman"
    override val titleRes = "Detail Tanaman"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTanamanView(
idTanaman: String,
navigateBack: () -> Unit,
modifier: Modifier = Modifier,
viewModel: DetailTanamanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(idTanaman) {
        viewModel.getTanamanById(idTanaman)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Tanaman",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        val uiState by viewModel.detailTanamanUiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Memberi jarak antara top app bar dan card detail
            BodyDetailTanaman(
                detailTanamanUiState = uiState,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BodyDetailTanaman(
    detailTanamanUiState: DetailTanamanUiState,
    modifier: Modifier = Modifier
) {
    when (detailTanamanUiState) {
        is DetailTanamanUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DetailTanamanUiState.Success -> {
            val tanaman = detailTanamanUiState.tanaman
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
            ) {
                ItemDetailMerk(tanaman = tanaman)
            }
        }
        is DetailTanamanUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailTanamanUiState.message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailMerk(
    tanaman: Tanaman,
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
            ComponentDetailTanaman(judul = "ID Tanaman", isinya = tanaman.idTanaman)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailTanaman(judul = "Nama Tanaman", isinya = tanaman.namaTanaman)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailTanaman(judul = "Periode Tanaman", isinya = tanaman.periodeTanaman)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailTanaman(judul = "Deskripsi Tanaman", isinya = tanaman.deskripsiTanaman)
        }
    }
}

@Composable
fun ComponentDetailTanaman(
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