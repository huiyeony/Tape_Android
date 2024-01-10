package com.janob.tape_aos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tape.OnboardVPAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.janob.tape_aos.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {
    lateinit var binding : ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tab layout와 viewpager 연결
        TabLayoutMediator(binding.onboardTabTb, binding.onboardViewpagerVp){ tab, position
            -> when(position){
            0 -> "프로필 설정1"
            1 -> "프로필 설정2"
            2 -> "사진 선택"
            3 -> "온보드1"
            4 -> "온보드2"
        }
        }
        // viewpager와 custom pager adapter 연결
        binding.onboardViewpagerVp.adapter = OnboardVPAdapter(this)

        // button과 viewpager 연결하기 : 다음 뷰페이저로 이동
        binding.onboardButtonBtn.setOnClickListener {
            if(binding.onboardViewpagerVp.currentItem==4){
                startActivity(Intent(this, NextActivity::class.java))
                finish()
            }
            else{
                if(binding.onboardViewpagerVp.currentItem==3){
                    binding.onboardButtonBtn.text = "테이프 시작하기"
                }
                binding.onboardViewpagerVp.currentItem = binding.onboardViewpagerVp.currentItem + 1
            }
        }
    }
}