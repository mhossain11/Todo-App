package com.faysalh.loginapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.faysalh.loginapp.daos.AppDao
import com.faysalh.loginapp.entity.AppModel


@Database(entities = [AppModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDaos():AppDao

    companion object{

        /*val migration_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table 'app_tbl'add column isFavorite text")
            }

        }*/
        var db :AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            return db?: synchronized(this){
                val _db = Room.databaseBuilder(
                    context,AppDatabase::class.java,
                    "appDatabase"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                  //  .addMigrations(migration_1_2)
                    .build()
                db=_db
                _db
            }
        }
    }
}