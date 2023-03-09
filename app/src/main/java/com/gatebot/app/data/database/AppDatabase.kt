package com.gatebot.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gatebot.app.BuildConfig
import com.gatebot.app.data.database.entities.VehicleEntity
import com.gatebot.app.data.database.daos.VehicleDao

@Database(entities = [VehicleEntity::class], version = BuildConfig.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "${BuildConfig.APP_NAME}.db").build()
    }
}