package com.example.tape

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.janob.tape_aos.LoginUser
import com.janob.tape_aos.R
import com.janob.tape_aos.TapeDatabase
import com.janob.tape_aos.databinding.FragmentProfile2Binding
import java.io.ByteArrayOutputStream

class Profile2Fragment : Fragment() {  //소개글, 프로필 설정

    lateinit var binding: FragmentProfile2Binding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var profile2DB: TapeDatabase
    lateinit var imageByte : ByteArray

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfile2Binding.inflate(inflater, container, false)

        profile2DB = TapeDatabase.Instance(requireContext())!!

        binding.profile2IconIv.setOnClickListener {
            OpenGallery() //갤러리 연동
        }

        return binding.root
    }

    private fun OpenGallery(){
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intent = checkNotNull(result.data)
                    val imageUri = intent.data

                    Glide.with(requireContext())
                        .load(imageUri)
                        .into(binding.profile2PicIv)

                    //갤러리에서 이미지 가져와 ByteArray 변환
                    imageByte = imageUri?.let { uriToByteArray(it) }!!


                    checkOpenGallery()
                    // Room 데이터베이스에 저장
                    /*if (imageByte != null) {  //갤러리에서 사진 선택했을 때
                        //profile2DB.loginuserDao().insertProfileimage(imageByte)
                        checkOpenGallery(imageByte, false)
                    } else {
                        val ImgnothingByteArray = imageToByteArray(requireContext().getDrawable(R.drawable.albumcover_5))
                        checkOpenGallery(ImgnothingByteArray, true)

                        //profile2DB.loginuserDao().insertProfileimage(ImgnothingByteArray)

                    }*/
                }
            }

        binding.profile2PicIv.setOnClickListener {
            val intent = Intent().apply {
                type = "image/"
                action = Intent.ACTION_GET_CONTENT
            }
            launcher.launch(intent)
        }
    }


    private fun uriToByteArray(uri: Uri): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        return inputStream?.readBytes() ?: byteArrayOf()
    }

    private fun imageToByteArray(drawable: Drawable?): ByteArray {
        val Drawable = (drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        Drawable.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun checkProfile() : String {
        val IntroText = binding.profile2IntroEt.text.toString()
        if(IntroText.length>150){
            binding.profile2IntroErrorTv.visibility=View.VISIBLE
            return "false"
        }

        return IntroText
    }

    fun checkOpenGallery() : ByteArray {
        val ImgnothingByteArray = imageToByteArray(requireContext().getDrawable(R.drawable.prof2_layer))
        val Imgnothing = imageToByteArray(requireContext().getDrawable(R.drawable.albumcover_5))   //기본이미지 아무렇게나 설정

        if (imageByte != ImgnothingByteArray) {   //갤러리에서 사진 선택한 경우
            return imageByte
        }else{   ////갤러리에서 사진 선택안한 경우
            return Imgnothing
        }
    }

}