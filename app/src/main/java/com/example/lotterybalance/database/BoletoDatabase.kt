package com.example.lotterybalance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.dao.PremioDao
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.database.entities.PremioEntity

@Database(entities = [BoletoEntity::class, PremioEntity::class], version = 27)
@TypeConverters(Converters::class)
abstract class BoletoDatabase: RoomDatabase() {

    abstract fun BoletoDao(): BoletoDao

    abstract fun PremioDao(): PremioDao
}