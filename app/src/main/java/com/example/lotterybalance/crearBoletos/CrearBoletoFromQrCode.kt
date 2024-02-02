package com.example.lotterybalance.crearBoletos

import com.example.lotterybalance.database.entities.BoletoEntity
import java.text.SimpleDateFormat
import java.util.Locale


fun crearBoletosFromQrCode(info: List<String>): BoletoEntity {

    val rawNumeroSerie = info[0].slice(2..11)
    val numeroSerie = rawNumeroSerie.toLong()
    val rawFecha = info[2].slice(5..11)
    val numSorteosJugados = info[2].last().digitToInt()
    val columnasJugadas = info[4].split(".").drop(1)
    var precio = 0.0
    var tipo = ""
    var reintegro: Int? = 0
    var numeroLoteria = mutableListOf<String>()
    var listaNumerosJugados = mutableListOf<String>()
    val premio = 0.0


    info.forEach { i ->
        if (i.startsWith("R=")) {
            reintegro = i.last().toString().toInt()
        }
        if (i.startsWith("N=")) {
            numeroLoteria = listOf(i).toMutableList()
        }

    }

    columnasJugadas.forEach { combinacion ->
        listaNumerosJugados.add(convertirString(combinacion))
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
            precio = ((columnasJugadas.size * 1.0) * numSorteosJugados)
        }

        "P=2" -> {
            tipo = "Bonoloto"
            precio = ((columnasJugadas.size * 0.5) * numSorteosJugados)
        }

        "P=4" -> {
            tipo = "El Gordo"
            precio = ((columnasJugadas.size * 1.5) * numSorteosJugados)
        }

        "P=7" -> {
            tipo = "Euromillones"
            precio = ((columnasJugadas.size * 2.5) * numSorteosJugados)
            reintegro = null
        }

        "P=14" -> {
            tipo = "Euro Dreams"
            precio = ((columnasJugadas.size * 2.5) * numSorteosJugados)
            reintegro = null
        }

        "P=10" -> {
            tipo = "Loteria Nacional"
            precio = 3.0
            listaNumerosJugados = numeroLoteria
            reintegro = null
        }

    }
    return BoletoEntity(
        numeroSerie = numeroSerie,
        tipo = tipo,
        fecha = fechaMili,
        precio = precio,
        combinaciones = listaNumerosJugados,
        reintegro = reintegro,
        premio = premio
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


