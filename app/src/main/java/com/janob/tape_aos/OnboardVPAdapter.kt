package com.example.tape

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardVPAdapter(fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Onboard1Fragment()
            1 -> Onboard2Fragment()
            2 -> Profile1Fragment()
            3 -> Profile2Fragment()
            else -> SetpictureFragment()
        }
    }
}