package com.example.lotterybalance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boletos_table")
data class BoletoEntity(
    @PrimaryKey val numeroSerie: Long,
    val tipo: String,
    val fecha: Long,
    val precio: Double,
    val combinaciones: List<String>,
    val reintegro: String

)


@Entity(tableName = "premio_table")
data class PremioEntity(
    @PrimaryKey val boletoId: Long,
    val premio: Double?

)

