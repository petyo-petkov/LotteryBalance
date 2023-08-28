package com.example.lotterybalance.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lotterybalance.database.entities.BoletoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoletoDao {

    @Query("SELECT * FROM boletos_table")
    fun getAllBoletos(): Flow<List<BoletoEntity>>

    @Query("SELECT * FROM boletos_table")
    fun loadOne(): Flow<BoletoEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(boleto: BoletoEntity)

    @Query("DELETE FROM boletos_table")
    suspend fun deleteAllBoletos()

    @Delete
    suspend fun delete(boleto: BoletoEntity)

}