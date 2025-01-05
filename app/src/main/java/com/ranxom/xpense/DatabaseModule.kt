package com.ranxom.xpense.di

import android.content.Context
import androidx.room.Room
import com.ranxom.xpense.data.local.database.XPenseDatabase
import com.ranxom.xpense.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): XPenseDatabase = Room.databaseBuilder(
        context,
        XPenseDatabase::class.java,
        "xpense_db"
    ).build()

    @Provides
    @Singleton
    fun provideTransactionDao(database: XPenseDatabase): TransactionDao {
        return database.transactionDao()
    }
}