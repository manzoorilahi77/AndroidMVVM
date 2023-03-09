package com.gatebot.app.data.remote.model.devices


import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("device_ip")
    var deviceIp: String?,
    @SerializedName("lane_id")
    var laneId: Int?,
    @SerializedName("lane_name")
    var laneName: String?
)