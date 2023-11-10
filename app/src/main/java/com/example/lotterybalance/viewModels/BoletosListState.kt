package com.example.lotterybalance.viewModels

import com.example.lotterybalance.database.entities.BoletoEntity

data class BoletosListState(

    val estadoBoletos: List<BoletoEntity> = emptyList()

)
