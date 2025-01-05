package com.ranxom.xpense.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.ranxom.xpense.ui.theme.XPenseTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.animateContentSize

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    onFinishOnboarding: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentPage = pagerState.currentPage,
                totalPages = 3,
                onNext = {
                    if (pagerState.currentPage < 2) {
                        // Launch the animation in the coroutine scope
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinishOnboarding()
                    }
                },
                onBack = {
                    if (pagerState.currentPage > 0) {
                        // Launch the animation in the coroutine scope
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            count = 3,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) {
                0 -> WelcomeScreen()
                1 -> AuthenticationSetupScreen()
                2 -> SmsPermissionScreen()
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    currentPage: Int,
    totalPages: Int,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            Box(modifier = Modifier.weight(1f)) {
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier,
                    visible = currentPage != 0,
                    enter = slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300)),
                    exit = slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                ) {
                    Button(onClick = onBack) {
                        Text("Back")
                    }
                }
            }

            // Next/Finish Button aligned to the right when not on first page
            if (currentPage != 0) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier,
                        visible = currentPage != totalPages - 1,
                        enter = fadeIn(animationSpec = tween(300)),
                        exit = fadeOut(animationSpec = tween(300))
                    ) {
                        Button(onClick = onNext) {
                            Text("Next")
                        }
                    }

                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier,
                        visible = currentPage == totalPages - 1,
                        enter = slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(300)),
                        exit = slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(300))
                    ) {
                        Button(
                            onClick = onNext,
                            // Removed the enabled property since we want it always enabled on last page
                            modifier = Modifier.animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        )  {
                            Text("Finish")
                        }
                    }
                }
            }
        }

        // Get Started button centered on first page
        androidx.compose.animation.AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = currentPage == 0,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Button(
                onClick = onNext,
                modifier = Modifier.animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Text("Get Started")
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to XPense", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Let’s get started with setting up the app")
    }
}

@Composable
fun AuthenticationSetupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Authentication Setup", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Choose your authentication method: Fingerprint or PIN.", textAlign = TextAlign.Center)
    }
}

@Composable
fun SmsPermissionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SMS Permission", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Allow access to SMS for automatic expense tracking.", textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    XPenseTheme(darkTheme = false) {
        OnBoardingScreen(onFinishOnboarding = {})
    }
}
