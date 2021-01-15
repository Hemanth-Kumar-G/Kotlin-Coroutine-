package com.hemanth.kotlinCoroutines.eachCoroutineExample.task.onetask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.*

class LongRunningTaskViewModel : ViewModel() {
    private val status = MutableLiveData<Resource<String>>()

    fun startLongRunningTask() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            try {
                // do a long running task
                doLongRunningTask()
                status.postValue(Resource.success("Task Completed"))
            } catch (e: Exception) {
                status.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    private suspend fun doLongRunningTask() {
        withContext(Dispatchers.Default) {
            // your code for doing a long running task
            // Added delay to simulate
            delay(5000)
        }
    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }
}