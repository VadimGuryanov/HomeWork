package kpfu.itis.task2.utils

import java.lang.NumberFormatException
import java.util.*
import java.text.DateFormat
import kotlin.math.roundToLong

class HelperService {

    companion object {

        fun parse(date: Date?) : String {
            if (date == null) {
                return DateFormat.getInstance().format(Calendar.getInstance().time)
            }
            return date.let {
                DateFormat.getInstance().format(it)
            }
        }

        fun parse(date: String) : Date? = DateFormat.getInstance().parse(date)

        fun roundUp(dbl: String?) : String? {
            return dbl?.run {
                when {
                    isEmpty() -> null
                    else -> {
                        try {
                            ((toDouble() * 1_000_000).roundToLong().toDouble() / 1_000_000).toString()
                        } catch (e : NumberFormatException) {
                            null
                        }
                    }
                }
            }
        }

    }

}
