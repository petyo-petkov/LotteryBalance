package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BoletoCard(
    tipo: String,
    fecha: String,
    precio: Double,
    modifier: Modifier,
    onConfirm: () -> Unit,
) {
    ElevatedCard(
        modifier
            .clickable { onConfirm() }
            .size(160.dp, 240.dp)
            .padding(4.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(160.dp, 40.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = tipo,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )

        }
        // Multimedia
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Fecha",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = fecha,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium

            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Precio:",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$precio ${Typography.euro}",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium

            )
        }
    }

}

