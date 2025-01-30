package com.example.a10_140.ui.view.Pekerja

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
import com.example.a10_140.model.Pekerja
import com.example.a10_140.ui.customwidget.CustomTopAppBar
import com.example.a10_140.ui.navigation.DestinasiNavigasi
import com.example.a10_140.ui.viewmodel.Pekerja.HomePekerjaViewModel
import com.example.a10_140.ui.viewmodel.Pekerja.PekerjaUiState
import com.example.a10_140.ui.viewmodel.PenyediaViewModel

object DestinasiHomePekerja: DestinasiNavigasi {
    override val route = "homepekerja"
    override val titleRes = "Home Pekerja"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PekerjaHomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToUpdate: (String) -> Unit,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomePekerjaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomePekerja.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getpekerja()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Pekerja")
            }
        }
    ) { innerPadding ->
        HomePekerjaStatus(
            pekerjaUiState = viewModel.pekerjaUiState,
            retryAction = { viewModel.getpekerja() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletepekerja(it.idPekerja)
                viewModel.getpekerja()
            },
            onUpdateClick = navigateToUpdate
        )
    }
}

@Composable
fun HomePekerjaStatus(
    pekerjaUiState: PekerjaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pekerja) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onUpdateClick: (String) -> Unit
) {
    when (pekerjaUiState) {
        is PekerjaUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is PekerjaUiState.Success ->
            if (pekerjaUiState.pekerja.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pekerja")
                }
            } else {
                PekerjaLayout(
                    perkerja = pekerjaUiState.pekerja,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPekerja)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onUpdateClick = {
                        onUpdateClick(it.idPekerja)
                    }
                )
            }
        is PekerjaUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction:()->Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PekerjaLayout(
    perkerja: List<Pekerja>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pekerja) -> Unit,
    onDeleteClick: (Pekerja) -> Unit = {},
    onUpdateClick: (Pekerja) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(perkerja) { item ->
            PekerjaCard(
                pekerja = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(item) },
                onDeleteClick = {
                    onDeleteClick(item)
                },
                onUpdateClick = {
                    onUpdateClick(item)
                }
            )
        }
    }
}

@Composable
fun PekerjaCard(
    pekerja: Pekerja,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pekerja) -> Unit = {},
    onUpdateClick: (Pekerja) -> Unit
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
                    text = pekerja.namaPekerja,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onUpdateClick(pekerja) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onDeleteClick(pekerja) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "Jabatan Pekerja: ${pekerja.jabatan}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Kontak Pekerja: ${pekerja.kontakPekerja}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
