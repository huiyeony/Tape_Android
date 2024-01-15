package com.janob.tape_aos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kakao.sdk.auth.model.OAuthToken

@Dao
interface LoginUserDao {

    @Insert
    fun insert(user: LoginUser)

/*    @Insert
    fun insertNickname(user: LoginUser)

    @Insert
    fun insertProfileintro(user: LoginUser)

    @Insert
    fun insertProfileimage(user: LoginUser)*/

    @Query("SELECT * FROM LoginUser")
    fun getLoginUsers(): List<LoginUser>

    @Query("SELECT * FROM LoginUser WHERE nickname = :nickname")
    fun getLoginUserNickname(nickname: String): LoginUser?

    @Query("SELECT * FROM LoginUser WHERE token = :token")
    fun getLoginUser(token: String): LoginUser?

}

