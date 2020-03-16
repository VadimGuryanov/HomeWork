package kpfu.itis.task2.utils

import android.graphics.Color
import kotlin.math.roundToInt

object Helper {

        fun degressWind(deg: Double) : String =
            Const.run {
                when(deg.roundToInt()) {
                    in degree_0..degree_22 -> "East"
                    in degree_337..degree_360 -> "East"
                    in degree_67..degree_112 -> "North"
                    in degree_157..degree_202 -> "West"
                    in degree_247..degree_292 -> "South"
                    in degree_22 + 1 until degree_67 -> "NE"
                    in degree_112 + 1 until degree_157 -> "NW"
                    in degree_202 + 1 until degree_247 -> "SW"
                    in degree_292 + 1 until degree_337 -> "SE"
                    else -> "Calm"
                }
            }

        fun setColor(temp: Int) : Int =
            Const.run {
                when {
                    temp <= -temp_25 -> Color.parseColor("#052555")
                    temp in -temp_24..-temp_15 -> Color.parseColor("#002D6D")
                    temp in -temp_14..-temp_5 -> Color.parseColor("#0043A4")
                    temp in -temp_4..temp_0 -> Color.parseColor("#64C7FF")
                    temp in temp_1..temp_5 -> Color.parseColor("#5BFF62")
                    temp in temp_6..temp_15 -> Color.parseColor("#8EAF0C")
                    temp >= temp_25 -> Color.parseColor("#FFD600")
                    else -> Color.WHITE
                }
            }
}
