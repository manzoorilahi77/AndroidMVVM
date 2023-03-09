package com.mvvm.app.data.database.daos

import androidx.room.*
import com.mvvm.app.data.database.entities.VehicleEntity

@Dao
interface VehicleDao {

    @get:Query("SELECT * FROM VehicleTable")
    val all: List<VehicleEntity>

    @Query("SELECT * FROM VehicleTable WHERE id = :entityId")
    fun getVehicleById(entityId: Int): VehicleEntity

    @Insert
    fun insert(entity: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entity: List<VehicleEntity>)

    @Update
    fun update(entity: VehicleEntity)

    @Delete
    fun delete(entity: VehicleEntity)

    @Query("DELETE FROM VehicleTable")
    fun deleteAllFromTable()

}
