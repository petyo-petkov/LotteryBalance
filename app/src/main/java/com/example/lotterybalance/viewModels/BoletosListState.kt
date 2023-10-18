package com.example.lotterybalance.viewModels

import com.example.lotterybalance.database.entities.BoletoEntity

data class BoletosListState(

    val boletosState: List<BoletoEntity> = emptyList()

)
