package com.example.a10_140.ui.view.AktivitasPertanian

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.UpdateAktivitasUiEvent
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.UpdateAktivitasUiState
import com.example.a10_140.ui.viewmodel.AktivitasPertanian.UpdateAktivitasViewModel
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateAktivitasView(
    navigateBack: () -> Unit,
    idAktivitas: String,
    modifier: Modifier = Modifier,
    viewModel: UpdateAktivitasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val AktivitasuiState = viewModel.AktivitasuiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(idAktivitas) {
        viewModel.getAktivitasById(idAktivitas)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = "Update Tanaman",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBodyAktivitas(
            updateAktivitasUiState = AktivitasuiState,
            onAktivitasValueChange = viewModel::UpdateAktivitasState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateAktivitas()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun UpdateBodyAktivitas(
    updateAktivitasUiState: UpdateAktivitasUiState,
    onAktivitasValueChange: (UpdateAktivitasUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputAktivitas(
            updateAktivitasUiEvent = updateAktivitasUiState.updateAktivitasUiEvent,
            onValueChange = onAktivitasValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputAktivitas(
    updateAktivitasUiEvent: UpdateAktivitasUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateAktivitasUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateAktivitasUiEvent.idAktivitas,
            onValueChange = { onValueChange(updateAktivitasUiEvent.copy(idAktivitas = it)) },
            label = { Text("ID Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateAktivitasUiEvent.idTanaman,
            onValueChange = { onValueChange(updateAktivitasUiEvent.copy(idTanaman = it)) },
            label = { Text("ID Tanaman") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateAktivitasUiEvent.idpekerja,
            onValueChange = { onValueChange(updateAktivitasUiEvent.copy(idpekerja = it)) },
            label = { Text("ID Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateAktivitasUiEvent.tanggalAktivitas,
            onValueChange = { onValueChange(updateAktivitasUiEvent.copy(tanggalAktivitas = it)) },
            label = { Text("Tanggal Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateAktivitasUiEvent.deskripsiAktivitas,
            onValueChange = { onValueChange(updateAktivitasUiEvent.copy(deskripsiAktivitas = it)) },
            label = { Text("Deskripsi Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}
