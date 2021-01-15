package com.hemanth.kotlinCoroutines.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hemanth.kotlinCoroutines.data.api.ApiHelper
import com.hemanth.kotlinCoroutines.data.local.DatabaseHelper
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.exceptionHandler.ExceptionHandlerViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.superior.IgnoreErrorAndContinueViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.tryCatch.TryCatchViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.parallel.ParallelNetworkCallsViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.series.SeriesNetworkCallsViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.single.SingleNetworkCallViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.room.RoomDBViewModel
import com.hemanth.kotlinCoroutines.eachCoroutineExample.timeout.TimeoutViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper?=null) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper!!) as T
        }
        if (modelClass.isAssignableFrom(TimeoutViewModel::class.java)) {
            return TimeoutViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(TryCatchViewModel::class.java)) {
            return TryCatchViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}