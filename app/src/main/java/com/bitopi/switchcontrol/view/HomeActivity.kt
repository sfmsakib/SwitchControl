package com.bitopi.switchcontrol.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.bitopi.switchcontrol.R
import com.bitopi.switchcontrol.databinding.HomeLayoutBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : HomeLayoutBinding
    lateinit var star : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        star = binding.onOffButton
        star.setOnClickListener{
            if(star.tag == R.drawable.rounded_green){
                star.tag = R.drawable.rounded_red
                star.setBackgroundResource(R.drawable.rounded_red)
            } else {
                star.tag = R.drawable.rounded_green
                star.setBackgroundResource(R.drawable.rounded_green)
            }
        }
    }
}