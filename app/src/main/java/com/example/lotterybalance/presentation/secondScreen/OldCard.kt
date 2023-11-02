package com.example.lotterybalance.presentation.secondScreen

/*
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

         */