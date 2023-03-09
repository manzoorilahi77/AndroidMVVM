package com.gatebot.app.data.remote.interfaces

import com.gatebot.app.data.remote.model.devices.GetDeviceResponse
import com.gatebot.app.data.remote.model.login.LoginReq
import com.gatebot.app.data.remote.model.login.LoginResponse
import com.gatebot.app.data.remote.model.traffic.LiveTrafficResponse
import com.gatebot.app.data.remote.model.unit.UnitResponse
import com.gatebot.app.data.remote.model.vehicle.AllowVehicleResponse
import com.gatebot.app.data.remote.model.vehicle.RegisterVehicle
import com.gatebot.app.data.remote.model.vehicle.VehicleLogResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IUsersController {

    @POST("/loginCheck")
    fun postLogin(@Body req: LoginReq): Call<LoginResponse>

    @GET("/getDevices")
    fun getDevices(): Call<GetDeviceResponse>

    @GET("/getVehicleLog")
    fun getVehicleLog(@Query("lane_id") lane_id: String,
                   @Query("status") status: String,
                   @Query("live") live: String ): Call<VehicleLogResponse>

    @GET("/getUnits")
    fun getUnits(): Call<UnitResponse>

    @GET("/get_live_traffic_status")
    fun getLiveTrafficStatus(): Call<LiveTrafficResponse>

    @POST("/allowVehicle")
    fun allowVehicle(@Query("vehicle_log_id") vehicle_log_id: String): Call<AllowVehicleResponse>

    @POST("/addVehicle")
    fun registerVehicle(@Body request: RegisterVehicle): Call<AllowVehicleResponse>

    @POST("/blacklistVehicle")
    fun blacklistVehicle(@Query("vehicle_log_id") vehicle_log_id: String): Call<AllowVehicleResponse>

}