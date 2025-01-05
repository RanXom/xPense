package com.ranxom.xpense.viewmodel

import androidx.lifecycle.ViewModel
import com.ranxom.xpense.data.local.TransactionItem
import androidx.lifecycle.viewModelScope
import com.ranxom.xpense.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionItem>>(emptyList())
    val transactions: StateFlow<List<TransactionItem>> = _transactions.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allTransactions.collect { transactionList ->
                _transactions.value = transactionList
            }
        }
    }

    fun addTransaction(transaction: TransactionItem) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: TransactionItem) {
        viewModelScope.launch {
            repository.deleteTransaction(transaction)
        }
    }

    fun updateTransaction(transaction: TransactionItem) {
        viewModelScope.launch {
            repository.updateTransaction(transaction)
        }
    }
}

