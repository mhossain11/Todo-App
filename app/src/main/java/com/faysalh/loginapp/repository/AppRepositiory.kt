package com.faysalh.loginapp.repository

import androidx.lifecycle.LiveData
import com.faysalh.loginapp.daos.AppDao
import com.faysalh.loginapp.entity.AppModel

class AppRepository(var appDao: AppDao) {

    suspend fun add (appModel: AppModel){
        appDao.add(appModel)
    }

   suspend fun update (appModel: AppModel){
        appDao.update(appModel)
    }

  suspend  fun delete (appModel: AppModel){
        appDao.delete(appModel)
    }

   fun repoGetAll(): LiveData<List<AppModel>> {
        return appDao.getAll()
    }

    fun repoSearchDatabase(searchQuery:String):LiveData<List<AppModel>>{
        return appDao.searchDatabase(searchQuery)
    }

    /*fun repoFavorite():LiveData<List<AppModel>>{
        return appDao.favorite()
    }*/

}