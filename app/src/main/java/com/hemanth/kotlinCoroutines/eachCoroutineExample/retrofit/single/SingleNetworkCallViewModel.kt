package com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SingleNetworkCallViewModel(private val apiHelper: ApiHelper):ViewModel() {

    private val users=MutableLiveData<Resource<List<ApiUser>>>()

    init{
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val userFromApi=apiHelper.getUsers()
                users.postValue(Resource.success(userFromApi))
            }catch (e:Exception){
                users.postValue(e.message?.let { Resource.error(it,null) })
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }
}