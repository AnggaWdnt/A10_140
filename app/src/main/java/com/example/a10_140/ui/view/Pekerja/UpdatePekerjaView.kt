package com.example.a10_140.ui.view.Pekerja

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaErrorState
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaUiEvent
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaUiState
import com.example.a10_140.ui.viewmodel.Pekerja.UpdatePekerjaViewModel
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePekerjaView(
    navigateBack: () -> Unit,
    id: String,
    modifier: Modifier = Modifier,
    viewModel: UpdatePekerjaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uipekerjaState = viewModel.pekerjauiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(id) {
        viewModel.getPekerjaById(id)
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
        UpdateBodyPekerja(
            updatepekerjaUiState = uipekerjaState,
            onPekerjaValueChange = viewModel::updatePekerjaState,
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
fun UpdateBodyPekerja(
    updatepekerjaUiState: UpdatePekerjaUiState,
    onPekerjaValueChange: (UpdatePekerjaUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormUpdatePekerja(
            updatepekerjaUiEvent = updatepekerjaUiState.updatePekerjaUiEvent,
            onValueChange = onPekerjaValueChange,
            modifier = Modifier.fillMaxWidth(),
            errorState = updatepekerjaUiState.isEntryValid
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9900),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormUpdatePekerja(
    updatepekerjaUiEvent: UpdatePekerjaUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdatePekerjaUiEvent) -> Unit = {},
    enabled: Boolean = true,
    errorState: UpdatePekerjaErrorState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updatepekerjaUiEvent.idPekerja,
            onValueChange = { onValueChange(updatepekerjaUiEvent.copy(idPekerja = it)) },
            label = { Text("ID Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = errorState.idPekerja != null,
            supportingText = { Text(errorState.idPekerja ?: "") }
        )

        OutlinedTextField(
            value = updatepekerjaUiEvent.namaPekerja,
            onValueChange = { onValueChange(updatepekerjaUiEvent.copy(namaPekerja = it)) },
            label = { Text("Nama Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = errorState.namaPekerja != null,
            supportingText = { Text(errorState.namaPekerja ?: "") }
        )

        OutlinedTextField(
            value = updatepekerjaUiEvent.jabatan,
            onValueChange = { onValueChange(updatepekerjaUiEvent.copy(jabatan = it)) },
            label = { Text("jabatan pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = errorState.jabatan != null,
            supportingText = { Text(errorState.namaPekerja ?: "") }
        )
        OutlinedTextField(
            value = updatepekerjaUiEvent.kontakpekerja,
            onValueChange = { onValueChange(updatepekerjaUiEvent.copy(kontakpekerja = it)) },
            label = { Text("Kontak Pekerja") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = errorState.kontakPekerja != null,
            supportingText = { Text(errorState.kontakPekerja ?: "") }
        )
    }
}