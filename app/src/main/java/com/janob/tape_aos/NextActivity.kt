package com.janob.tape_aos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.janob.tape_aos.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    lateinit var binding : ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}