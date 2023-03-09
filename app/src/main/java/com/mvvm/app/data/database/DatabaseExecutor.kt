package com.mvvm.app.data.database

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.mvvm.app.data.database.entities.VehicleEntity
import com.mvvm.app.utilities.CommonUtils
import com.mvvm.app.utilities.Constants
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.math.abs

class DatabaseExecutor(private val mApplication: Application) {
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    val databaseInstance = AppDatabase.getInstance(context = mApplication)
    val MAX_HOURS = 24

    interface Callback {
        fun onComplete(result: List<VehicleEntity>)
        fun onError(e: Exception?)
    }

    fun execute(dbType: String, entity: VehicleEntity, callback: Callback) {
        executor.execute {
            val result: List<VehicleEntity>
            try {
                when (dbType) {
                    Constants.DB_TYPE_INSERT -> {
                        databaseInstance.vehicleDao().insert(entity)
                    }
                    Constants.DB_TYPE_UPDATE -> {
                        databaseInstance.vehicleDao().update(entity)
                    }
                    Constants.DB_TYPE_DELETE -> {
                        databaseInstance.vehicleDao().delete(entity)
                    }
                }
                result = databaseInstance.vehicleDao().all
                val finalResult = getResult(result)

                handler.post {
                    callback.onComplete(finalResult)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handler.post {
                    callback.onError(e)
                }
            }
        }
    }

    fun insertAll(entity: List<VehicleEntity>, callback: Callback) {
        executor.execute {
            val result: List<VehicleEntity>
            try {
                databaseInstance.vehicleDao().insertAll(entity)
                result = databaseInstance.vehicleDao().all
                val finalResult = getResult(result)

                handler.post {
                    callback.onComplete(finalResult)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handler.post {
                    callback.onError(e)
                }
            }
        }
    }

    private fun getResult(result: List<VehicleEntity>): List<VehicleEntity> {
        val finalResult = ArrayList<VehicleEntity>()
        if (result.isNotEmpty()) {
            result.forEach { item ->
                val currentTime = Date().time
                val diff = currentTime - CommonUtils.getTimestamp(item.timestamp)
                val seconds = (abs(diff) / 1000).toDouble()
                val minutes = seconds / 60
                val hours = minutes / 60
                if (hours <= MAX_HOURS) {
                    //Log.e("diff", "$diff, hours: $hours")
                    finalResult.add(item)
                } else {
                    databaseInstance.vehicleDao().delete(item)
                }
            }
        }
        return finalResult
    }

    fun executeDeleteAll(callback: Callback) {
        executor.execute {
            val result: List<VehicleEntity>
            val databaseInstance = AppDatabase.getInstance(context = mApplication)
            try {
                databaseInstance.vehicleDao().deleteAllFromTable()
                result = databaseInstance.vehicleDao().all
                handler.post {
                    callback.onComplete(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handler.post {
                    callback.onError(e)
                }
            }
        }
    }

    fun executeGetAll(callback: Callback) {
        executor.execute {
            val result: List<VehicleEntity>
            val databaseInstance = AppDatabase.getInstance(context = mApplication)
            result = databaseInstance.vehicleDao().all
            try {
                handler.post {
                    callback.onComplete(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handler.post {
                    callback.onError(e)
                }
            }
        }
    }
}