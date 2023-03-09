package com.gatebot.app.utilities

import com.gatebot.app.BuildConfig

object Constants {

    var BASE_URL = BuildConfig.HOST

    const val PREF_TOKEN: String = "PREF_TOKEN"
    const val PREF_LIVE: String = "PREF_LIVE"
    const val PREF_BASE_URLS: String = "PREF_BASE_URLS"

    // API
    const val API_AUTHORIZATION = "X-access-token"

    /** TAGS */
    const val FRAG_ALPHABETS = "ALPHABETS"

    /** Default values */
    const val PREF_IS_LOGIN = "IS_LOGIN"
    const val PREF_UNITS = "PREF_UNITS"
    const val PREF_DEVICES = "PREF_DEVICES"

    // Database Insert Types
    const val DB_TYPE_INSERT = "INSERT"
    const val DB_TYPE_INSERT_ALL = "INSERT_ALL"
    const val DB_TYPE_UPDATE = "UPDATE"
    const val DB_TYPE_DELETE = "DELETE"
    const val DB_TYPE_DELETE_ALL = "DELETE_ALL"

    /** Below pattern will accept
     *  Minimum eight characters, at least one uppercase letter, one lowercase letter and one number */
    const val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"
}  