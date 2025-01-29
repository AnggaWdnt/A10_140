package com.example.a10_140.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a10_140.ui.Home.DestinasiHomeScreen
import com.example.a10_140.ui.Home.HomeScreen
import com.example.a10_140.ui.view.AktivitasPertanian.DestinasiDetailAktivitas
import com.example.a10_140.ui.view.AktivitasPertanian.DestinasiEntryAktivitas
import com.example.a10_140.ui.view.AktivitasPertanian.DestinasiHomeAktivitas
import com.example.a10_140.ui.view.AktivitasPertanian.DetailAktivitasView
import com.example.a10_140.ui.view.AktivitasPertanian.EntryAktivitasScreen
import com.example.a10_140.ui.view.AktivitasPertanian.HomeAktivitasScreen
import com.example.a10_140.ui.view.AktivitasPertanian.UpdateAktivitasView
import com.example.a10_140.ui.view.CatatanPanen.CatatanPanenHomeScreen
import com.example.a10_140.ui.view.CatatanPanen.DestinasiDetailCatatan
import com.example.a10_140.ui.view.CatatanPanen.DestinasiEntryCatatan
import com.example.a10_140.ui.view.CatatanPanen.DestinasiHomeCatatanPanen
import com.example.a10_140.ui.view.CatatanPanen.DetailCatatanView
import com.example.a10_140.ui.view.CatatanPanen.EntryCatatanScreen
import com.example.a10_140.ui.view.CatatanPanen.UpdateCatatanView
import com.example.a10_140.ui.view.Pekerja.DestinasiDetailpekerja
import com.example.a10_140.ui.view.Pekerja.DestinasiEntryPekerja
import com.example.a10_140.ui.view.Pekerja.DestinasiHomePekerja
import com.example.a10_140.ui.view.Pekerja.DetailPekerjaView
import com.example.a10_140.ui.view.Pekerja.EntryPekerjaScreen
import com.example.a10_140.ui.view.Pekerja.PekerjaHomeScreen
import com.example.a10_140.ui.view.Pekerja.UpdatePekerjaView
import com.example.a10_140.ui.view.tanaman.DestinasiDetailTanaman
import com.example.a10_140.ui.view.tanaman.DestinasiEntryTanaman
import com.example.a10_140.ui.view.tanaman.DestinasiHomeTanaman
import com.example.a10_140.ui.view.tanaman.DetailTanamanView
import com.example.a10_140.ui.view.tanaman.EntryTanamanScreen
import com.example.a10_140.ui.view.tanaman.HomeTanamanScreen
import com.example.a10_140.ui.view.tanaman.UpdateTanamanView


@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHomeScreen.route,
        modifier = Modifier
    ) {
        // Home
        composable(DestinasiHomeScreen.route) {
            HomeScreen(
                onTanamanstart = {
                    navController.navigate(DestinasiHomeTanaman.route)
                },
                onPekerjastart = {
                    navController.navigate(DestinasiHomePekerja.route)
                },
                onCatatanPanenstart = {
                    navController.navigate(DestinasiHomeCatatanPanen.route)
                },
                onAktivitasPertaniantart = {
                    navController.navigate(DestinasiHomeAktivitas.route)
                },
                modifier = Modifier
            )
        }

        //tanaman
        composable(DestinasiHomeTanaman.route) {
            HomeTanamanScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntryTanaman.route)},
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailTanaman.route}/$id")
                },
                navigateToUpdate = { id ->
                    navController.navigate("update_merk/$id") // Navigasi ke update
                },
                navigateBack = {navController.navigate(DestinasiHomeScreen.route) {
                    popUpTo(DestinasiHomeScreen.route) {
                        inclusive = true
                    }
                } }
            )
        }

        composable(
            route = "${DestinasiDetailTanaman.route}/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailTanamanView(
                idTanaman = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(DestinasiEntryTanaman.route) {
            EntryTanamanScreen(navigateBack = {
                navController.navigate(DestinasiHomeTanaman.route) {
                    popUpTo(DestinasiHomeTanaman.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = "update_tanaman/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            UpdateTanamanView(
                idTanaman = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Pekerja
        composable(DestinasiHomePekerja.route) {
            PekerjaHomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntryPekerja.route)},
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailpekerja.route}/$id")
                },
                navigateToUpdate = { id ->
                    navController.navigate("update_pemasok/$id") // Navigasi ke update
                },
                navigateBack = {navController.navigate(DestinasiHomeScreen.route) {
                    popUpTo(DestinasiHomeScreen.route) {
                        inclusive = true
                    }
                } }
            )
        }

        composable(
            route = "${DestinasiDetailpekerja.route}/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailPekerjaView(
                idPekerja = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(DestinasiEntryPekerja.route) {
            EntryPekerjaScreen(navigateBack = {
                navController.navigate(DestinasiHomePekerja .route) {
                    popUpTo(DestinasiHomePekerja.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = "update_pemasok/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            UpdatePekerjaView(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Catatan
        composable(DestinasiHomeCatatanPanen.route) {
            CatatanPanenHomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntryCatatan.route)},
                onDetailClick = { id ->
                    navController.navigate("${DestinasiEntryCatatan.route}/$id")
                },
                navigateToUpdate = { id ->
                    navController.navigate("update_kategori/$id") // Navigasi ke update
                },
                navigateBack = {navController.navigate(DestinasiHomeScreen.route) {
                    popUpTo(DestinasiHomeScreen.route) {
                        inclusive = true
                    }
                } }
            )
        }

        composable(
            route = "${DestinasiDetailCatatan.route}/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailCatatanView(
                idPanen = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(DestinasiEntryCatatan.route) {
            EntryCatatanScreen(navigateBack = {
                navController.navigate(DestinasiHomeCatatanPanen.route) {
                    popUpTo(DestinasiHomeCatatanPanen.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = "update_kategori/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            UpdateCatatanView(
                idCatatan = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Aktivitas
        composable(DestinasiHomeAktivitas.route) {
            HomeAktivitasScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryAktivitas.route) },
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailTanaman.route}/$id")
                },
                navigateToUpdate = { id ->
                    navController.navigate("update_aktivitas/$id") // Navigasi ke update
                },
                navigateBack = {
                    navController.navigate(DestinasiHomeScreen.route) {
                        popUpTo(DestinasiHomeScreen.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(DestinasiEntryAktivitas.route) {
            EntryAktivitasScreen(navigateBack = {
                navController.navigate(DestinasiHomeAktivitas.route) {
                    popUpTo(DestinasiHomeAktivitas.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = "${DestinasiDetailAktivitas.route}/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailAktivitasView(
                idAktivitas = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = "update_produk/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            UpdateAktivitasView(
                idAktivitas = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}