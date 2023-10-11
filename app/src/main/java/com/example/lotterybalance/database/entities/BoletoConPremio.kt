package com.example.lotterybalance.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BoletoConPremio(
    @Embedded val boleto: BoletoEntity,
    @Relation(
        parentColumn = "numeroSerie",
        entityColumn = "boletoId"
    )
    val premioEntity: PremioEntity?
)
