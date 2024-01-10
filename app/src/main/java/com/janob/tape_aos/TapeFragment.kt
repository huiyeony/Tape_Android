package com.janob.tape_aos

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.janob.tape_aos.databinding.FragmentTapeBinding

class TapeFragment : Fragment() {

    lateinit var binding: FragmentTapeBinding
    private lateinit var replies: MutableList<Reply>
    private lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTapeBinding.inflate(layoutInflater)

        //더미 데이터
        pref = this.requireActivity().getSharedPreferences("reply", AppCompatActivity.MODE_PRIVATE)
        initDummy()
        replies = Reply.getReplyFromPreferences(pref)

        //일단 텍스트 클릭하면 댓글로 이동함
        binding.clickTv.setOnClickListener{
            val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_fm, ReplyListFragment())
            transaction.commit()
        }


        return binding.root
    }
    private fun initDummy() {

        val initialized = pref.getBoolean("initialized",false)
        if(!initialized){
            Reply.saveReplyFromPreferences(pref,0, "너무 좋아요ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ가사 너무 아련...")
            Reply.saveReplyFromPreferences(pref,1, "노래 너무 좋아요❤️")
            Reply.saveReplyFromPreferences(pref,2, "이 조합 미쳤다,,완벽한 겨울노래")
            Reply.saveReplyFromPreferences(pref,3, "목소리 너무 신비스럽게 이쁘네")
            Reply.saveReplyFromPreferences(pref,4, "들을 때마다 더 좋아지냐 마음이 따뜻해지네")
            Reply.saveReplyFromPreferences(pref,5, "안볼수가 없는 조합")
            Reply.saveReplyFromPreferences(pref,6, "우와... 겨울 분위기 대박 노래 너무 좋아요ㅠ")
            Reply.saveReplyFromPreferences(pref,7, "두 분의 목소리 진짜 악기같다... 넘 좋아요❤")
            Reply.saveReplyFromPreferences(pref,8, "combinations of angelic voice")
            Reply.saveReplyFromPreferences(pref,9, "what a wonderful and emotional collaboration..")

        }

        val editor = pref.edit()
        editor.putBoolean("initialized",true)
        editor.apply()

    }
}
