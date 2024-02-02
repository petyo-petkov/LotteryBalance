package com.example.lotterybalance.crearBoletos

import android.annotation.SuppressLint
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoState

@SuppressLint("SimpleDateFormat")
fun crearBoletoEntity(data: String): BoletoEntity {

    val info = data.split(";")
    return when {
        info.size > 1 -> crearBoletosFromQrCode(info)
        info.size == 1 && info[0].length == 20 -> crearBoletosFromBarCode(info)
        else -> { BoletoState().estadoBoleto }
    }
}
