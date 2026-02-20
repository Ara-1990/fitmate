package com.the.fitmate.ui.workout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkOutsScreen(
    onWorkoutClick: (week: Int, workout: Int) -> Unit,
) {
    val weeks = listOf("1 Week", "2 Week", "3 Week", "4 Week")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { weeks.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            weeks.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Button(onClick = {
                    onWorkoutClick(page + 1, 2) },
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("First workout")
                }

                Button(onClick = {onWorkoutClick(page + 1, 3)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)

                ) {
                    Text("Second workout")
                }

                Button(onClick = {onWorkoutClick(page + 1, 4)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)

                ) {
                    Text("Third workout")
                }
            }

        }
    }


}