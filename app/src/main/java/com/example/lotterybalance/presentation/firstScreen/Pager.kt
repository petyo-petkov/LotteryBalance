package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.lotterybalance.R
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
        contentPadding = PaddingValues(horizontal = 70.dp),
        pageSize = PageSize.Fixed(260.dp),
    ) { page ->
        val boleto = lista.sortedBy { it.fecha }[page]

        // Tarjeta
        Column(
            modifier = modifier
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var image: Painter = painterResource(id = R.drawable.logo)
            when (boleto.tipo) {
                "Primitiva" -> {
                    image = painterResource(id = R.drawable.la_primitiva)
                }

                "Euromillones" -> {
                    image = painterResource(id = R.drawable.euromillones)
                }

                "Bonoloto" -> {
                    image = painterResource(id = R.drawable.bonoloto)
                }

                "El Gordo" -> {
                    image = painterResource(id = R.drawable.el_godo)
                }

                "Loteria Nacional" -> {
                    image = painterResource(id = R.drawable.loteria_nacional)
                }
            }

            OutlinedCard(
                modifier = modifier
                    .size(260.dp, 60.dp),
                shape = AbsoluteRoundedCornerShape(
                    topLeft = 20.dp,
                    topRight = 20.dp,
                    bottomLeft = 0.dp,
                    bottomRight = 0.dp
                ),
                border = BorderStroke(1.dp, color = Color.Black)
            ) {
                Box(
                    modifier = modifier
                        .background(color = MaterialTheme.colorScheme.primary)
                        .fillMaxSize()
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = image,
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = modifier.padding(vertical = 4.dp))

            OutlinedCard(
                modifier = modifier
                    .size(260.dp, 240.dp),
                shape = AbsoluteRoundedCornerShape(
                    topLeft = 0.dp,
                    topRight = 0.dp,
                    bottomLeft = 20.dp,
                    bottomRight = 20.dp
                ),
                border = BorderStroke(1.dp, color = Color.Black)

            ) {
                Box(
                    modifier = modifier
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = boleto.tipo,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider(
                            modifier = modifier,
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )
                        Text(
                            text = formatter.format(boleto.fecha),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider(
                            modifier = modifier,
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            text = "${boleto.precio} ${Typography.euro}",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
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
            val color by animateColorAsState(
                if (pagerState.currentPage == iteration)
                    MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onPrimary, label = ""
            )

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