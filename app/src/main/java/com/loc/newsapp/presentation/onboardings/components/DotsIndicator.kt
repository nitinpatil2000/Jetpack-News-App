package com.loc.newsapp.presentation.onboardings.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.presentation.onboardings.Diamension.IndicatorSize
import com.loc.newsapp.ui.theme.BlueGray
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { pageCount->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (pageCount == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}


//@Composable
//fun DotsIndicator(
//    modifier: Modifier = Modifier,
//    pageSize: Int,
//    selectedPage: Int,
//    selectedColor: Color = MaterialTheme.colorScheme.primary,
//    unselectedColor: Color = BlueGray
//) {
//    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
//        repeat(pageSize) { pageCount ->
//            Box(
//                modifier = Modifier
//                    .size(IndicatorSize)
//                    .clip(CircleShape)
//                    .background(color = if (pageCount == selectedPage) selectedColor else unselectedColor)
//            )
//
//        }
//    }
//}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DotsIndicatorPreview() {
    NewsAppTheme {
        DotsIndicator(pageSize = 1, selectedPage = 1)
    }

}