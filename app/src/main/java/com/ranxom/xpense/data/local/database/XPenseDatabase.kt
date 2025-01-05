package com.ranxom.xpense.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.data.local.dao.TransactionDao

@Database(
    entities = [TransactionItem::class],
    version = 2,
    exportSchema = false
)
abstract class XPenseDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}