package com.example.lotterybalance.presentation.firstScreen

/*

// DatePicker
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val formatter = SimpleDateFormat("dd MMM yy")
    if (openDialog.value) {
        val state = rememberDateRangePickerState()
        val confirmEnabled = derivedStateOf { state.selectedEndDateMillis != null }
        val startDay = state.selectedStartDateMillis
        val endDay = state.selectedEndDateMillis
        val selectedBoletos = boletoModel.sortidoBoletos

        if (confirmEnabled.value){
            boletoModel.sortByDate(formatter.format(startDay), formatter.format(endDay))
            Log.i("range", "$selectedBoletos")
        }

        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {

                        openDialog.value = false
                    },
                    enabled = state.selectedEndDateMillis != null
                ) {
                    Text(text = "Save", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel", color = Color.White)
                }
            }
        ) {
            DateRangePicker(
                state = state,
                modifier = Modifier.weight(1f),
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.White,
                    todayDateBorderColor = Color(0xFFFFAB91),
                    selectedDayContainerColor = Color(0xFFFFAB91),
                    dayInSelectionRangeContainerColor = Color(0xFFFFCCBC),
                    dayInSelectionRangeContentColor = Color(0xFF5F5D5D),


                ),
                title = {
                    Text(text = "Selecionar el rango de fechas", modifier = Modifier
                        .padding(16.dp))
                        },
                headline = {
                    Row(modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)) {
                        Box(Modifier.weight(1f)) {
                            (if(state.selectedStartDateMillis!=null)
                                state.selectedStartDateMillis?.let { formatter.format(it) }
                            else "Inicio")?.let { Text(text = it) }
                        }
                        Box(Modifier.weight(1f)) {
                            (if(state.selectedEndDateMillis!=null)
                                state.selectedEndDateMillis?.let { formatter.format(it) }
                            else "Fin")?.let { Text(text = it) }
                        }
                        Box(Modifier.weight(0.2f)) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Okk")
                        }
                    }
                }
            )
        }

    }

 */