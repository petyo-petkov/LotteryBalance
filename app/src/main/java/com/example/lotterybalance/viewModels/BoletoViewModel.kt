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

    private val _boletosListState = mutableStateOf(BoletosListState())
    val boletosListState: State<BoletosListState> = _boletosListState

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
                        _boletosListState.value = boletosListState.value.copy(
                            estadoBoletos = result
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
                    result?.let {
                        _boletoState.value = boletoState.value.copy(
                            estadoBoleto = result
                        )
                    }

                }
        }
    }

    fun deleteAllBoletos(boletos: List<BoletoEntity>) {
        viewModelScope.launch {
            boletoDao.deleteAllBoletos(boletos)
            _boletosListState.value = boletosListState.value.copy(
                estadoBoletos = boletosListState.value.estadoBoletos - boletos.toSet()
            )
        }
    }

    fun deleteOneBoleto(boleto: BoletoEntity) {
        viewModelScope.launch {
            boletoDao.deleteBoleto(boleto)
            _boletosListState.value = boletosListState.value.copy(
                estadoBoletos = boletosListState.value.estadoBoletos - boleto
            )
        }
    }

    suspend fun updatePremio(boleto: BoletoEntity) {
        viewModelScope.launch {
            boletoDao.updatePremio(boleto)

        }
    }


}
