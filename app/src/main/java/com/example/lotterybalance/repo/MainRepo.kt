package com.example.lotterybalance.repo

import android.content.Context
import android.widget.Toast
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainRepo @Inject constructor(

    private val scanner: GmsBarcodeScanner,
    private val context: Context,

    ) {

    fun startScanning(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()

                .addOnSuccessListener { barcode ->
                    launch {
                        send(barcode.rawValue)
                    }
                }
                .addOnCanceledListener {
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "codigo no compatible o ya existe", Toast.LENGTH_LONG)
                        .show()
                }
            awaitClose { }
        }
    }
}
