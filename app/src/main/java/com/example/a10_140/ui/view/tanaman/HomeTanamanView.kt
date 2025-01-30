package com.example.a10_140.ui.view.tanaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a10_140.R
import com.example.a10_140.model.Tanaman
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.view.AktivitasPertanian.OnError
import com.example.a10_140.ui.view.AktivitasPertanian.OnLoading
import com.example.a10_140.ui.viewmodel.PenyediaViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.HomeTanamanViewModel
import com.example.a10_140.ui.viewmodel.Tanaman.TanamanUiState

object DestinasiHomeTanaman: DestinasiNavigasi {
    override val route = "hometanaman1"
    override val titleRes = "Home Tanaman"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTanamanScreen(
    navigateToItemEntry: () -> Unit,
    navigateToUpdate: (String) -> Unit,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeTanamanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeTanaman.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.gettanaman()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Tanaman")
            }
        }
    ) { innerPadding ->
        HomeTanamanStatus(
            tanamanUiState = viewModel.tanamanUiState,
            retryAction = { viewModel.gettanaman() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onUpdateClick = navigateToUpdate,
            onDeleteClick = {
                viewModel.deletetanaman(it.idTanaman)
                viewModel.gettanaman()
            }
        )
    }
}

@Composable
fun HomeTanamanStatus(
    tanamanUiState: TanamanUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tanaman) -> Unit = {},
    onUpdateClick: (String) -> Unit,
    onDetailClick: (String) -> Unit
) {
    when (tanamanUiState) {
        is TanamanUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is TanamanUiState.Success ->
            if (tanamanUiState.tanaman.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Tanaman")
                }
            } else {
                TanamanLayout(
                    tanaman = tanamanUiState.tanaman,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idTanaman)
                    },
                    onUpdateClick = {
                        onUpdateClick(it.idTanaman)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is TanamanUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun TanamanLayout(
    tanaman: List<Tanaman>,
    modifier: Modifier = Modifier,
    onDetailClick: (Tanaman) -> Unit,
    onUpdateClick: (Tanaman) -> Unit,
    onDeleteClick: (Tanaman) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tanaman) { item ->
            TanamanCard(
                tanaman = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(item) },
                onUpdateClick = {
                    onUpdateClick(item)
                },
                onDeleteClick = {
                    onDeleteClick(item)
                }
            )
        }
    }
}

@Composable
fun TanamanCard(
    tanaman: Tanaman,
    modifier: Modifier = Modifier,
    onUpdateClick: (Tanaman) -> Unit = {},
    onDeleteClick: (Tanaman) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tanaman.namaTanaman,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onUpdateClick(tanaman) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Update Tanaman"
                    )
                }
                IconButton(onClick = { onDeleteClick(tanaman) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus Tanaman"
                    )
                }
            }
            Text(
                text = "Periode Tanaman: ${tanaman.periodeTanaman}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Deskripsi Tanaman: ${tanaman.deskripsiTanaman}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
