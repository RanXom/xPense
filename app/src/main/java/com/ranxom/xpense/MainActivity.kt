package com.ranxom.xpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ranxom.xpense.data.local.OnBoardingManager
import com.ranxom.xpense.screens.AddTransactionScreen
import com.ranxom.xpense.screens.AllTransactions
import com.ranxom.xpense.screens.HomeScreen
import com.ranxom.xpense.ui.theme.XPenseTheme
import com.ranxom.xpense.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.ranxom.xpense.screens.OnBoardingScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Create ViewModel instance
            val viewModel: TransactionViewModel = viewModel()

            // Create an OnboardingManager instance to check if onboarding is completed
            val onboardingManager = OnBoardingManager(this)
            val onboardingCompletedState = remember {
                mutableStateOf(onboardingManager.isOnboardingCompleted())
            }

            XPenseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Observe onboarding completion state
                    if (onboardingCompletedState.value) {
                        // After onboarding is complete, navigate to the main app
                        AppNavigator(viewModel = viewModel)
                    } else {
                        // Show OnboardingScreen until it's completed
                        OnBoardingScreen(onFinishOnboarding = {
                            // Mark onboarding as completed in SharedPreferences
                            onboardingManager.setOnboardingCompleted(true)

                            // Update the state to recompose the UI
                            onboardingCompletedState.value = true
                        })
                    }
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
                onSeeAllClicked = { navController.navigate("all_transactions") },
                onAddTransactionClicked = { navController.navigate("add_transaction") }
            )
        }
        // Screen Routes
        composable("all_transactions") {
            AllTransactions(
                viewModel = viewModel,
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable("add_transaction") {
            AddTransactionScreen(
                onAddTransaction = { transactionItem ->
                    viewModel.addTransaction(transactionItem)
                },
                onDismiss = { navController.popBackStack() },
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}

