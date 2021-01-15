package com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.launch

class SeriesNetworkCallsViewModel(private val apiHelper: ApiHelper):ViewModel() {
    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val usersFromApi = apiHelper.getUsers()
            val moreUsersFromApi = apiHelper.getMoreUsers()
            val allUsersFromApi = mutableListOf<ApiUser>()
            allUsersFromApi.addAll(usersFromApi)
            allUsersFromApi.addAll(moreUsersFromApi)
            users.postValue(Resource.success(allUsersFromApi))
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }
}