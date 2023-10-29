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

    @Query("SELECT * FROM boletoDB WHERE fecha BETWEEN :startDay AND :endDay")
    fun getBoletosByDates(startDay: Long, endDay: Long): Flow<List<BoletoEntity>>

    @Query("SELECT * FROM boletoDB")
    fun getAllBoletos(): Flow<List<BoletoEntity>>

    @Query("SELECT * FROM boletoDB WHERE numeroSerie=:id")
    suspend fun loadBoletoByID(id: Long): BoletoEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneBoleto(boleto: BoletoEntity)


    @Delete
    suspend fun deleteBoleto(boleto: BoletoEntity)

    @Delete
    suspend fun deleteAllBoletos(boletos: List<BoletoEntity>)

}
