package com.example.a10_140.ui.view.Pekerja

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
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.UpdateUiEvent
import com.example.a10_140.ui.viewmodel.Pekerja.UpdateUiState
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePekerjaScreen(
    navigateBack: () -> Unit,
    idPekerja: String,
    modifier: Modifier = Modifier,
    viewModel: UpdatePekerjaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(idPekerja) {
        viewModel.getPekerjaById(idPekerja)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = "Update Pekerja",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBody(
            updateUiState = uiState,
            onPekerjaValueChange = viewModel::updateUpdatePekerjaState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePekerja()
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
fun UpdateBody(
    updateUiState: UpdateUiState,
    onPekerjaValueChange: (UpdateUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            updateUiEvent = updateUiState.updateUiEvent,
            onValueChange = onPekerjaValueChange,
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
fun FormInput(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateUiEvent.idPekerja,
            onValueChange = { onValueChange(updateUiEvent.copy(idPekerja = it)) },
            label = { Text("id pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.namaPekerja,
            onValueChange = { onValueChange(updateUiEvent.copy(namaPekerja = it)) },
            label = { Text("Nama Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.jabatan,
            onValueChange = { onValueChange(updateUiEvent.copy(jabatan = it)) },
            label = { Text("jabatan pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.kontakpekerja,
            onValueChange = { onValueChange(updateUiEvent.copy(kontakpekerja = it)) },
            label = { Text("Kontak Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}
