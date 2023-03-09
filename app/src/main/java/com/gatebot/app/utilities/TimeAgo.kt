package com.gatebot.app.utilities

import android.annotation.SuppressLint
import android.content.Context
import com.gatebot.app.R
import java.lang.StringBuilder
import java.util.*
import kotlin.math.roundToInt

open class TimeAgo(protected var context: Context) {
    fun timeAgo(date: Date): String {
        return timeAgo(date.time)
    }

    @SuppressLint("StringFormatInvalid")
    fun timeAgo(millis: Long): String {
        val diff = Date().time - millis
        val r = context.resources
        val prefix = r.getString(R.string.time_ago_prefix)
        val suffix = r.getString(R.string.time_ago_suffix)
        val seconds = (Math.abs(diff) / 1000).toDouble()
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val years = days / 365
        val words: String = when {
            seconds < 60 -> {
                "${seconds.roundToInt()} secs"
            }
            minutes < 60 -> {
                "${minutes.roundToInt()} mins"
            }
            hours < 24 -> {
                "${hours.roundToInt()} hrs ${getMins(minutes)}" //r.getString(R.string.time_ago_hours, hours.roundToInt())
            }days < 30 -> {
                "${days.roundToInt()} days" //r.getString(R.string.time_ago_days, days.roundToInt())
            }
            days < 45 -> {
                r.getString(R.string.time_ago_month, 1)
            }
            days < 365 -> {
                r.getString(R.string.time_ago_months, (days / 30).roundToInt())
            }
            years < 1.5 -> {
                r.getString(R.string.time_ago_year, 1)
            }
            else -> {
                r.getString(R.string.time_ago_years, years.roundToInt())
            }
        }
        val sb = StringBuilder()
        if (prefix != null && prefix.length > 0) {
            sb.append(prefix).append(" ")
        }
        sb.append(words)
        if (suffix != null && suffix.length > 0) {
            sb.append(" ").append(suffix)
        }
        return sb.toString().trim { it <= ' ' }
    }

    private fun getMins(minutes: Double): String {
        return if (minutes < 120) "${minutes.roundToInt() / 2} mins" else ""
    }
}