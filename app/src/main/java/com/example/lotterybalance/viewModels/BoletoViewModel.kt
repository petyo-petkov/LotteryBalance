package com.example.lotterybalance.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoletoViewModel @Inject constructor(

    private val boletoDao: BoletoDao,

    ) : ViewModel() {

    private val _boletoListState = mutableStateOf(BoletosListState())
    val boletoListState: State<BoletosListState> = _boletoListState

    private val _boletoState = mutableStateOf(BoletoState())
    val boletoState: State<BoletoState> = _boletoState


    private val _sortidoState = mutableStateOf(SortidoListState())
    val sortidoState: State<SortidoListState> = _sortidoState


    fun getAllBoletos() {
        viewModelScope.launch {
            boletoDao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.i("error", error.message.toString())
                }
                .collect { result ->
                    if (result.isNotEmpty()) {
                        _boletoListState.value = boletoListState.value.copy(
                            boletosState = result
                        )

                    }
                }

        }
    }

    fun sortBoletosByDate(startDay: Long, endDay: Long) {
        viewModelScope.launch {
            boletoDao.getSelectedDates(startDay, endDay)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.i("error", error.message.toString())
                }
                .collect { result ->
                    if (result.isNotEmpty()) {
                        _sortidoState.value = sortidoState.value.copy(
                            sortidoState = result
                        )
                    } else {
                        boletoDao.getSelectedDates(startDay = 0, endDay = 0)
                        _sortidoState.value = sortidoState.value.copy(
                            sortidoState = result
                        )
                    }
                }
        }
    }

    fun loadBoletoByID(id: Long) {
        viewModelScope.launch {
            boletoDao.loadBoletoByID(id)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.i("error", error.message.toString())
                }
                .collect { result ->
                    if (result != null) {
                        _boletoState.value = boletoState.value.copy(
                            boletoState = result
                        )
                    }

                }
        }
    }

    fun deleteAllBoletos(boletos: List<BoletoEntity>) {
        viewModelScope.launch {
            boletoDao.deleteAllBoletos(boletos)
            val currentBoletos = _boletoListState.value.boletosState
            val newBoletos = currentBoletos - boletos
            _boletoListState.value = boletoListState.value.copy(boletosState = newBoletos)
        }
    }

    fun deleteOneBoleto(boleto: BoletoEntity) {
        viewModelScope.launch {
            boletoDao.deleteBoleto(boleto)
            val currentBoletos = _boletoListState.value.boletosState
            if (currentBoletos.contains(boleto)) {
                val newBoletos = currentBoletos - boleto
                _boletoListState.value = boletoListState.value.copy(boletosState = newBoletos)
            }
        }
    }



    /*
        fun deleteAllBoletos(boletos: List<BoletoEntity>) {
            viewModelScope.launch {
                boletoDao.deleteAllBoletos(boletos)
                boletoDao.getAllBoletos()
                    .flowOn(Dispatchers.IO)
                    .collect { result ->
                        if (result.isEmpty()) {
                            _boletosState.value = boletosState.value.copy(
                                boletosState = result
                            )
                        }
                    }

            }

        }

        fun deleteOneBoleto(boleto: BoletoEntity) {
            viewModelScope.launch {
                boletoDao.deleteBoleto(boleto)
                boletoDao.getAllBoletos()
                    .flowOn(Dispatchers.IO)
                    .collect { result ->
                        if (result.isEmpty()) {
                            _boletosState.value = boletosState.value.copy(
                                boletosState = result
                            )

                        }
                    }

            }
        }

     */

    suspend fun updatePremio(boleto: BoletoEntity) {
        viewModelScope.launch {
            boletoDao.updatePremio(boleto)

        }
    }


}
