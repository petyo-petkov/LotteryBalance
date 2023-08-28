package com.example.lotterybalance.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boletos_table")
data class BoletoEntity(
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "id") val id: Int = 0,
    @PrimaryKey
    @ColumnInfo(name = "numero_serie") val numero_serie: String,
    @ColumnInfo(name = "tipo") val tipo: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "precio") val precio: Double
)