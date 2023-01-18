package com.bitopi.switchcontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitopi.switchcontrol.model.Movie
import com.bitopi.switchcontrol.model.ResponseModel
import com.bitopi.switchcontrol.model.network.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val responseModel = MutableLiveData<ResponseModel>()
    val movieList  = MutableLiveData<List<Movie>>()
    var job : Job? = null

    val exceptionHandler = CoroutineExceptionHandler{_,throwable ->
        onError("Exception Handled : ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun turnTheLight(){
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            val response = mainRepository.turnTheLight()
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
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}