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
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.lotterybalance.R
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    modifier: Modifier = Modifier,
    boletoModel: BoletoViewModel,
    lista: List<BoletoEntity>

) {
    val formatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    var showInfo by rememberSaveable { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 10,
        pageCount = { lista.size },
        initialPageOffsetFraction = 0f
    )
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 70.dp),
        pageSize = PageSize.Fixed(260.dp),
        beyondBoundsPageCount = 2,
        flingBehavior = fling

    ) { page ->
        val boleto = lista[page]

        // Tarjeta
        Column(
            modifier = modifier
                .size(280.dp)
                .padding(4.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

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
            var image: Painter = painterResource(id = R.drawable.new_logo)
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

                "Euro Dreams" -> {
                    image = painterResource(id = R.drawable.euro_dreams)
                }
            }

            // CABEZERA
            Card(
                modifier = modifier
                    .size(260.dp, 60.dp),
                shape = AbsoluteRoundedCornerShape(
                    topLeft = 20.dp,
                    topRight = 20.dp,
                    bottomLeft = 0.dp,
                    bottomRight = 0.dp
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary)
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

            // BODY
            Card(
                modifier = modifier
                    .clickable {
                        showInfo = true
                        boletoModel.loadBoletoByID(boleto.numeroSerie)
                    }
                    .size(260.dp, 240.dp),
                shape = AbsoluteRoundedCornerShape(
                    topLeft = 0.dp,
                    topRight = 0.dp,
                    bottomLeft = 20.dp,
                    bottomRight = 20.dp
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary)

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
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color by animateColorAsState(
                if (pagerState.currentPage == iteration)
                    MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.primary,
                label = ""
            )

            Box(
                modifier = modifier
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
        modifier = Modifier,
        boletoModel,
        show = showInfo,
        onDismiss = { showInfo = false }
    )
}