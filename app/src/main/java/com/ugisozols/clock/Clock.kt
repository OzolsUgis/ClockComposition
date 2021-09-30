package com.ugisozols.clock

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@SuppressLint("SimpleDateFormat")
@Composable
fun Clock(
    modifier: Modifier = Modifier,
    clockStyle: ClockStyle = ClockStyle(),
    hours : Float,
    minutes : Float,
    seconds : Float
) {
    val radius = clockStyle.radius
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    val circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    val angle by remember {
        mutableStateOf(0f)
    }




    Canvas(
        modifier = modifier

    ){


        center = this.center
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                }
            )
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx() + 1f,
                Paint().apply {
                    color = Color.RED
                    style = Paint.Style.STROKE
                    strokeWidth = 1.dp.toPx()
                }
            )
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx() + 2f,
                Paint().apply {
                    color = Color.LTGRAY
                    style = Paint.Style.STROKE
                    strokeWidth = 1.dp.toPx()
                }
            )
            val indicatorStartPoint = Offset(x = center.x, y = center.y)




            rotate(degrees = (seconds * 6f) - 180f){
                drawLine(
                    color = clockStyle.secondIndicatorColor,
                    start = indicatorStartPoint,
                    end = Offset(center.x, clockStyle.secondIndicatorLength.toPx()),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(degrees = minutes * 6f - 180f){
                drawLine(
                    color = clockStyle.minuteIndicatorColor,
                    start = indicatorStartPoint,
                    end = Offset(center.x, clockStyle.minuteIndicatorLength.toPx()),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(degrees = hours * 30f - 180f){
                drawLine(
                    color = clockStyle.hourIndicatorColor,
                    start = indicatorStartPoint,
                    end = Offset(center.x, clockStyle.hourIndicatorLength.toPx()),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }


        }


        // Draw Lines
        for ( i in 0..360){
            val angleInRad = (i + angle - 90) * ((PI / 180f).toFloat())
            val lineTypes = when {
                i % 30 == 0 -> TimeIndicatorTypes.HourLine
                i % 6 == 0 -> TimeIndicatorTypes.MinuteLine
                else -> TimeIndicatorTypes.SecondLine
            }
            val lineLength = when(lineTypes){
                TimeIndicatorTypes.HourLine -> clockStyle.hourLength.toPx()
                TimeIndicatorTypes.MinuteLine -> clockStyle.oneMinuteLength.toPx()
                TimeIndicatorTypes.SecondLine -> clockStyle.secondLength.toPx()
            }
            val lineColor = when(lineTypes){
                TimeIndicatorTypes.HourLine -> clockStyle.oneHourColor
                TimeIndicatorTypes.MinuteLine -> clockStyle.oneMinuteColor
                TimeIndicatorTypes.SecondLine -> clockStyle.secondColor
            }
            val lineStartsAt = Offset(
                x = (radius.toPx() - lineLength) * cos(angleInRad),
                y = (radius.toPx()- lineLength) * sin(angleInRad)
            )
            val lineEndsAt = Offset(
                x = radius.toPx() * cos(angleInRad),
                y = radius.toPx() * sin(angleInRad)
            )
            drawLine(
                color = lineColor,
                start = lineStartsAt,
                end = lineEndsAt,
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}