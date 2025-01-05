package com.ranxom.xpense.data.local.dao

import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.data.local.TransactionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeTransactionDao : TransactionDao {
    private val transactions = MutableStateFlow(
        listOf(TransactionItem(
                id = 1,
                amount = 100.0,
                description = "Groceries",
                timestamp = System.currentTimeMillis(),
                isDebit = true,
                source = TransactionSource.MANUAL
            ), TransactionItem(
                id = 2,
                amount = 50.0,
                description = "Coffee",
                timestamp = System.currentTimeMillis() - 10000,
                isDebit = true,
                source = TransactionSource.MANUAL
            ), TransactionItem(
                id = 3,
                amount = 200.0,
                description = "Online shopping",
                timestamp = System.currentTimeMillis() - 20000,
                isDebit = false,
                source = TransactionSource.MANUAL
            ))
    )

    override fun getAllTransactions(): Flow<List<TransactionItem>> = transactions

    override suspend fun insertTransaction(transaction: TransactionItem) {
        // Do nothing for now
    }

    override fun getTransactionsByMonth(yearMonth: String): Flow<List<TransactionItem>> {
        // Return all transactions for now (modify if needed)
        return transactions
    }

    override fun getTotalIncome(): Flow<Double?> {
        // Calculate total income (sum of amounts for non-debit transactions, assuming income transactions have isDebit = 0)
        val totalIncome = transactions.value.filter { !it.isDebit }.sumOf { it.amount }
        return flowOf(totalIncome) // Wrap it in a Flow
    }

    override fun getTotalExpense(): Flow<Double?> {
        // Calculate total expense (sum of amounts for debit transactions, assuming expense transactions have isDebit = 1)
        val totalExpense = transactions.value.filter { it.isDebit }.sumOf { it.amount }
        return flowOf(totalExpense) // Wrap it in a Flow
    }

    override suspend fun insertTransactions(transactions: List<TransactionItem>) {
        // Do nothing for now
    }

    override suspend fun updateTransaction(transaction: TransactionItem) {
        // Do nothing for now
    }

    override suspend fun deleteTransaction(transaction: TransactionItem) {
        // Do nothing for now
    }
}
