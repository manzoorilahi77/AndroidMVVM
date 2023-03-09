package com.mvvm.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mvvm.app.data.database.converters.VehicleImageConverter

@Entity(tableName = "VehicleTable")
class VehicleEntity() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var authorised: Boolean? = null
    var blacklisted: Boolean? = null
    @TypeConverters(VehicleImageConverter::class)
    var imgPath: ArrayList<String>? = ArrayList()
    var laneId: Int? = null
    var laneName: String? = null
    var licensePlateNumber: String? = null
    var logId: Int? = null
    var timestamp: String? = null
    var unitName: String? = null
    var dateStr: String? = null
    var timeStr: String? = null
    var mLive: Boolean? = null
}