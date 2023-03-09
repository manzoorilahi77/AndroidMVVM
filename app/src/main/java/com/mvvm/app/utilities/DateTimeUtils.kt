package com.mvvm.app.utilities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    const val YYYYTOSS = "EEE, dd MMM yyyy HH:mm:ss Z" // "Thu, 02 Jun 2022 06:44:44 GMT"
    const val DateDDMMYYYY = "dd MMM yyyy"
    const val TIME = "hh:mm a"

    fun formatDate(data: String?, inFormat: String, outFormat: String): String {
        val inputFormat = SimpleDateFormat(inFormat, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(outFormat, Locale.ENGLISH)
        if (!data.isNullOrEmpty()) {
            try {
                val date = inputFormat.parse(data)
                return outputFormat.format(date)
            } catch (ex: ParseException) {
                ex.message
            }
        }
        return ""
    }
}