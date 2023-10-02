package com.example.lotterybalance.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.lotterybalance.database.entities.BoletoConPremio
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.database.entities.PremioEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface BoletoDao {

    @Query("SELECT * FROM boletos_table WHERE fecha BETWEEN :startDay AND :endDay")
    fun getSelectedDates(startDay: Date, endDay: Date): Flow<List<BoletoEntity>>

    @Query("SELECT * FROM boletos_table")
    fun getAllBoletos(): Flow<List<BoletoEntity>>

    @Query("SELECT * FROM boletos_table")
    fun loadOneBoleto(): Flow<BoletoEntity>

    @Query("SELECT * FROM boletos_table WHERE numero_serie=:id")
    suspend fun loadBoletoByID(id: Long): BoletoEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneBoleto(boleto: BoletoEntity)


    @Delete
    suspend fun deleteBoleto(boleto: BoletoEntity)

    @Delete
    suspend fun deleteAllBoletos(boletos: List<BoletoEntity>)

    @Transaction
    @Query("SELECT * FROM boletos_table")
    fun getAllBoletosConPremio(): Flow<List<BoletoConPremio>>

}

@Dao
interface PremioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPremio(premio: PremioEntity)

    @Update
    suspend fun updatePremio(premio: PremioEntity)

    @Query("SELECT * FROM premio_table")
    fun getPremios(): Flow<List<PremioEntity>>

    @Query("SELECT * FROM premio_table")
    fun getPremio(): Flow<PremioEntity>

    @Query("SELECT * FROM premio_table WHERE boletoId=:id")
    suspend fun loadPremioByID(id: Long): PremioEntity

    @Delete
    suspend fun deletePremios(premios: List<PremioEntity>)

    @Delete
    suspend fun deletePremio(premio: PremioEntity)
}

