package com.janob.tape_aos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tape.OnboardVPAdapter
import com.example.tape.Profile1Fragment
import com.example.tape.Profile2Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.janob.tape_aos.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {
    lateinit var binding : ActivityOnboardBinding

    val profile1Fragment = Profile1Fragment()
    val profile2Fragment = Profile2Fragment()
    val loginActivity = LoginActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // tab layout와 viewpager 연결
        TabLayoutMediator(binding.onboardTabTb, binding.onboardViewpagerVp){ tab, position
            -> when(position){
            0 -> "온보드1"
            1 -> "온보드2"
            2 -> "프로필 설정1"
            3 -> "프로필 설정2"
            4 -> "사진 선택"
        }
        }
        // viewpager와 custom pager adapter 연결
        binding.onboardViewpagerVp.adapter = OnboardVPAdapter(this)

        // button과 viewpager 연결하기 : 다음 뷰페이저로 이동
        binding.onboardButtonBtn.setOnClickListener {
            if(binding.onboardViewpagerVp.currentItem==4){
                val profileDB = TapeDatabase.Instance(this)!!
                profileDB.loginuserDao().insert(getLoginUser())

                val checkusers = profileDB.loginuserDao().getLoginUsers() //log로 확인
                Log.d("users_check", checkusers.toString())

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                if(binding.onboardViewpagerVp.currentItem==3){
                    if(profile2Fragment.checkProfile()=="false"){ }
                    binding.onboardViewpagerVp.currentItem = binding.onboardViewpagerVp.currentItem + 1

                }
                else if(binding.onboardViewpagerVp.currentItem==2){
                    if(profile1Fragment.checkProfile()=="false"){ }
                    binding.onboardViewpagerVp.currentItem = binding.onboardViewpagerVp.currentItem + 1

                }
                binding.onboardViewpagerVp.currentItem = binding.onboardViewpagerVp.currentItem + 1
            }
        }
    }

    private fun getLoginUser(): LoginUser {
        val nickname :String = profile1Fragment.checkProfile()
        val token : String = loginActivity.checkProfile()
        val profileimg : ByteArray = profile2Fragment.checkOpenGallery()
        val profileintro: String = profile2Fragment.checkProfile()
        return LoginUser(token, nickname, profileimg, profileintro)
    }

}