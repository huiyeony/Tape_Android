package com.example.tape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.janob.tape_aos.databinding.FragmentProfile1Binding

class Profile1Fragment : Fragment() {

    lateinit var binding : FragmentProfile1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfile1Binding.inflate(inflater, container, false)

        return binding.root
    }
}