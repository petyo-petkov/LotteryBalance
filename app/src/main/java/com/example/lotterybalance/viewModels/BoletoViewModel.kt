package com.example.lotterybalance.viewModels

import androidx.lifecycle.MutableLiveData
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

    private val dao: BoletoDao,

    ): ViewModel() {

    val listaBoletosState = MutableLiveData<List<BoletoEntity>>()

    fun getBoletoEntity() {
        viewModelScope.launch {
            dao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isNotEmpty()) {
                        listaBoletosState.postValue(result)
                    }
                }

        }

    }

    fun deleteAll() {
        viewModelScope.launch {
            dao.deleteAllBoletos()
            dao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        listaBoletosState.postValue(result)
                    }
                }
        }

    }

    fun deleteOne(boleto: BoletoEntity) {
        viewModelScope.launch {
            dao.delete(boleto)
            dao.getAllBoletos()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    if (result.isEmpty()) {
                        listaBoletosState.postValue(result)
                    }
                }
        }

    }

}