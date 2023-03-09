package com.mvvm.app.data.remote.repository

import androidx.lifecycle.MutableLiveData
import com.mvvm.app.data.remote.client.GateBotClientBuilder
import com.mvvm.app.data.remote.model.base.Resource
import com.mvvm.app.data.remote.model.devices.GetDeviceResponse
import com.mvvm.app.data.remote.model.login.LoginReq
import com.mvvm.app.data.remote.model.login.LoginResponse
import com.mvvm.app.data.remote.model.traffic.LiveTrafficResponse
import com.mvvm.app.data.remote.model.unit.UnitResponse
import com.mvvm.app.data.remote.model.vehicle.AllowVehicleResponse
import com.mvvm.app.data.remote.model.vehicle.RegisterVehicle
import com.mvvm.app.data.remote.model.vehicle.VehicleLogResponse

class UsersRepository  : BaseRepository() {


    private var apiService = GateBotClientBuilder.Companion.ServicesApiInterface

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: UsersRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UsersRepository().also { instance = it }
            }
    }

    fun postLogin(responseData: MutableLiveData<Resource<LoginResponse>>, request: LoginReq) {
        fetch(responseData, apiService.fetchManagers()?.postLogin(request))
    }

    fun getDevices(responseData: MutableLiveData<Resource<GetDeviceResponse>>) {
        fetch(responseData, apiService.fetchManagers()?.getDevices())
    }

    fun getVehicleLog(responseData: MutableLiveData<Resource<VehicleLogResponse>>, lane_id:String,
    status: String, live: String) {
        fetch(responseData, apiService.fetchManagers()?.getVehicleLog(lane_id, status, live))
    }

    fun getUnits(responseData: MutableLiveData<Resource<UnitResponse>>) {
        fetch(responseData, apiService.fetchManagers()?.getUnits())
    }

    fun getLiveTrafficStatus(responseData: MutableLiveData<Resource<LiveTrafficResponse>>) {
        fetch(responseData, apiService.fetchManagers()?.getLiveTrafficStatus())
    }

    fun allowVehicle(responseData: MutableLiveData<Resource<AllowVehicleResponse>>, log_id:String) {
        fetch(responseData, apiService.fetchManagers()?.allowVehicle(log_id))
    }

    fun registerVehicle(responseData: MutableLiveData<Resource<AllowVehicleResponse>>, req: RegisterVehicle) {
        fetch(responseData, apiService.fetchManagers()?.registerVehicle(req))
    }

    fun blacklistVehicle(responseData: MutableLiveData<Resource<AllowVehicleResponse>>, log_id:String) {
        fetch(responseData, apiService.fetchManagers()?.blacklistVehicle(log_id))
    }
}