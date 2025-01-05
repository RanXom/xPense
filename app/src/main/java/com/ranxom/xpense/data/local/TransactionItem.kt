package com.ranxom.xpense.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val timestamp: Long,
    val isDebit: Boolean,
    val description: String? = null,
    val category: String? = null,
    val source: TransactionSource // For linking with SMS messages
)
enum class TransactionSource {
    SMS, MANUAL
}