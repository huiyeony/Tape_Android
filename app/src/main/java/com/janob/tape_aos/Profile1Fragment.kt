package com.example.tape

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.janob.tape_aos.LoginUser
import com.janob.tape_aos.TapeDatabase
import com.janob.tape_aos.databinding.FragmentProfile1Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Profile1Fragment : Fragment() {  //닉네임 설정

    lateinit var binding: FragmentProfile1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfile1Binding.inflate(inflater, container, false)

        //todo : 빈공간 누르면 키보드 hide
        binding.profile1NicknameEt.setOnFocusChangeListener { view, hasFocus ->
            Toast.makeText(context, "키보드 종료 $hasFocus", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }


     fun checkProfile(): String {    //프로필조건 충족하는지 확인

        if (binding.profile1NicknameEt.text.toString().isEmpty() || binding.profile1NicknameEt.text.length > 20) {
            binding.profile1NicknameError2Tv.visibility = View.VISIBLE
            binding.profile1NicknameError1Tv.visibility = View.GONE
            binding.profile1NicknameError2Tv.visibility = View.GONE
            return "false"

        } else if (!profile1NicknameEtCheck(binding.profile1NicknameEt.text.toString())) {
            binding.profile1NicknameError1Tv.visibility = View.VISIBLE
            binding.profile1NicknameError2Tv.visibility = View.GONE
            binding.profile1NicknameError3Tv.visibility = View.GONE
            return "false"

        } else if(!CheckExistNickname(binding.profile1NicknameEt.text.toString())) {//다른 아이디와 같을때
            binding.profile1NicknameError1Tv.visibility = View.GONE
            binding.profile1NicknameError2Tv.visibility = View.GONE
            binding.profile1NicknameError3Tv.visibility = View.VISIBLE
            return "false"
        }
         val profile1DB = TapeDatabase.Instance(requireContext())!!
         val nicknameUser = binding.profile1NicknameEt.text.toString()

         return nicknameUser
    }


    private fun profile1NicknameEtCheck(string : String) :Boolean{  //영어, 숫자, 마침표, _ 외 다른 거 있는지 확인
        val check = "[a-zA-Z0-9._]+".toRegex()
        return string.matches(check)
    }


    private fun CheckExistNickname(string : String) :Boolean {  //이미 있는 닉네임인지 확인

        val Nicknamedb = TapeDatabase.Instance(requireContext())
        val users = Nicknamedb.loginuserDao().getLoginUsers()

        // 입력된 닉네임과 동일한 닉네임이 이미 존재하는지 확인
        val existNickname = users.any { it.nickname == string }

        return !existNickname
    }
}
