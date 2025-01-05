package com.ranxom.xpense.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val dateAndTime: String,
    val isDebit: Boolean,
    val description: String? = null,
    val category: String? = null,
    val messageId: String? = null // For linking with SMS messages
)