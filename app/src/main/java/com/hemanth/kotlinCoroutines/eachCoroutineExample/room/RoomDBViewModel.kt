package com.hemanth.kotlinCoroutines.eachCoroutineExample.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.local.AppDatabase
import com.hemanth.kotlinCoroutines.data.local.DatabaseHelper
import com.hemanth.kotlinCoroutines.data.local.entity.User
import com.hemanth.kotlinCoroutines.utils.Resource
import kotlinx.coroutines.launch

class RoomDBViewModel(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<User>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromDb = databaseHelper.getUsers()
                if (usersFromDb.isEmpty()) {
                    val usersFromApi = apiHelper.getUsers()
                    val usersToInsertInDB = mutableListOf<User>()
                    for (apiUser in usersFromApi) {
                        val user = User(
                            id = apiUser.id,
                            name = apiUser.name,
                            email = apiUser.email,
                            avatar = apiUser.avatar
                        )
                        usersToInsertInDB.add(user)
                    }
                    databaseHelper.insertAll(usersToInsertInDB)
                    users.postValue(Resource.success(usersToInsertInDB))

                } else {
                    users.postValue(Resource.success(usersFromDb))
                }
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }
}