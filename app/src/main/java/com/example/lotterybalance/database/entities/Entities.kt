package com.example.lotterybalance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boletoDB")
data class BoletoEntity(
    @PrimaryKey val numeroSerie: Long,
    val tipo: String,
    val fecha: Long,
    val precio: Double,
    val combinaciones: List<String>,
    var reintegro: Int? ,
    var premio: Double?

)