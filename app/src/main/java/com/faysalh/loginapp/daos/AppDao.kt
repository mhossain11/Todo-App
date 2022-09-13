package com.faysalh.loginapp.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.faysalh.loginapp.entity.AppModel

@Dao
interface AppDao {

    @Insert
   suspend fun add (appModel: AppModel)

    @Delete
   suspend fun delete(appModel: AppModel)

    @Update
   suspend fun update(appModel: AppModel)

    @Query("Select * From app_tbl order by id desc ")
    fun getAll():LiveData<List<AppModel>>

    @Query("SELECT * FROM app_tbl WHERE title LIKE :searchQuery ")
    fun searchDatabase(searchQuery:String):LiveData<List<AppModel>>

    /*@Query("SELECT * FROM app_tbl ")
    fun favorite():LiveData<List<AppModel>>*/

}