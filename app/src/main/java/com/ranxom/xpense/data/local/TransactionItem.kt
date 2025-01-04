package com.ranxom.xpense.data.local

data class TransactionItem(
    val amount: Double = 0.0,
    val dateAndTime: String,
    val isDebit: Boolean = true
)
