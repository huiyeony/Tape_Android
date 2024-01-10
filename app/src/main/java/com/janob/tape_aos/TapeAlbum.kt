package com.janob.tape_aos

data class TapeAlbum(
    var tapeTitle: String = "",   // 테이프 이름
    var singer : String = "",
    var userName : String = "",
    var albumCover : Int ?= null,
    var userImage : Int ?= null   //유저 동그라미 화면

)
