package com.mvvm.app.data.local

import com.mvvm.app.BuildConfig
import com.mvvm.app.data.remote.model.devices.GetDeviceResponse
import com.mvvm.app.data.remote.model.unit.Units
import com.mvvm.app.utilities.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

object Singleton {

    var isNetworkConnected: Boolean = true
    var token: String = ""
    var isLive: Boolean = false
    var unitList: ArrayList<Units> = ArrayList()
    var devices: GetDeviceResponse? = null
    val appPreferences = AppPreferences()


    fun initPrefData() {
        updateToken(null)
        updateBaseURL(null)
        updateUnitList(null)
        updateLive(null)
        updateDevices(null)
    }
    fun updateToken(data: String?) {
        if (data != null) {
            appPreferences.setString(Constants.PREF_TOKEN, data)
        }
        val newData = appPreferences.getString(Constants.PREF_TOKEN)
        token = if (newData.isNotEmpty()) newData else ""
    }

    fun updateLive(data: Boolean?) {
        if (data != null) {
            appPreferences.setBool(Constants.PREF_LIVE, data)
        }
        isLive = appPreferences.getBool(Constants.PREF_LIVE) ?: false
    }

    fun updateBaseURL(data: String?) {
        if (data != null) {
            appPreferences.setString(Constants.PREF_BASE_URLS, data)
        }
        val newData = appPreferences.getString(Constants.PREF_BASE_URLS)
        Constants.BASE_URL = if (newData.isNotEmpty()) newData else BuildConfig.HOST
    }

    fun updateUnitList(list: ArrayList<Units>?) {
        if (!list.isNullOrEmpty()) {
            val data = Gson().toJson(list)
            appPreferences.setString(Constants.PREF_UNITS, data)
        }
        val newData = appPreferences.getString(Constants.PREF_UNITS)
        val turnsType = object : TypeToken<ArrayList<Unit>>() {}.type
        unitList = if (newData.isNotEmpty()) Gson().fromJson(newData, turnsType) else ArrayList()
    }

    fun updateDevices(response: GetDeviceResponse?) {
        if (response != null) {
            val data = Gson().toJson(response)
            appPreferences.setString(Constants.PREF_DEVICES, data)
        }
        val newData = appPreferences.getString(Constants.PREF_DEVICES)
        val turnsType = object : TypeToken<GetDeviceResponse>() {}.type
        devices = if (newData.isNotEmpty()) Gson().fromJson(newData, turnsType) else null
    }

}