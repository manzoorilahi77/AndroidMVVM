package com.mvvm.app.data.remote.model.devices


import com.google.gson.annotations.SerializedName

data class GetDeviceResponse(
    @SerializedName("entry_devices")
    var entryDevices: List<Device>?,
    @SerializedName("exit_devices")
    var exitDevices: List<Device>?,
    @SerializedName("society_name")
    var societyName: String?
)