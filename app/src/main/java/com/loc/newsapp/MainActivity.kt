package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.loc.newsapp.domain.usecases.UserAppEntryUseCases
import com.loc.newsapp.presentation.onboardings.OnBoardingScreen
import com.loc.newsapp.presentation.onboardings.OnBoardingViewModel
import com.loc.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    //    i want to use this file so we need to inject this using inject annotation
    @Inject
    lateinit var userAppEntryUseCases: UserAppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        //we need to check whether dagger is correct or not so collect return the flow boolean value.
        lifecycleScope.launch {
            userAppEntryUseCases.readAppEntry().collect {
                Log.d("TAG", it.toString())
            }
        }
        setContent {
            NewsAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    //todo same things.
                    OnBoardingScreen(
                        // OnBoardingScreen(viewModel::onEvent)
                        event = {
                            viewModel.onEvent(it)
                        })
                }
            }
        }
    }
}
