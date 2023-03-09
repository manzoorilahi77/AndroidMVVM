package com.gatebot.app.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gatebot.app.MApplication

/**
 * Preferences class stores temporarily data using [PreferenceManager] and [SharedPreferences]
 */
class AppPreferences {

    private val mSharedPreferences: SharedPreferences

    init {
        val mContext = MApplication.getInstance()
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
    }

    companion object {

        private const val APP_ENV = "APP_ENV"
        private const val APP_ENV_DEV = "dev"
        private const val APP_ENV_TES = "testing"
        private const val APP_ENV_STAGE = "staging"
        private const val APP_ENV_PROD = "prod"
        private const val AUTH_TOKEN = "auth_token"
        private const val IS_TABLET = "is_tablet"

        fun setAppEnv(appEnv: String, context: Context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(APP_ENV, appEnv).apply()
        }

        fun getAppEnv(context: Context): Boolean {
            return APP_ENV_DEV == PreferenceManager.getDefaultSharedPreferences(context).getString(
                APP_ENV, "")
        }
    }

    val isTablet: Boolean
        get() = mSharedPreferences.getBoolean(IS_TABLET, false)

    fun setString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return mSharedPreferences.getString(key, "") ?: ""
    }

    fun setInt(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return mSharedPreferences.getInt(key, 0)
    }

    fun removeString(key:String){
        mSharedPreferences.edit().remove(key).apply()
    }

    fun setFloat(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return mSharedPreferences.getFloat(key, 0f)
    }

    fun setBool(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBool(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }
    fun saveArrayList(list: ArrayList<String>, key: String) {
        val editor = mSharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()     // This line is IMPORTANT !!!
    }
    fun getArrayList(key: String): ArrayList<String> {
        val gson = Gson()
        val json = mSharedPreferences.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        return gson.fromJson<ArrayList<String>>(json, type)
    }
    fun setIsTablet() {
        val editor = mSharedPreferences.edit()
        editor.putBoolean(IS_TABLET, true)
        editor.apply()
    }
    fun clear(){
        mSharedPreferences.edit().clear().apply()
    }

}
