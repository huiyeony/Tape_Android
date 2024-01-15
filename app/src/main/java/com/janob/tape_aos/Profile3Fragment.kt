package com.janob.tape_aos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.janob.tape_aos.databinding.FragmentProfile2Binding
import com.janob.tape_aos.databinding.FragmentProfile3Binding

class Profile3Fragment : Fragment() {

    lateinit var binding : FragmentProfile3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfile3Binding.inflate(inflater, container, false)


        return binding.root
    }

}