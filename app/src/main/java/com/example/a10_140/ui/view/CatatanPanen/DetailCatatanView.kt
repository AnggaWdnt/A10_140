package com.example.a10_140.ui.view.CatatanPanen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a10_140.model.CatatanPanen
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.CatatanPanen.DetailCatatanPanenUiState
import com.example.a10_140.ui.viewmodel.CatatanPanen.DetailCatatanViewModel
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiDetailCatatan: DestinasiNavigasi {
    override val route = "detailCatatan"
    override val titleRes = "Detail Catatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCatatanView(
    idPanen: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailCatatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(idPanen) {
        viewModel.getCatatanPanenById(idPanen)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Catatan Panen",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        val uiState by viewModel.detailCatatanlUiState.collectAsState()

        Column(
            modifier = modifier
                .padding(padding)
                .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Memberi jarak antara top app bar dan card detail
            BodyDetailCatatan(
                detailCatatanUiState = uiState,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BodyDetailCatatan(
    detailCatatanUiState: DetailCatatanPanenUiState,
    modifier: Modifier = Modifier
) {
    when (detailCatatanUiState) {
        is DetailCatatanPanenUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DetailCatatanPanenUiState.Success -> {
            val catatan = detailCatatanUiState.catatanPanen
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Menghilangkan padding vertikal
            ) {
                ItemDetailCatatan(catatan = catatan)
            }
        }
        is DetailCatatanPanenUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailCatatanUiState.message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailCatatan(
    catatan: CatatanPanen,
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
            ComponentDetailCatatan(judul = "ID Panen", isinya = catatan.idPanen)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailCatatan(judul = "ID Tanaman", isinya = catatan.idTanaman)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailCatatan(judul = "Tanggal Panen", isinya = catatan.tanggalPanen)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailCatatan(judul = "Jumlah Panen", isinya = catatan.jumlahPanen)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailCatatan(judul = "Keterangan", isinya = catatan.keterangan)
        }
    }
}

@Composable
fun ComponentDetailCatatan(
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