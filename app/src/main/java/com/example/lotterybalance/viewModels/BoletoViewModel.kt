package com.example.lotterybalance.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoletoViewModel @Inject constructor(

    private val boletoDao: BoletoDao,

    ) : ViewModel() {

    var boletos by mutableStateOf<List<BoletoEntity>>(listOf())
        private set
    var boleto by mutableStateOf(
        BoletoEntity(
            numeroSerie = 0,
            tipo = "",
            precio = 0.0,
            fecha = 0,
            combinaciones = listOf(),
            reintegro = "",
            premio = 0.0
        )
    )
        private set


    var sortidoBoletos by mutableStateOf<List<BoletoEntity>>(listOf())
        private set


    fun getAllBoletos() {
        viewModelScope.launch {
            boletoDao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isNotEmpty()) {
                        boletos = result
                    }
                }
        }
    }

    fun sortBoletosByDate(startDay: Long, endDay: Long) {
        viewModelScope.launch {
            boletoDao.getSelectedDates(startDay, endDay)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isNotEmpty()) {
                        sortidoBoletos = result
                    }
                }
        }
    }

    fun loadBoletoByID(id: Long) {
        viewModelScope.launch {
            boletoDao.loadBoletoByID(id)
                .flowOn(Dispatchers.IO)
                .collect{ result ->
                    boleto = result
                }
        }
    }

    fun deleteAllBoletos() {
        viewModelScope.launch {
            boletoDao.deleteAllBoletos(boletos)
            boletoDao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        boletos = result
                    }
                }

        }

    }

    fun deleteOneBoleto() {
        viewModelScope.launch {
            boletoDao.deleteBoleto(boleto)
            boletoDao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        boletos = result
                    }
                }

        }

    }
    fun updatePremio(boleto: BoletoEntity){
        viewModelScope.launch{
            boletoDao.updatePremio(boleto)
        }
    }


}
