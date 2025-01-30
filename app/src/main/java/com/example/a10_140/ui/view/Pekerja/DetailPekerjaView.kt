package com.example.a10_140.ui.view.Pekerja

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a10_140.model.Pekerja
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.Pekerja.DetailPekerjaUiState
import com.example.a10_140.ui.viewmodel.Pekerja.DetailPekerjaViewModel
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiDetailpekerja : DestinasiNavigasi {
    override val route = "detail_pekerja"
    override val titleRes = "Detail pekerja"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPekerjaView(
    navigateBack: () -> Unit,
    idPekerja: String,
    navigateToUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPekerjaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(idPekerja) {
        viewModel.getPekerjaById(idPekerja)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Pekerja",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToUpdate(idPekerja) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Pekerja")
            }
        }
    ) { padding ->
        val uiState by viewModel.detailPekerjaUiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is DetailPekerjaUiState.Loading -> LoadingView()
                is DetailPekerjaUiState.Success -> {
                    val pekerja = (uiState as DetailPekerjaUiState.Success).pekerja
                    DetailContent(pekerja = pekerja)
                }
                is DetailPekerjaUiState.Error -> ErrorView(
                    message = (uiState as DetailPekerjaUiState.Error).message
                )
            }
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DetailContent(
    pekerja: Pekerja,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
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
            ComponentDetailPekerja(judul = "ID Pekerja", isinya = pekerja.idPekerja)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailPekerja(judul = "Nama Pekerja", isinya = pekerja.namaPekerja)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailPekerja(judul = "Jabatan", isinya = pekerja.jabatan)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailPekerja(judul = "No.Telepon Pekerja", isinya = pekerja.kontakPekerja)
        }
    }
}

@Composable
fun ComponentDetailPekerja(
    judul: String,
    isinya: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul:",
            fontSize = 14.sp,
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
