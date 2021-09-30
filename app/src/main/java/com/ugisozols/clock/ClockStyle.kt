package com.ugisozols.clock

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val radius : Dp = 100.dp,
    val oneMinuteLength : Dp = 10.dp,
    val hourLength : Dp = 20.dp,
    val secondLength : Dp = 0.dp,
    val secondColor : Color = Color.Transparent,
    val oneMinuteColor : Color = Color.Gray,
    val oneHourColor : Color = Color.Black,
    val secondIndicatorLength : Dp = 70.dp,
    val minuteIndicatorLength : Dp = 70.dp,
    val hourIndicatorLength : Dp = 50.dp,
    val secondIndicatorColor : Color = Color.Red,
    val minuteIndicatorColor : Color = Color.Black,
    val hourIndicatorColor : Color = Color.Black

)
