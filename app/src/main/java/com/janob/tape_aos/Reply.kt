package com.janob.tape_aos

import android.content.SharedPreferences
import java.util.prefs.Preferences

data class Reply(val idx: Int, var text :String){

    companion object{
        //댓글을 저장하고 수정하는 함수
        fun saveReplyFromPreferences(pref : SharedPreferences, idx : Int, text : String) : Reply{
            val editor = pref.edit()

            editor.putString("${idx}.text", text)

            editor.apply()

            return Reply(idx, text)
        }
        //댓글 리스트를 불러오는 함수
        fun getReplyFromPreferences(pref : SharedPreferences) : MutableList<Reply> {
            var replies = mutableListOf<Reply>()

            for (i in 0 until 10){
                val text = pref.getString("${i}.text","")!!
                if(text.isNotBlank()){
                    replies.add(Reply(i,text))
                }
            }
            return replies
        }
        //댓글 삭제하는 함수
        fun removeReplyFromPreferences(pref : SharedPreferences, idx :Int){
            val editor = pref.edit()
            editor.remove("${idx}.text")
            editor.apply()
        }
    }
}
