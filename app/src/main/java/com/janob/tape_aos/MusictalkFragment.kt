package com.janob.tape_aos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.janob.tape_aos.databinding.FragmentMusictalkBinding

class MusictalkFragment : Fragment() {

    lateinit var binding: FragmentMusictalkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusictalkBinding.inflate(layoutInflater)
        return binding.root
    }
}