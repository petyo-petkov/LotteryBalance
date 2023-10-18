package com.example.lotterybalance.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(

    private val repo: MainRepo,
    private val dao: BoletoDao,
    private val context: Context,

    ) : ViewModel() {

        private var state by mutableStateOf("")

    fun startScanning() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.startScanning()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.i("tag", "Error: ${error.message}")
                    Toast.makeText(context, "codigo no compatible o ya existe", Toast.LENGTH_LONG)
                        .show()

                }
                .flowOn(Dispatchers.IO)
                .collect { data ->
                    if (!data.isNullOrBlank()) {
                        state = data
                        dao.insertOneBoleto(crearBoletoEntity(data))

                        Log.i("rawData", data)

                    }
                }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun crearBoletoEntity(data: String): BoletoEntity {

        val info = data.split(";")
        val rawNumeroSerie = info[0].slice(2..11)
        val numeroSerie = rawNumeroSerie.toLong()
        val rawFecha = info[2].slice(5..11)
        val semana = info[2].last().digitToInt()
        val combinaciones = info[4].split(".").drop(1)
        var precio = 0.0
        var tipo = ""
        var numeroLoteria = mutableListOf<String>()
        var reintegro: Int? = 0
        var numerosSeparados = mutableListOf<String>()
        val premio = 0.0

        info.forEach { i ->
            if(i.startsWith("R=")){
                reintegro = i.last().toString().toInt()
            }
            if (i.startsWith("N=")){
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
            }
            "P=10" -> {
                tipo = "Loteria Nacional"
                precio = 3.0
                numerosSeparados = numeroLoteria
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
}