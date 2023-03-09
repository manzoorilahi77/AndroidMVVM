package com.gatebot.app.utilities

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.gatebot.app.BuildConfig
import java.util.regex.Matcher
import java.util.regex.Pattern

const val EMAIL_PATTERN =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

fun Activity.logd(message: String) {
    if (BuildConfig.DEBUG)
        Log.d(this::class.java.simpleName, message)
}

fun Activity.loge(message: String) {
    if (BuildConfig.DEBUG)
        Log.e(this::class.java.simpleName, message)
}

fun Activity.logv(message: String) {
    if (BuildConfig.DEBUG)
        Log.v(this::class.java.simpleName, message)
}


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun EditText.onTextChanged(action: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(string: Editable?) = Unit
        override fun beforeTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) =
            Unit

        override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
            action(string ?: "")
        }
    })
}

fun EditText.clearOnTextChangedListener() {
    onTextChanged {}
}

fun validateEmail(email: String?): Boolean {
    return if (email.isNullOrEmpty()) {
        false
    } else {
        val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher
        matcher = pattern.matcher(email)
        matcher.matches()
    }
}

fun isDynamicTokenRequired(): Boolean{
    return BuildConfig.API_VERSION == "V2"
}

fun String?.removeComma() = this?.replace("""[$,]""".toRegex(),"")





