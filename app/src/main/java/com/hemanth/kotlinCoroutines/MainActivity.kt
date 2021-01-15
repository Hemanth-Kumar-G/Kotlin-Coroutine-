package com.hemanth.kotlinCoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.exceptionHandler.ExceptionHandlerActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.superior.IgnoreErrorAndContinueActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.errorHandling.tryCatch.TryCatchActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.parallel.ParallelNetworkCallsActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.series.SeriesNetworkCallsActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.retrofit.single.SingleNetworkCallActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.room.RoomDBActivity
import com.hemanth.kotlinCoroutines.eachCoroutineExample.timeout.TimeoutActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
    }

    fun startRoomDatabaseActivity(view: View) {
        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
    }

    fun startTimeoutActivity(view: View) {
        startActivity(Intent(this@MainActivity, TimeoutActivity::class.java))
    }

    fun startTryCatchActivity(view: View) {
        startActivity(Intent(this@MainActivity, TryCatchActivity::class.java))
    }

    fun startExceptionHandlerActivity(view: View) {
        startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
    }

    fun startIgnoreErrorAndContinueActivity(view: View) {
        startActivity(Intent(this@MainActivity, IgnoreErrorAndContinueActivity::class.java))
    }

    fun startLongRunningTaskActivity(view: View) {}
    fun startTwoLongRunningTasksActivity(view: View) {}


}