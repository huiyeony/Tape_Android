package com.janob.tape_aos

import android.app.blob.BlobStoreManager
import android.content.Intent
import android.content.pm.PackageInstaller
import android.media.MediaSession2
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.trusted.Token
import com.janob.tape_aos.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        var keyHash = Utility.getKeyHash(this)
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error == null) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

        setContentView(binding.root)

        binding.loginSignIn.setOnClickListener{
            onClick(binding.loginSignIn)
            Log.d("test", "확인")
        }

    }

    protected fun onClick(view : View){
        when (view?.id) {
            view.id -> {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                        Log.d("test", "확인1")
                        if (error != null) {
                            Log.d("login failure(onClick)", "카카오톡으로 로그인 실패 $error")
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            } else {
                                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                            }
                        } else if (token != null) {
                            Log.d("login success(onClick)", "카카오톡으로 로그인 성공 ${token.accessToken}")
                            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                            firstlogincheck(token)
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                }
            }
        }
    }

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("login failure(mCallback)", "카카오 계정으로 로그인 실패 $error")
        } else if (token != null) {
            Log.d("login success(mCallback)", "카카오 계정으로 로그인 성공 ${token.accessToken}")
            firstlogincheck(token)
        }
    }


    private fun firstlogincheck(Token : OAuthToken){
        //카카오로그인 확인
        val loginpreferences = getSharedPreferences("user.id",MODE_PRIVATE)
        val editor = loginpreferences.edit()


        token = Token.idToken.toString()  //사용자 token String으로 변환해서 저장

        //이미 있는 계정인지 비교하기 위해 DB에서 정보 가져오기
        val TapeDB = TapeDatabase.Instance(this)!!
        val user = TapeDB.loginuserDao().getLoginUser(token)

        val Equal : Boolean = token.equals(user.toString())



        if (Equal) { // 이후 로그인 시 처리
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fm, TapeFragment())
                .commit()
        } else { // 처음 로그인 시 처리
            startActivity(Intent(this, OnboardActivity::class.java))
            checkProfile()
        }
    }

    fun checkProfile() : String {
        return token
    }

    private fun save(jwt: Int) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)  //인자값으로 받은 jwt(user.id)를 저장
        val editor = spf.edit()

        editor.putInt("jwt", jwt) //키값 저장
        editor.apply()
    }


}