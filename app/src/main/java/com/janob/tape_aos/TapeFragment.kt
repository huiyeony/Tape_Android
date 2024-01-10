package com.janob.tape_aos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.janob.tape_aos.databinding.FragmentTapeBinding
import com.janob.tape_aos.databinding.ItemTapeBinding

class TapeFragment : Fragment() {

    lateinit var binding: FragmentTapeBinding
    private var TapeAlbumData = ArrayList<TapeAlbum>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTapeBinding.inflate(inflater, container, false)



        //메인 tape 리사이클러뷰 데이터
        TapeAlbumData.add(TapeAlbum("Broken Melodies", "NCT DREAM", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("Thirsty", "aepsa", "K_pop_lover", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("와르르", "Colde", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("Broken Melodies", "NCT DREAM", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("Thirsty", "aepsa", "K_pop_lover", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("와르르", "Colde", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("Broken Melodies", "NCT DREAM", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("Thirsty", "aepsa", "K_pop_lover", R.drawable.albumcover_5, R.drawable.albumcover_5))
        TapeAlbumData.add(TapeAlbum("와르르", "Colde", "music_play", R.drawable.albumcover_5, R.drawable.albumcover_5))


        //리사이클러뷰 어댑터
        val tapeAlbumRVAdapter = TapeAlbumRVAdapter(TapeAlbumData, requireContext())
        binding.tapeTapelistRv.adapter=tapeAlbumRVAdapter
        binding.tapeTapelistRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return binding.root

    }

}


