package kpfu.itis.task2.utils

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.view.View
import kotlinx.android.synthetic.main.fragment_redaction.view.*
import java.util.*

class DataPicker(var view: View) {

    private var dateAndTime = Calendar.getInstance()

    fun setDate() {
        DatePickerDialog(
            view.context, d,
            dateAndTime[Calendar.YEAR],
            dateAndTime[Calendar.MONTH],
            dateAndTime[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private var d = OnDateSetListener { _ , year, monthOfYear, dayOfMonth ->
        dateAndTime[Calendar.YEAR] = year
        dateAndTime[Calendar.MONTH] = monthOfYear
        dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
        setTime()
    }

    private fun setTime() {
        TimePickerDialog(
            view.context, t,
            dateAndTime[Calendar.HOUR_OF_DAY],
            dateAndTime[Calendar.MINUTE], true
        ).show()
    }

    private var t = OnTimeSetListener { _ , hourOfDay, minute ->
        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
        dateAndTime[Calendar.MINUTE] = minute
        setInitialDateTime()
    }

    private fun setInitialDateTime() {
        view.btn_date.text = HelperService.parse(dateAndTime.time)
    }

}
