package com.janob.tape_aos

import com.google.gson.annotations.SerializedName

data class IncludedSong(
    val songIdx:Int,
    val coverImg:Int,
    val title:String,
    val singer:String,
)