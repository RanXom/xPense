package com.ranxom.xpense.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.data.local.TransactionSource

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableLiveData<List<TransactionItem>>()
    val transactions: LiveData<List<TransactionItem>> = _transactions

    init {
        // Initialize with some dummy data
        _transactions.value = List(20) { index ->
            TransactionItem(
                id = index,
                amount = (index + 1) * 100.0,
                description = "Transaction $index",
                isDebit = index % 2 == 0,
                timestamp = System.currentTimeMillis() - index * 100000L,
                source = TransactionSource.MANUAL
            )
        }
    }

    fun addTransaction(transaction: TransactionItem) {
        _transactions.value = (_transactions.value ?: emptyList()) + transaction
    }
}
