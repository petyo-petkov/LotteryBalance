package com.example.lotterybalance.crearBoletos

import com.example.lotterybalance.database.entities.BoletoEntity
import java.time.LocalDate

fun crearBoletosFromBarCode(info: List<String>): BoletoEntity {

    val rawNumero = info[0].slice(0..9)
    val numeroSerie = rawNumero.toLong()
    val numeroLoteria = info[0].slice(11..15)
    val numeroSorteo = info[0].slice(2..3).toInt()
    val precio: Double
    val fechaInicio: LocalDate
    val fechaInicioJueves = LocalDate.of(2024, 1, 4)
    val fechaInicioSabado = LocalDate.of(2024, 1, 6)

    when {
        numeroSorteo % 2 == 0 -> {
            fechaInicio = fechaInicioSabado
            precio = 6.0
        }

        else -> {
            fechaInicio = fechaInicioJueves
            precio = 3.0
        }
    }

    val diasTranscurridos = ((((numeroSorteo - 1) / 2)) * 7)
    val fechaSorteo = fechaInicio.minusDays(0).plusDays(diasTranscurridos.toLong())
    val fecha = fechaSorteo.toEpochDay() * 24 * 60 * 60 * 1000


    return BoletoEntity(
        numeroSerie = numeroSerie,
        tipo = "Loteria Nacional",
        fecha = fecha,
        precio = precio,
        combinaciones = listOf(numeroLoteria),
        reintegro = null,
        premio = 0.0
    )
}