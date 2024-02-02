package com.example.lotterybalance.di

import android.content.Context
import androidx.room.Room
import com.example.lotterybalance.database.BoletoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val BOLETO_DATABASE_NAME = "boletoDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BoletoDatabase::class.java, BOLETO_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBoletoDao(db: BoletoDatabase) = db.BoletoDao()


}
