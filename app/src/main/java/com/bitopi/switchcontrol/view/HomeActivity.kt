package com.bitopi.switchcontrol.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bitopi.switchcontrol.R
import com.bitopi.switchcontrol.databinding.HomeLayoutBinding
import com.bitopi.switchcontrol.model.Data
import com.bitopi.switchcontrol.model.OnOffStatus
import com.bitopi.switchcontrol.model.Switches
import com.bitopi.switchcontrol.model.network.MainRepository
import com.bitopi.switchcontrol.model.network.RetrofitService
import com.bitopi.switchcontrol.viewmodel.MainViewModel
import com.bitopi.switchcontrol.viewmodel.MyViewModelFactory

class HomeActivity : AppCompatActivity() {
    lateinit var binding : HomeLayoutBinding
    lateinit var onOffButton : ImageButton
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onOffButton = binding.onOffButton
        onOffButton.tag = R.drawable.rounded_green
        onOffButton.setBackgroundResource(R.drawable.rounded_green)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.responseModel.observe(this) {
            Log.i("SwitchControlLog",it.toString())
            if(onOffButton.tag == R.drawable.rounded_green){
                Log.i("SWITCH_CONTROL_LOG","Inside on click if")
                onOffButton.tag = R.drawable.rounded_red
                onOffButton.setBackgroundResource(R.drawable.rounded_red)
            } else {
                Log.i("SWITCH_CONTROL_LOG","Inside on click else")
                onOffButton.tag = R.drawable.rounded_green
                onOffButton.setBackgroundResource(R.drawable.rounded_green)
            }
            onOffButton.isClickable = true

        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            onOffButton.isClickable = true
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
            } else {
            }
        })



        onOffButton.setOnClickListener{
            onOffButton.isClickable = false
            Log.i("SWITCH_CONTROL_LOG","Inside on click")
            val onOffStatus: OnOffStatus = if(onOffButton.tag == R.drawable.rounded_green){
                OnOffStatus(Data(ArrayList(listOf(Switches("on", 0)))))
            } else {
                OnOffStatus(Data(ArrayList(listOf(Switches("off", 0)))))
            }
            viewModel.turnTheLight(onOffStatus)

        }
    }
}