package com.loc.newsapp.presentation.onboardings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.presentation.OnBoardingEvent
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NextTextButton
import com.loc.newsapp.presentation.onboardings.Diamension.IndicatorWidth
import com.loc.newsapp.presentation.onboardings.Diamension.MediumPadding2
import com.loc.newsapp.presentation.onboardings.components.DotsIndicator
import com.loc.newsapp.presentation.onboardings.components.OnBoardingPage
import com.loc.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch
import okhttp3.internal.wait

//i am not passed the viewmodel i pass the event of the viewmodel
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            //in this pages have the list of pages information in the data class
            pages.size
        }

        val buttonState = remember {
            //derived state is used for the two text in the buttons.
            derivedStateOf {
//                switch cases
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "GetStarted")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                //for the bottom padding i.e, when the screen navigation.
                .navigationBarsPadding(),
            //this two properties used for when add the next and back button.
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(
                modifier = Modifier.width(IndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            //for the landscpae and potrait mode.
            val scope = rememberCoroutineScope()

            Row(verticalAlignment = Alignment.CenterVertically) {
                //first time hide this back text button
                if (buttonState.value[0].isNotEmpty()) {
                    NextTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if(pagerState.currentPage == 2){
                                //go to the home screen
                                //when click this button show the true value and store this value in the datastore.
                                event(OnBoardingEvent.SaveAppEntry)
                            }else{
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

//@Preview()
//@Composable
//fun OnBoardingScreenPreview() {
//    NewsAppTheme {
//        OnBoardingScreen()
//    }
//}