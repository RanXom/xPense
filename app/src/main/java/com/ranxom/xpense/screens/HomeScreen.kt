package com.ranxom.xpense.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ranxom.xpense.data.local.TransactionItem
import com.ranxom.xpense.ui.theme.XPenseTheme

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Name Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            ) {
                Column {
                    Text(
                        text = "Good Afternoon",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Shizain",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            // Card Section
            CardSection(
                modifier = Modifier
                    .padding(top = 0.dp)
            )

            // Recent Transactions Section
            RecentTransactionsSection(
                modifier = Modifier
                    .weight(1f) // This is key for proper scrolling
                    .padding(top = 0.dp)
            )
        }
    }
}

@Composable
fun TransactionList() {
    val transactions = listOf(
        TransactionItem(amount = 500.0, dateAndTime = "2025-01-05", isDebit = true),
        TransactionItem(amount = 1500.0, dateAndTime = "2025-01-06", isDebit = false),
        TransactionItem(amount = 300.0, dateAndTime = "2025-01-07", isDebit = true),
        TransactionItem(amount = 1000.0, dateAndTime = "2025-01-08", isDebit = false),
        TransactionItem(amount = 1500.0, dateAndTime = "2025-01-06", isDebit = false),
        TransactionItem(amount = 500.0, dateAndTime = "2025-01-05", isDebit = true),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        transactions.forEach { transaction ->
            TransactionItemUI(transaction = transaction)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun TransactionItemUI(transaction: TransactionItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp)
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side: Amount & Date-Time
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Title: Amount
            Text(
                text = transaction.amount.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            // Subtitle: Date and Time
            Text(
                text = transaction.dateAndTime,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Right side: Arrow (Green or Red)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Green or Red Arrow based on whether it's Debit or Credit
            Icon(
                imageVector = if (transaction.isDebit) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp,
                contentDescription = null,
                tint = if (transaction.isDebit) Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun RecentTransactionsSection(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Recent Transactions",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "See All",
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TransactionList()
    }
}



@Composable
fun CardSection(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface) // Dynamic card background
            .padding(16.dp)
    ) {
        // Total Balance Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondary) // Dynamic surface background
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "T O T A L  B A L A N C E",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.8f) // Dynamic text color
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$10,000",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface // Dynamic text color
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Income & Expense Section
        Row(
            modifier = Modifier
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Income Section
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "T O T A L  I N C O M E",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.Green.copy(0.5f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$15,000",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Expense Section
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "T O T A L  E X P E N S E",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Rounded.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.Red.copy(0.8f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$5,000",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    XPenseTheme(darkTheme = false) {
        HomeScreen()
    }
}