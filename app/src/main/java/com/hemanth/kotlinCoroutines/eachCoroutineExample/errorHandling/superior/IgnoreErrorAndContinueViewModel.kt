package com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.superior

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.model.ApiUser
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.lang.Exception

class IgnoreErrorAndContinueViewModel(
    private val apiHelper: ApiHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                supervisorScope {

                    val usersFromApiDeferred = async { apiHelper.getUsersWithError() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                    val usersFromApi = try {
                        usersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }
                    val moreUsersFromApi = try {
                        moreUsersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }

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