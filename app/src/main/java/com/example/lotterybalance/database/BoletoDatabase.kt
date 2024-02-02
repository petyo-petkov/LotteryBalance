package com.example.lotterybalance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.entities.BoletoEntity

@Database(entities = [BoletoEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class BoletoDatabase: RoomDatabase() {

    abstract fun BoletoDao(): BoletoDao
}