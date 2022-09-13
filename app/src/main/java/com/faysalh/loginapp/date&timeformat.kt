package com.faysalh.loginapp

import com.faysalh.loginapp.entity.AppModel
import java.text.SimpleDateFormat
import java.util.*

fun formattedDateTime(millie:Long,format:String):String{
    return SimpleDateFormat(format, Locale.getDefault()).format(Date(millie))
}

const val deleteClick = "Delete"
const val BUNDLE_NOTE_ID="bundle_note_id"
const val on = "ON"
const val off = "OFF"

interface CallBack{
    fun actionCallback(appModel: AppModel, tag:String)
}
