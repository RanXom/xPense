package com.ranxom.xpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ranxom.xpense.screens.AllTransactions
import com.ranxom.xpense.screens.HomeScreen
import com.ranxom.xpense.ui.theme.XPenseTheme
import com.ranxom.xpense.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Create ViewModel instance
            val viewModel: TransactionViewModel = viewModel()

            XPenseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(viewModel = viewModel)
                }
            }
        }
    }
}

// Navigation Component
@Composable
fun AppNavigator(viewModel: TransactionViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Home Screen Route
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onSeeAllClicked = { navController.navigate("all_transactions") }
            )
        }
        // All Transactions Screen Route
        composable("all_transactions") {
            AllTransactions(
                viewModel = viewModel,
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}
