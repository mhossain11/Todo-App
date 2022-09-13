package com.faysalh.loginapp.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "app_tbl")
data class AppModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String? = null,
    val description:String?=null,
    var isFavorite:Boolean= false,
    var date: Long,
    var time: Long
 ):Parcelable