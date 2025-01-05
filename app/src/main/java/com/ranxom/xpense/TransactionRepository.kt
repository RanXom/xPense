package com.ranxom.xpense.data.repository

import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.data.local.dao.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions: Flow<List<TransactionItem>> = transactionDao.getAllTransactions()

    suspend fun addTransaction(transaction: TransactionItem) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: TransactionItem) {
        transactionDao.deleteTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionItem) {
        transactionDao.updateTransaction(transaction)
    }

    fun getTransactionsByMonth(yearMonth: String): Flow<List<TransactionItem>> {
        return transactionDao.getTransactionsByMonth(yearMonth)
    }

    fun getTotalIncome(): Flow<Double?> {
        return transactionDao.getTotalIncome()
    }

    fun getTotalExpense(): Flow<Double?> {
        return transactionDao.getTotalExpense()
    }
}