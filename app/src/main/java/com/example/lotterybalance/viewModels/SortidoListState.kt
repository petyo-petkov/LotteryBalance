package com.example.lotterybalance.viewModels

import com.example.lotterybalance.database.entities.BoletoEntity

data class SortidoListState(

    val sortidoState: List<BoletoEntity> = emptyList()

)
