package com.faysalh.loginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.faysalh.loginapp.database.AppDatabase
import com.faysalh.loginapp.entity.AppModel
import com.faysalh.loginapp.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(ctx: Application) :AndroidViewModel(ctx) {

val repo = AppRepository(AppDatabase.getDatabase(ctx).appDaos())

    fun insert(appModel: AppModel){
        viewModelScope.launch {
            repo.add(appModel)
        }

    }

    fun edit(appModel: AppModel){
        viewModelScope.launch {
            repo.update(appModel)
        }

    }

    fun remove(appModel: AppModel){
        viewModelScope.launch {
            repo.delete(appModel)
        }

    }

    fun getAllView(): LiveData<List<AppModel>> {
        return repo.repoGetAll()
    }

    fun searchDatabaseView(searchQuery:String):LiveData<List<AppModel>>{
        return repo.repoSearchDatabase(searchQuery)
    }

    /*fun favoriteView():LiveData<List<AppModel>>{
        return repo.repoFavorite()
    }*/

}