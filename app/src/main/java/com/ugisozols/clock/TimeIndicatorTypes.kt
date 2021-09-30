package com.ugisozols.clock

sealed class TimeIndicatorTypes {
    object MinuteLine : TimeIndicatorTypes()
    object HourLine : TimeIndicatorTypes()
    object SecondLine : TimeIndicatorTypes()
}
