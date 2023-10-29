package com.example.lotterybalance.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoletoViewModel @Inject constructor(

    private val boletoDao: BoletoDao,

    ) : ViewModel() {

    private var getBoletoJob: Job? = null

    private val _boletosListState = mutableStateOf(BoletosListState())
    val boletosListState: State<BoletosListState> = _boletosListState

    private val _boletoState = mutableStateOf(BoletoState())
    val boletoState: State<BoletoState> = _boletoState

    private val _sortidoState = mutableStateOf(SortidoListState())
    val sortidoState: State<SortidoListState> = _sortidoState

    init {
        getAllBoletos()
    }


    suspend fun insertOneBoleto(boleto: BoletoEntity) {
        boletoDao.insertOneBoleto(boleto)
    }

    private fun getAllBoletos() {
        getBoletoJob?.cancel()
        getBoletoJob = boletoDao.getAllBoletos()
            .flowOn(Dispatchers.IO)
            .onEach { boleto ->
                _boletosListState.value = boletosListState.value.copy(
                    estadoBoletos = boleto
                )
            }
            .launchIn(viewModelScope)
    }

    fun getBoletosByDates(startDay: Long, endDay: Long) {
        getBoletoJob?.cancel()
        getBoletoJob = boletoDao.getBoletosByDates(startDay, endDay)
            .flowOn(Dispatchers.IO)
            .onEach { selection ->
                when (selection.isNotEmpty()) {
                    true ->
                        _sortidoState.value = sortidoState.value.copy(
                            sortidoState = selection
                        )
                    false ->
                        _sortidoState.value = sortidoState.value.copy(
                        sortidoState = emptyList()
                    )
                }
            }
            .launchIn(viewModelScope)

    }

    suspend fun loadBoletoByID(id: Long){
        boletoDao.loadBoletoByID(id)?.let { boleto ->
            _boletoState.value = boletoState.value.copy(
                estadoBoleto = boleto
            )
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

    /*
        private fun getAllBoletos() {
                viewModelScope.launch {
                    getBoletoJob?.cancel()
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

        suspend fun loadBoletoByID(id: Long) {
            val boleto = boletoDao.loadBoletoByID(id)
            if (boleto != null) {
                _boletoState.value = boletoState.value.copy(
                    estadoBoleto = boleto
                )
            }
        }

         */
}
