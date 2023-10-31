package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("SimpleDateFormat", "RememberReturnType")
@Composable
fun LazyFila(
    boletoModel: BoletoViewModel,
    lista: List<BoletoEntity>

) {
    val formatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    val coroutine = rememberCoroutineScope()


    // Dialogo al pulsar el icono "info"
    var showInfo by rememberSaveable { mutableStateOf(false) }

    // lazyRow
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 10)
    val showScrollButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 2 } }
    var selectedKey by rememberSaveable { mutableIntStateOf(0) }

    LazyRow(
        modifier = Modifier.padding(2.dp, 6.dp),
        state = listState
    ) {
        itemsIndexed(lista) { index, boleto ->
            selectedKey = index

            BoletoCard(
                tipo = boleto.tipo,
                fecha = formatter.format(boleto.fecha),
                precio = boleto.precio,
                modifier = Modifier,
                onConfirm = {
                    showInfo = true
                    coroutine.launch {
                        boletoModel.loadBoletoByID(boleto.numeroSerie)
                    }

                }
            )

        }
    }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(lista.size) { iteration ->
            val color = if (selectedKey == iteration)
                MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.onPrimary
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(6.dp)
            )
        }
    }


/*
    // LazyRow Back Button
    if (showScrollButton) {
        SmallFloatingActionButton(
            onClick = {
                coroutine.launch { listState.animateScrollToItem(0) }
            },
            modifier = Modifier
                .padding(20.dp)
                .size(60.dp, 24.dp),
            shape = ShapeDefaults.ExtraLarge,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        )
        {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
        }
    }

 */


    InfoDialog(
        boletoModel,
        show = showInfo,
        onDismiss = { showInfo = false }
    )

}
