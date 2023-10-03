package com.example.lotterybalance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boletos_table")
data class BoletoEntity(
   // @PrimaryKey(autoGenerate = true)
   // val id: Int = 0,
    @PrimaryKey val numeroSerie: Long,
    val tipo: String,
    val fecha: Long,
    val precio: Double,
    val combinaciones: List<String>,
    val reintegro: String

)


@Entity(tableName = "premio_table")
data class PremioEntity(
    //@PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey val boletoId: Long,
    var premio: Double?

)

