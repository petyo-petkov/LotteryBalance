package com.example.lotterybalance.viewModels

import android.annotation.SuppressLint
import com.example.lotterybalance.database.entities.BoletoEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun crearBoletoEntity(data: String): BoletoEntity {

    val info = data.split(";")
    return when {
        info.size > 1 -> crearBoletosFromQrCode(info)
        info.size == 1 && info[0].length == 20 -> crearBoletosFromBarCode(info)
        else -> {
            BoletoEntity(
                numeroSerie = 0L,
                tipo = "",
                fecha = 0,
                precio = 0.0,
                combinaciones = listOf(""),
                reintegro = 0,
                premio = 0.0
            )
        }
    }
}

private fun crearBoletosFromQrCode(info: List<String>): BoletoEntity {

    val rawNumeroSerie = info[0].slice(2..11)
    val numeroSerie = rawNumeroSerie.toLong()
    val rawFecha = info[2].slice(5..11)
    val semana = info[2].last().digitToInt()
    val combinaciones = info[4].split(".").drop(1)
    var precio = 0.0
    var tipo = ""
    var reintegro: Int? = 0
    var numeroLoteria = mutableListOf<String>()
    var numerosSeparados = mutableListOf<String>()
    val premio = 0.0


    info.forEach { i ->
        if (i.startsWith("R=")) {
            reintegro = i.last().toString().toInt()
        }
        if (i.startsWith("N=")) {
            numeroLoteria = listOf(i).toMutableList()
        }

    }

    combinaciones.forEach { combinacion ->
        numerosSeparados.add(convertirString(combinacion))
    }

    val meses = mapOf(
        "ENE" to "JAN",
        "FEB" to "FEB",
        "MAR" to "MAR",
        "ABR" to "APR",
        "MAY" to "MAY",
        "JUN" to "JUN",
        "JUL" to "JUL",
        "AGO" to "AUG",
        "SEP" to "SEP",
        "OCT" to "OCT",
        "NOV" to "NOV",
        "DIC" to "DEC"
    )
    val fechaEng = rawFecha.replace(Regex("[A-Z]{3}")) {
        meses[it.value] ?: it.value
    }
    val formatter = SimpleDateFormat("ddMMMyy", Locale.ENGLISH)
    val fecha = formatter.parse(fechaEng)
    val fechaMili = fecha!!.time

    when (info[1]) {
        "P=1" -> {
            tipo = "Primitiva"
            precio = ((combinaciones.size * 1.0) * semana)
        }

        "P=2" -> {
            tipo = "Bonoloto"
            precio = ((combinaciones.size * 0.5) * semana)
        }

        "P=4" -> {
            tipo = "El Gordo"
            precio = ((combinaciones.size * 1.5) * semana)
        }

        "P=7" -> {
            tipo = "Euromillones"
            precio = ((combinaciones.size * 2.5) * semana)
            reintegro = null
        }

        "P=14" -> {
            tipo = "Euro Dreams"
            precio = ((combinaciones.size * 2.5) * semana)
            reintegro = null
        }

        "P=10" -> {
            tipo = "Loteria Nacional"
            precio = 3.0
            numerosSeparados = numeroLoteria
            reintegro = null
        }

    }
    return BoletoEntity(
        numeroSerie = numeroSerie,
        tipo = tipo,
        fecha = fechaMili,
        precio = precio,
        combinaciones = numerosSeparados,
        reintegro = reintegro,
        premio = premio
    )

}

private fun crearBoletosFromBarCode(info: List<String>): BoletoEntity {

    val rawNumero = info[0].slice(0..9)
    val numeroSerie = rawNumero.toLong()
    val numeroLoteria = info[0].slice(11..15)
    val numeroSorteo = info[0].slice(2..3).toInt()
    val precio: Double
    val fechaInicio: LocalDate
    val fechaInicioJUeves = LocalDate.of(2023, 1, 5)
    val fechaInicioSabado = LocalDate.of(2023, 1, 7)

    when {
        numeroSorteo % 2 == 0 -> {
            fechaInicio = fechaInicioSabado
            precio = 6.0
        }

        else -> {
            fechaInicio = fechaInicioJUeves
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

// Convierte "1=1234567890" en "12 34 56 78 90"
private fun convertirString(input: String): String {
    val regex = Regex("(\\d{2})")
    val matches = regex.findAll(input)

    val numeros = mutableListOf<String>()
    for (match in matches) {
        numeros.add(match.value)
    }

    return numeros.joinToString(" ")
}

