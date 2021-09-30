package com.ugisozols.clock

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ugisozols.clock.ui.theme.ClockTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ClockTheme {
                Box(modifier = Modifier.fillMaxSize()){


                    val date = remember {
                        System.currentTimeMillis()
                    }

                    var hours by remember {
                        mutableStateOf((((date /1000)/ 3600 + 3) % 12).toFloat())
                    }

                    var minutes by remember {
                        mutableStateOf((((date /1000 ) / 60) % 60).toFloat())
                    }
                    var seconds by remember {
                        mutableStateOf((date / 1000) % 60.toFloat())
                    }


                    LaunchedEffect(key1 = seconds){
                        delay(1000L)
                        seconds += 1f
                        minutes += 1f / 60f
                        hours += 1f / (60f * 24)



                    }

                    Clock(modifier = Modifier
                        .align(Alignment.Center),
                        hours = hours,
                        minutes = minutes,
                        seconds = seconds
                    )
                }

            }
        }
    }
}
