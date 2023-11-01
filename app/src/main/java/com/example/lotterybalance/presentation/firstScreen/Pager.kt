package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    modifier: Modifier = Modifier,
    boletoModel: BoletoViewModel

    ) {
    val formatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    var showInfo by rememberSaveable { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()


    val lista = boletoModel.boletosListState.value.estadoBoletos
    val pagerState = rememberPagerState(
        pageCount = { lista.takeLast(10).size },
        initialPageOffsetFraction = 0f
    )
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 64.dp),
        pageSize = PageSize.Fixed(260.dp),
    ) { page ->
        val boleto = lista.sortedBy { it.fecha }[page]

        Card(
            modifier
                .clickable {
                    showInfo = true
                    coroutine.launch {
                        boletoModel.loadBoletoByID(boleto.numeroSerie)
                    }
                }
                .size(280.dp)
                .padding(4.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                        .also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                },
            shape = ShapeDefaults.Large,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = boleto.tipo,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )

            }
            // Multimedia
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = modifier.padding(top = 20.dp)) {
                    Text(
                        text = "Fecha",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontFamily = FontFamily.Default,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = formatter.format(boleto.fecha),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge

                    )
                }
                Column(modifier = modifier.padding(bottom = 30.dp)) {
                    Text(
                        text = "Precio:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontFamily = FontFamily.Default,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${boleto.precio} ${Typography.euro}",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge

                    )
                }
            }
        }

    }
    // Pager Indicator
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.onPrimary
            Box(
                modifier = Modifier
                    .clickable {
                        coroutine.launch {
                            pagerState.animateScrollToPage(iteration)
                        }
                    }
                    .padding(3.dp)
                    .clip(ShapeDefaults.ExtraLarge)
                    .background(color)
                    .size(10.dp, 4.dp)
            )
        }
    }


    InfoDialog(
        boletoModel,
        show = showInfo,
        onDismiss = { showInfo = false }
    )
}