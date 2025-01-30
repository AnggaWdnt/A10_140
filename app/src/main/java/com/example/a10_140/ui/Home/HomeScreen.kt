package com.example.a10_140.ui.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_140.R
import com.example.a10_140.ui.navigation.DestinasiNavigasi


object DestinasiHomeScreen : DestinasiNavigasi {
    override val route = "homescreen"
    override val titleRes = "Home Screen"
}


@Composable
fun HomeScreen(
    onTanamanstart: () -> Unit,
    onPekerjastart: () -> Unit,
    onCatatanPanenstart: () -> Unit,
    onAktivitasPertaniantart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF32CD32), Color(0xFF228B22)) // Hijau tanaman
                )
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon Tanaman
        Image(
            painter = painterResource(id = R.drawable.tanaman), // Ganti dengan nama ikon Anda
            contentDescription = "Ikon Tanaman",
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Judul
        Text(
            text = "Pertanian Management",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol 1
        Button(
            onClick = { onTanamanstart() },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Tanaman",
                fontSize = 18.sp,
                color = Color(0xFF006400),
                fontWeight = FontWeight.Bold
            )
        }

        // Tombol 2
        Button(
            onClick = { onPekerjastart() },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Pekerja",
                fontSize = 18.sp,
                color = Color(0xFF006400),
                fontWeight = FontWeight.Bold
            )
        }

        // Tombol 3
        Button(
            onClick = { onCatatanPanenstart() },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Catatan Panen",
                fontSize = 18.sp,
                color = Color(0xFF006400),
                fontWeight = FontWeight.Bold
            )
        }

        // Tombol 4
        Button(
            onClick = { onAktivitasPertaniantart() },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Aktivitas Pertanian",
                fontSize = 18.sp,
                color = Color(0xFF006400),
                fontWeight = FontWeight.Bold
            )
        }
    }
}