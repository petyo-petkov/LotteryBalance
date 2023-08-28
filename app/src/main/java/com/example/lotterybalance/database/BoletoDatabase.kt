package com.example.lotterybalance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity

@Database(entities = [BoletoEntity::class], version = 1)
abstract class BoletoDatabase: RoomDatabase() {

    abstract fun getBoletoDao(): BoletoDao
}