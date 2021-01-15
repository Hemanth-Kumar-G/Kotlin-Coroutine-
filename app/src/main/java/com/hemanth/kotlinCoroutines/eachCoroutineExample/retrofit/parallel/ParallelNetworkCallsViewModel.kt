package com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.parallel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ParallelNetworkCallsViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                // coroutineScope is needed, else in case of any network error, it will crash
                coroutineScope {

                    val usersFromApiDeferred = async { apiHelper.getUsers() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }
                    val usersFromApi = usersFromApiDeferred.await()
                    val moreUsersFromApi = moreUsersFromApiDeferred.await()
                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    users.postValue(Resource.success(allUsersFromApi))
                }

            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }
}