package com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.exceptionHandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ExceptionHandlerViewModel (private val apiHelper: ApiHelper):ViewModel(){


    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    private  val exceptionHandler = CoroutineExceptionHandler{ _ ,exception->
        users.postValue(Resource.error("Something Went Wrong $exception", null))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            users.postValue(Resource.loading(null))
            val usersFromApi = apiHelper.getUsers()
            users.postValue(Resource.success(usersFromApi))
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}