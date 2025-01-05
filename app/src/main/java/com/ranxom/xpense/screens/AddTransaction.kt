package com.ranxom.xpense.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.data.local.TransactionSource

@Composable
fun AddTransactionScreen(
    onAddTransaction: (TransactionItem) -> Unit,
    onDismiss: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var isDebit by remember { mutableStateOf(true) }
    var amountError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = {
                amount = it
                amountError = it.toDoubleOrNull() == null
            },
            label = { Text("Amount") },
            isError = amountError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        if (amountError) {
            Text(
                text = "Please enter a valid amount",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isDebit = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDebit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Text("Expense")
            }

            Button(
                onClick = { isDebit = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isDebit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Text("Income")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (amount.isEmpty() || amount.toDoubleOrNull() == null) {
                    // Show error message or disable the button until valid input
                    return@Button
                }
                onAddTransaction(
                    TransactionItem(
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        isDebit = isDebit,
                        category = category,
                        description = description,
                        timestamp = System.currentTimeMillis(),
                        source = TransactionSource.MANUAL
                    )
                )
                onDismiss()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Transaction")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTransactionPreview(){
    AddTransactionScreen(onAddTransaction = {}, onDismiss = {})
}