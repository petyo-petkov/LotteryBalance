package com.example.lotterybalance.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BoletoConPremio(
    @Embedded val boleto: BoletoEntity,
    @Relation(
        parentColumn = "numero_serie",
        entityColumn = "boletoId"
    )
    val premio: PremioEntity
)
