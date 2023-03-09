package com.gatebot.app.utilities

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.gatebot.app.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CommonDatePicker(private val context: Context, val type: Int = 0,
                       private val date: String?, val listener: (String) -> Unit) {

    private var cal = Calendar.getInstance(Locale.ENGLISH)

    companion object {
        const val ALL = 1
        const val ONLY_FUTURE = 2
        const val ONLY_PAST = 3
    }

    init {
        showPicker()
    }

    private fun showPicker() {
        val locale = Locale.ENGLISH
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)
        cal.time = validateDate()
        val picker = DatePickerDialog(context, R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            },
            // set DatePickerDialog to point to today's date when it loads up
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        if (type == ONLY_FUTURE) {
            picker.datePicker.minDate = getCurrentTime()
        }
        if (type == ONLY_PAST) {
            picker.datePicker.maxDate = getCurrentTime()
        }
        picker.show()
    }

    private fun getCurrentTime(): Long {
        val inSdf = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
        val ca = Calendar.getInstance(Locale.ENGLISH)
        ca.time = Date(System.currentTimeMillis())
        val s = "${ca.get(Calendar.YEAR)}-${ca.get(Calendar.MONTH) + 1}-${ca.get(Calendar.DAY_OF_MONTH)}"
        val currDate = inSdf.parse(s)
        return currDate.time - 1000
    }

    fun showPicker(isOnlyFuture: Boolean) {
        val locale = Locale.ENGLISH
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)
        val picker = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            },
            // set DatePickerDialog to point to today's date when it loads up
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
        if (isOnlyFuture) {
            picker.datePicker.minDate = System.currentTimeMillis() - 1000
        }
        picker.show()
    }

    private fun validateDate(): Date {
        val myFormat = "dd-MMM-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        if (!date.isNullOrEmpty()) {
            try {
                val d = sdf.parse(date)
                return d
            } catch (e: ParseException) {

            }
        }
        return Date()
    }

    private fun updateDateInView() {
        val myFormat = "dd-MMM-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        listener(sdf.format(cal.time))
    }
}