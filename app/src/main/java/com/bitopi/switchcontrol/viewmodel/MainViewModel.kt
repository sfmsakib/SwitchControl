package com.bitopi.switchcontrol.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitopi.switchcontrol.model.Movie
import com.bitopi.switchcontrol.model.OnOffResponse
import com.bitopi.switchcontrol.model.OnOffStatus
import com.bitopi.switchcontrol.model.network.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val responseModel = MutableLiveData<OnOffResponse>()
    val movieList  = MutableLiveData<List<Movie>>()
    var job : Job? = null

    val exceptionHandler = CoroutineExceptionHandler{_,throwable ->
        onError("Exception Handled : ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun turnTheLight(onOffStatus: OnOffStatus) {
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            val response = mainRepository.turnTheLight(onOffStatus)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    responseModel.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error : ${response.message()}")
                }
            }

        }
    }
    fun getAllMovies(){
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            val response = mainRepository.getAllMovies()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    movieList.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error : ${response.message()}")
                }
            }

        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        Log.i("SWITCH_CONTROL_LOG", "Error:$message")

        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}