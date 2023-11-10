package com.example.lotterybalance.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(

    private val repo: MainRepo,
    private val dao: BoletoDao
) : ViewModel() {

    private var state by mutableStateOf("")

    fun startScanning() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.startScanning()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    Log.i("tag", "Error: ${error.message}")

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

}