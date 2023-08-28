package com.example.lotterybalance.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(

    private val repo: MainRepo,
    private val dao: BoletoDao,
    private val context: Context,

    ) : ViewModel() {

    val scannState = MutableLiveData<String>()


    fun startScanning() {
        viewModelScope.launch {
            repo.startScanning()
                .onEach { data ->
                    if (!data.isNullOrBlank()) {
                        dao.insertOne(crearBoletoEntity(data))
                    }
                }

                .catch { error ->
                    Log.i("tag", "Error: ${error.message}")
                    Toast.makeText(context, "codigo no compatible o ya existe", Toast.LENGTH_LONG)
                        .show()
                }
                .collect { data ->
                    if (!data.isNullOrBlank()) {
                        scannState.postValue(data)
                        Log.i("rawData", data)
                    }
                }
        }
    }
    private fun crearBoletoEntity(data: String): BoletoEntity {

        val info = data.split(";")
        val numero_serie = info[0]
        val fecha = info[2].slice(5..11)
        val semana = info[2].last().digitToInt()
        val combinaciones = info[4].split(".").drop(1)
        var precio = 0.0
        var tipo = ""

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
            }

        }

        return BoletoEntity(numero_serie = numero_serie, tipo = tipo, fecha = fecha, precio = precio)
    }
}