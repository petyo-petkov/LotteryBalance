package com.example.lotterybalance.viewModels

import com.example.lotterybalance.database.entities.BoletoEntity

data class BoletoState(
    val estadoBoleto: BoletoEntity = BoletoEntity(
        numeroSerie = 0,
        tipo = "",
        precio = 0.0,
        fecha = 0,
        combinaciones = listOf(),
        reintegro = 0,
        premio = 0.0
    )

)
