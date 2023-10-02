package com.example.lotterybalance.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.dao.PremioDao
import com.example.lotterybalance.database.entities.BoletoConPremio
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.database.entities.PremioEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class BoletoViewModel @Inject constructor(

    private val boletoDao: BoletoDao,
    private val premioDao: PremioDao,

    ) : ViewModel() {

    //VARIABLES BOLETO
    var boletos by mutableStateOf<List<BoletoEntity>>(listOf())
        private set
    var boleto by mutableStateOf(
        BoletoEntity(
            numero_serie = 0,
            tipo = "",
            precio = 0.0,
            fecha = Date(),
            combinaciones = listOf(),
            reintegro = ""
        )
    )
        private set

    var boletosPremio by mutableStateOf<List<BoletoConPremio>>(listOf())
        private set

    var sortidoBoletos by mutableStateOf<List<BoletoEntity>>(listOf())
        private set

    // VARIABLES PREMIO
    var premios by mutableStateOf<List<PremioEntity>>(listOf())
        private set

    var premio by mutableStateOf(PremioEntity(premio = 0.0, boletoId = 0))
        private set


    // FUNC BOLETO
    fun getBoletoEntity() {
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


    fun sortBoletosByDate(startDay: Date, endDay: Date) {
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



    fun getOneBoleto() {
        viewModelScope.launch {
            boletoDao.loadOneBoleto()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    boleto = result
                }
        }
    }

    suspend fun loadBoletoByID(id: Long) {
        return withContext(Dispatchers.IO) {
            boleto = boletoDao.loadBoletoByID(id)
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

    fun deleteOneBoleto(boleto: BoletoEntity) {
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

    fun getAllboletosConPremios() {
        viewModelScope.launch {
            boletoDao.getAllBoletosConPremio()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isNotEmpty()) {
                        boletosPremio = result
                    }
                }
        }
    }


    // FUNC PREMIO
    suspend fun insertPremio(data: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            makePremioEntity(data)?.let { premioDao.insertPremio(it) }

        }

    }

    suspend fun loadPremioById(id: Long) {
        return withContext(Dispatchers.IO) {
            premio = premioDao.loadPremioByID(id)
        }
    }

    suspend fun updatePremio(premio: PremioEntity) {
        viewModelScope.launch {
            premioDao.updatePremio(premio)
        }
    }

    fun getPremios() {
        viewModelScope.launch {
            premioDao.getPremios()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isNotEmpty()) {
                        premios = result
                    }
                }
        }
    }

    fun getPremio() {
        viewModelScope.launch {
            premioDao.getPremio()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    premio = result
                }
        }
    }

    fun deletePremios() {
        viewModelScope.launch {
            premioDao.deletePremios(premios)
            premioDao.getPremios()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        premios = result
                    }

                }

        }
    }

    fun deletePremio(premio: PremioEntity) {
        viewModelScope.launch {
            premioDao.deletePremio(premio)
            premioDao.getPremios()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        premios = result
                    }
                }
        }
    }

    private fun makePremioEntity(data: Double?): PremioEntity? {
        return data?.let { PremioEntity(premio = it, boletoId = boleto.numero_serie) }
    }

}
